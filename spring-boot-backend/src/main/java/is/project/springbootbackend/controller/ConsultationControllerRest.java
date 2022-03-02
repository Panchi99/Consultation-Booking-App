package is.project.springbootbackend.controller;

import is.project.springbootbackend.model.Consultation;
import is.project.springbootbackend.model.Professor;
import is.project.springbootbackend.service.ConsultationService;
import is.project.springbootbackend.service.ProfessorService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/consultations")
@CrossOrigin("http://localhost:8081")
public class ConsultationControllerRest {

    private final ConsultationService consultationService;

    public ConsultationControllerRest(ConsultationService consultationService) {
        this.consultationService = consultationService;
    }

    @GetMapping
    public List<Consultation> findAll() {
        return this.consultationService.listAllConsultations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consultation> findById(@PathVariable Long id) {
        return this.consultationService.findById(id)
                .map(consultation -> ResponseEntity.ok().body(consultation))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Consultation> save(@RequestParam String name, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDateTime, @RequestParam Integer numParticipantsAllowed, @RequestParam Long professorId){
        return this.consultationService.create(name, startDateTime,numParticipantsAllowed,professorId)
                .map(consultation -> ResponseEntity.ok().body(consultation))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        this.consultationService.delete(id);
        if(this.consultationService.findById(id).isEmpty()) return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/addToConsultation/{consultationId}/{studentId}")
    public ResponseEntity<String> addToConsultation(@PathVariable Long consultationId, @PathVariable Long studentId){

        return this.consultationService.addStudentToConsultation(studentId,consultationId)
                .map(consultation -> ResponseEntity.ok().body("Join Successful"))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
