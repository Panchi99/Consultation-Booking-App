package is.project.springbootbackend.service;

import is.project.springbootbackend.model.Consultation;
import is.project.springbootbackend.model.Professor;

import java.util.List;
import java.util.Optional;

public interface ProfessorService {
    Optional<Professor> findById(Long id);

    List<Professor> listAll();

    Optional<Professor>  create(String name, String password, String email,String imageUrl);

    Professor addConsultation(Long professorId, Long consultationId);

    void deleteById(Long professorId);

}
