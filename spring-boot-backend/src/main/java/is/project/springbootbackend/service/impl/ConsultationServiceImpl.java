package is.project.springbootbackend.service.impl;

import is.project.springbootbackend.model.exceptions.ConsultationNotFoundException;
import is.project.springbootbackend.model.Consultation;
import is.project.springbootbackend.model.Professor;
import is.project.springbootbackend.model.Student;
import is.project.springbootbackend.model.exceptions.ProfessorNotFoundException;
import is.project.springbootbackend.model.exceptions.StudentNotFoundException;
import is.project.springbootbackend.repository.ConsultationRepository;
import is.project.springbootbackend.repository.ProfessorRepository;
import is.project.springbootbackend.repository.StudentRepository;
import is.project.springbootbackend.service.ConsultationService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ConsultationServiceImpl implements ConsultationService {

    private final ConsultationRepository consultationRepository;
    private final StudentRepository studentRepository;
    private final ProfessorRepository professorRepository;

    public ConsultationServiceImpl(ConsultationRepository consultationRepository, StudentRepository studentRepository, ProfessorRepository professorRepository) {
        this.consultationRepository = consultationRepository;
        this.studentRepository = studentRepository;
        this.professorRepository = professorRepository;
    }

    @Override
    public List<Consultation> listAllConsultations() {
        return this.consultationRepository.findAll();
    }

    @Override
    public Optional<Consultation> findById(Long id) {
        return this.consultationRepository.findById(id);
    }

    @Override
    public Optional<Consultation> create(String name, LocalDateTime startDateTime, Integer numParticipantsAllowed, Long professorId) {

        Professor professor = this.professorRepository.findById(professorId).orElseThrow(() -> new ProfessorNotFoundException(professorId));
        return Optional.of(this.consultationRepository.save(new Consultation(name,startDateTime,startDateTime.plusMinutes(30).withNano(0),numParticipantsAllowed,professor)));
    }

    @Override
    @Transactional
    public Consultation update(Long id, String name, LocalDateTime startDateTime, LocalDateTime endDateTime, Integer numParticipantsAllowed,List<Long> participants,Long professorId) {

        Professor professor = this.professorRepository.findById(professorId).orElseThrow(() -> new ProfessorNotFoundException(professorId));

        Consultation consultation = this.consultationRepository.findById(id).orElseThrow(() -> new ConsultationNotFoundException(id));

        List<Student> studentParticipants = this.studentRepository.findAllById(participants);

        consultation.setId(id);
        consultation.setName(name);
        consultation.setStartDateTime(startDateTime);
        consultation.setEndDateTime(endDateTime);
        consultation.setNumParticipantsAllowed(numParticipantsAllowed);
        consultation.setStudents(studentParticipants);
       // consultation.setProfessor(professor);
        return consultation;
    }


    @Override
    public Consultation delete(Long id) {
        Consultation consultation = this.consultationRepository.findById(id).get();
        this.consultationRepository.delete(consultation);
        return consultation;
    }

    @Override
    @Transactional
    public Optional<Consultation> addStudentToConsultation(Long studentId, Long consultationId) {
        Student student = this.studentRepository.findById(studentId).orElseThrow(()-> new StudentNotFoundException(studentId));
        Consultation consultation = this.consultationRepository.findById(consultationId).orElseThrow(() -> new ConsultationNotFoundException(consultationId));
        consultation.getStudents().add(student);
        return  Optional.of(consultation);
    }
}
