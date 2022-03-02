package is.project.springbootbackend.service;

import is.project.springbootbackend.model.Consultation;
import is.project.springbootbackend.model.Student;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ConsultationService {

    List<Consultation> listAllConsultations();

    Optional<Consultation> findById(Long id);

    Optional<Consultation> create(String name, LocalDateTime startDateTime, Integer numParticipantsAllowed,Long professorId);

    Consultation update(Long id, String name, LocalDateTime startDateTime, LocalDateTime endDateTime, Integer numParticipantsAllowed,List<Long> participants,Long professorId);

    Consultation delete(Long id);

    Optional<Consultation> addStudentToConsultation(Long studentId, Long consultationId);

}
