package is.project.springbootbackend.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
public class Student extends BaseUser{

//    @OneToMany(fetch = FetchType.EAGER)
//    private List<Professor> professors;

//    public Student( String name, String password, String email, List<Professor> professors) {
//        super(name, password, email);
//        this.professors = professors;
//    }

    public Student( String name, String password, String email,String imageUrl) {
        super(name, password, email,imageUrl);
    }


    public Student() {
    }
}
