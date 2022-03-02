package is.project.springbootbackend.service.impl;

import is.project.springbootbackend.model.Student;
import is.project.springbootbackend.model.exceptions.StudentNotFoundException;
import is.project.springbootbackend.repository.StudentRepository;
import is.project.springbootbackend.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student findById(Long id) {
        return this.studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
    }

    @Override
    public List<Student> listAll() {
        return this.studentRepository.findAll();
    }

    @Override
    public Student create(String name,String password,String email,String imageUrl) {
        return this.studentRepository.save(new Student(name,password,email,imageUrl));
    }
}
