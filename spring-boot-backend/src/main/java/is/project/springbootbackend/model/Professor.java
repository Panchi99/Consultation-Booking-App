package is.project.springbootbackend.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
public class Professor extends BaseUser{

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "professor")
    @JsonIgnoreProperties("professor")
    private List<Consultation> consultations;


    public Professor(String name, String password, String email, String imageUrl, List<Consultation> consultations) {
        super(name,password,email,imageUrl);
        this.consultations = consultations;
    }
    public Professor(){
    }
}
