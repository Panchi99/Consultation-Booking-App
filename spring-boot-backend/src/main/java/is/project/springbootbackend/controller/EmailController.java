package is.project.springbootbackend.controller;

import is.project.springbootbackend.model.Consultation;
import is.project.springbootbackend.model.Student;
import is.project.springbootbackend.model.exceptions.ConsultationNotFoundException;
import is.project.springbootbackend.service.ConsultationService;
import is.project.springbootbackend.service.MailSenderService;
import is.project.springbootbackend.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mail")
@CrossOrigin("http://localhost:8081")
public class EmailController {

    private final MailSenderService mailSenderService;
    private final StudentService studentService;
    private final ConsultationService consultationService;

    public EmailController(MailSenderService mailSenderService, StudentService studentService, ConsultationService consultationService) {
        this.mailSenderService = mailSenderService;
        this.studentService = studentService;
        this.consultationService = consultationService;
    }

    @GetMapping("/sendMail/{consultationId}/{studentId}")
    public ResponseEntity<String> sendAndAddToConsultation(@PathVariable Long consultationId, @PathVariable Long studentId){
        Student student = this.studentService.findById(studentId);
        Consultation consultation = this.consultationService.findById(consultationId).orElseThrow(() -> new ConsultationNotFoundException(consultationId));

        mailSenderService.sendMail(consultation.getProfessor().getEmail(),"Нов студент за консултација",
                "Студентот со име: "+student.getName()+" се пријави за консултацијата на ден: "+consultation.getStartDateTime().toLocalDate().toString()
                        + " во "+consultation.getStartDateTime().toLocalTime().getHour()+" часот и "+consultation.getStartDateTime().toLocalTime().getMinute()+" минути!");

        return ResponseEntity.ok().body("Email Sent");
    }
}
