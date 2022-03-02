package is.project.springbootbackend.model.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ProfessorNotFoundException extends RuntimeException{
    public ProfessorNotFoundException(Long id) {
        super(String.format("Professor with id: %d is not found", id));
    }
}
