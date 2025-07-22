package cfg.clg.service;



import java.util.List;



import org.springframework.beans.BeanUtils;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;



import cfg.clg.dto.StudentDTO;

import cfg.clg.dto.StudentWithApplicationsDTO;

import cfg.clg.entity.ApplicationEntity;

import cfg.clg.entity.StudentEntity;

import cfg.clg.exception.InvalidEmailException;

import cfg.clg.exception.InvalidNameException;

import cfg.clg.exception.StudentNotFoundException;

import cfg.clg.repository.ApplicationRepository;

import cfg.clg.repository.StudentRepository;



@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private ApplicationRepository appRepo;

    public StudentDTO saveStudent(StudentDTO dto) {
        if (dto.getSname() == null || dto.getSname().length() < 5)
            throw new InvalidNameException("Name must be at least 5 characters.");
        if (dto.getEmail() == null || !dto.getEmail().endsWith("@gmail.com"))
            throw new InvalidEmailException("Email must end with @gmail.com");

        StudentEntity entity = new StudentEntity();
        BeanUtils.copyProperties(dto, entity);

        if (!Boolean.TRUE.equals(dto.getHasEntranceExam())) {
            entity.setEntranceExamScore(null);
        }

        entity = studentRepo.save(entity);

        StudentDTO out = new StudentDTO();
        BeanUtils.copyProperties(entity, out);
        return out;
    }

    public StudentWithApplicationsDTO getStudentWithApplications(int sid) {
        StudentEntity student = studentRepo.findById(sid)
            .orElseThrow(() -> new StudentNotFoundException("Student ID not found: " + sid));

        List<ApplicationEntity> apps = appRepo.findByStudentSid(sid);
        StudentDTO dto = new StudentDTO();
        BeanUtils.copyProperties(student, dto);

        StudentWithApplicationsDTO out = new StudentWithApplicationsDTO();
        out.setStudent(dto);
        out.setApplications(apps);
        return out;
    }

    /*public boolean canReapply(int sid) {
        List<ApplicationEntity> apps = appRepo.findByStudentSid(sid);
        return apps.stream().anyMatch(a ->
            "REJECTED".equalsIgnoreCase(a.getStatus()) &&
            "No available seats.".equalsIgnoreCase(a.getRejectionReason())
        );
    }*/
}