package is.project.springbootbackend.service;

import is.project.springbootbackend.model.Student;

import java.util.List;

public interface StudentService {

    Student findById(Long id);

    List<Student> listAll();

    Student create(String name,String password,String email,String imageUrl);
}
