package cfg.clg.dto;



import java.util.List;

import cfg.clg.entity.ApplicationEntity;

import lombok.Data;



@Data
public class StudentWithApplicationsDTO {
    private StudentDTO student;
    private List<ApplicationEntity> applications;
}
