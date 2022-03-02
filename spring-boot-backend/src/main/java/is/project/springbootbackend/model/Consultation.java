package is.project.springbootbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Consultation {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @DateTimeFormat(pattern = "dd:MM:yyyy HH:mm")
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private Integer numParticipantsAllowed;
    @ManyToMany
    private List<Student> students;

    @ManyToOne
    @JsonIgnoreProperties("consultations")
    private Professor professor;

//    public Consultation(String name, LocalDateTime startDateTime, LocalDateTime endDateTime, Integer numParticipantsAllowed, Professor professor) {
//        this.name = name;
//        this.startDateTime = startDateTime;
//        this.endDateTime = endDateTime;
//        this.numParticipantsAllowed = numParticipantsAllowed;
//        this.students = new ArrayList<>();
//        this.professor = professor;
//    }


    public Consultation( String name, LocalDateTime startDateTime, LocalDateTime endDateTime, Integer numParticipantsAllowed, Professor professor) {
        this.name = name;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.numParticipantsAllowed = numParticipantsAllowed;
        this.students = new ArrayList<>();
        this.professor = professor;
    }

    public Consultation() {
    }

    @Override
    public String toString() {
        return "Consultation{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startDateTime=" + startDateTime +
                ", endDateTime=" + endDateTime +
                ", numParticipantsAllowed=" + numParticipantsAllowed +
                ", students=" + students +
                '}';
    }
}
