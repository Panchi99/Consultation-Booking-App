package is.project.springbootbackend.service.impl;
import is.project.springbootbackend.model.Consultation;
import is.project.springbootbackend.model.Professor;
import is.project.springbootbackend.model.exceptions.ConsultationNotFoundException;
import is.project.springbootbackend.model.exceptions.ProfessorNotFoundException;
import is.project.springbootbackend.repository.ConsultationRepository;
import is.project.springbootbackend.repository.ProfessorRepository;
import is.project.springbootbackend.service.ProfessorService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfessorServiceImpl implements ProfessorService {

    private final ProfessorRepository professorRepository;
    private final ConsultationRepository consultationRepository;

    public ProfessorServiceImpl(ProfessorRepository professorRepository, ConsultationRepository consultationRepository) {
        this.professorRepository = professorRepository;
        this.consultationRepository = consultationRepository;
    }

    @Override
    public Optional<Professor> findById(Long id) {
        return this.professorRepository.findById(id);
    }

    @Override
    public List<Professor> listAll() {
        return this.professorRepository.findAll();
    }

    @Override
    public Optional<Professor>  create(String name, String password, String email,String imageUrl) {
        //List<Consultation> consultations = this.consultationRepository.findAll();
        return Optional.of(this.professorRepository.save(new Professor(name,password,email,imageUrl,new ArrayList<>())));
    }

    @Override
    @Transactional
    public Professor addConsultation(Long professorId, Long consultationId) {
        Professor professor = findById(professorId).orElseThrow(() -> new ProfessorNotFoundException(professorId));;
        Consultation consultation = this.consultationRepository.findById(consultationId).orElseThrow(() -> new ConsultationNotFoundException(consultationId));
        professor.getConsultations().add(consultation);
        return professor;
    }

    @Override
    public void deleteById(Long professorId) {
    this.professorRepository.deleteById(professorId);
    }
}
