package is.project.springbootbackend.model.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ConsultationNotFoundException extends RuntimeException{
    public ConsultationNotFoundException(Long id) {
        super(String.format("Consultation with id: %d is not found", id));
    }
}
