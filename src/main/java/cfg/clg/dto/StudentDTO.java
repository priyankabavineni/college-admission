package cfg.clg.dto;



import lombok.Data;



@Data
public class StudentDTO {
    private int sid;
    private String sname;
    private String email;
    private Double tenthMarks;
    private Double interMarks;
    private Boolean hasEntranceExam;
    private Integer entranceExamScore;
}
