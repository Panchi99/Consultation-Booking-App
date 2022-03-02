package is.project.springbootbackend.config;


import is.project.springbootbackend.model.Consultation;
import is.project.springbootbackend.model.Professor;
import is.project.springbootbackend.model.Student;
import is.project.springbootbackend.model.exceptions.ProfessorNotFoundException;
import is.project.springbootbackend.service.ConsultationService;
import is.project.springbootbackend.service.ProfessorService;
import is.project.springbootbackend.service.StudentService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Component
public class DataInitializer {

    private final ConsultationService consultationService;

    private final ProfessorService professorService;

    private final StudentService studentService;

    public DataInitializer(ConsultationService consultationService, ProfessorService professorService, StudentService studentService) {
        this.consultationService = consultationService;
        this.professorService = professorService;
        this.studentService = studentService;
    }

    //    private MatchType randomizeEventType(int i) {
//        if(i % 3 == 0) return MatchType.FRIENDLY;
//        else if(i % 3 == 1) return MatchType.CHARITY;
//        return MatchType.COMPETITIVE;
//    }

    @PostConstruct
    public void initData() {
        for (int i = 1; i < 6; i++) {
            if (i==1) {
                Professor newProfessor = this.professorService.create("Professor " + i, "pass" + i, "professorprofessor2022@gmail.com","https://image.shutterstock.com/image-vector/sample-stamp-grunge-texture-vector-260nw-1389188336.jpg").get();
                Consultation consultation = this.consultationService.create("Consultation " + i, LocalDateTime.now().withNano(0), 5, newProfessor.getId()).get();
                this.professorService.addConsultation(newProfessor.getId(), consultation.getId());

            }
            else {
                Professor newProfessor = this.professorService.create("Professor " + i, "pass" + i, "professor" + i + "@edu.com","https://image.shutterstock.com/image-vector/sample-stamp-grunge-texture-vector-260nw-1389188336.jpg").get();
                Consultation consultation = this.consultationService.create("Consulataion " + i, LocalDateTime.now().withNano(0), 5, newProfessor.getId()).get();
                this.professorService.addConsultation(newProfessor.getId(), consultation.getId());
            }
        }

        for (int i = 1; i < 11; i++) {
            if (i==1)
                this.studentService.create("Main Student" + i, "Student" + i , "student181046@gmail.com","https://image.shutterstock.com/image-vector/sample-stamp-grunge-texture-vector-260nw-1389188336.jpg");
            else
                this.studentService.create("Student: " + i, "Student" + i , "student"+i+"@students.edu.com","https://image.shutterstock.com/image-vector/sample-stamp-grunge-texture-vector-260nw-1389188336.jpg");
        }
    }
}
