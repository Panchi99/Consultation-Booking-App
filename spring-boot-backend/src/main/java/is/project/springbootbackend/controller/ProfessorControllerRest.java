package is.project.springbootbackend.controller;
import is.project.springbootbackend.model.Consultation;
import is.project.springbootbackend.model.Professor;
import is.project.springbootbackend.service.ProfessorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/professors")
@CrossOrigin("http://localhost:8081")
public class ProfessorControllerRest {

    private final ProfessorService professorService;

    public ProfessorControllerRest(ProfessorService manufacturerService) {
        this.professorService = manufacturerService;
    }

    @GetMapping
    public List<Professor> findAll() {
        return this.professorService.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Professor> findById(@PathVariable Long id) {
        return this.professorService.findById(id)
                .map(professor -> ResponseEntity.ok().body(professor))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Professor> save(@RequestParam String name,@RequestParam String email, @RequestParam String password,@RequestParam String imageUrl){
        return this.professorService.create(name, email,password,imageUrl)
                .map(professor -> ResponseEntity.ok().body(professor))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        this.professorService.deleteById(id);
        if(this.professorService.findById(id).isEmpty()) return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/consultations/{professorId}")
    public ResponseEntity<List<Consultation>> findConsultationsByProfessorId(@PathVariable Long professorId) {
        return this.professorService.findById(professorId)
                .map(professor -> professor.getConsultations())
                .map(consultations -> ResponseEntity.ok().body(consultations))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}


