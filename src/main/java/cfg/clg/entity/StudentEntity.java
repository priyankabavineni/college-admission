package cfg.clg.entity;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(schema = "collegeadmissionsystem", name = "student")
public class StudentEntity {

    @Id
    private int sid;

    private String sname;
    private String email;

    @Column
    private double tenthMarks;

    @Column
    private double interMarks;

    private Boolean hasEntranceExam;
    private Integer entranceExamScore;

    @Column
    private String status; // ✅ New field to store APPROVED/REJECTED

    @OneToMany(mappedBy = "student")
    @JsonBackReference
    private List<ApplicationEntity> applications;
}
