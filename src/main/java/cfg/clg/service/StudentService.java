package cfg.clg.service;

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

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repo;

    @Autowired
    private ApplicationRepository applicationRepo;

    public StudentDTO saveStudent(StudentDTO student) {
        validateStudent(student);

        StudentEntity studentEntity = new StudentEntity();
        BeanUtils.copyProperties(student, studentEntity);
        StudentEntity savedEntity = repo.save(studentEntity);

        StudentDTO savedDto = new StudentDTO();
        BeanUtils.copyProperties(savedEntity, savedDto);
        return savedDto;
    }

    private void validateStudent(StudentDTO student) {
        if (student.getSname() == null || student.getSname().length() < 5) {
            throw new InvalidNameException("Name must be minimum 5 characters");
        }
        if (student.getEmail() == null || !student.getEmail().endsWith("@gmail.com")) {
            throw new InvalidEmailException("Email must end with @gmail.com");
        }
    }

    public StudentDTO getStudentById(int sid) {
        Optional<StudentEntity> opt = repo.findById(sid);
        if (opt.isEmpty()) {
            throw new StudentNotFoundException("Student with ID " + sid + " not found.");
        }
        StudentDTO dto = new StudentDTO();
        BeanUtils.copyProperties(opt.get(), dto);
        return dto;
    }

    public StudentWithApplicationsDTO getStudentWithApplications(int sid) {
        StudentDTO student = getStudentById(sid);
        List<ApplicationEntity> applications = applicationRepo.findByStudentSid(sid);

        StudentWithApplicationsDTO dto = new StudentWithApplicationsDTO();
        dto.setStudent(student);
        dto.setApplications(applications);

        return dto;
    }

}
