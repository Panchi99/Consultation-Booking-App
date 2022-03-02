package is.project.springbootbackend.model;


import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
public class BaseUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String password;
    private String email;
    private String imageUrl;
    //private UserType userType;


    public BaseUser(String name, String password, String email,String imageUrl) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.imageUrl = imageUrl;
    }
    public BaseUser(){

    }
}
