
package cfg.clg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cfg.clg.dto.ApplicationDto;
import cfg.clg.entity.AdminEntity;
import cfg.clg.entity.ApplicationEntity;
import cfg.clg.entity.ProgramEntity;
import cfg.clg.entity.StudentEntity;
import cfg.clg.exception.AdminNotFoundException;
import cfg.clg.exception.ProgramNotFoundException;
import cfg.clg.exception.StudentNotFoundException;
import cfg.clg.repository.AdminRepository;
import cfg.clg.repository.ApplicationRepository;
import cfg.clg.repository.ProgramRepository;
import cfg.clg.repository.StudentRepository;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private ProgramRepository programRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AdminRepository adminRepository;

    public ApplicationDto processApplication(ApplicationDto dto) {
        ApplicationEntity app = new ApplicationEntity();
        app.setApplicationId(dto.getApplicationId());
        app.setStatus("applied");

        ProgramEntity program = programRepository.findById(dto.getProgramId())
                .orElseThrow(() -> new ProgramNotFoundException("Program not found with ID: " + dto.getProgramId()));

        StudentEntity student = studentRepository.findById(dto.getSid())
                .orElseThrow(() -> new StudentNotFoundException("Student not found with ID: " + dto.getSid()));

        AdminEntity admin = adminRepository.findById(dto.getAdminId())
                .orElseThrow(() -> new AdminNotFoundException("Admin not found with ID: " + dto.getAdminId()));

        app.setProgram(program);
        app.setStudent(student);
        app.setAdmin(admin);

        applicationRepository.save(app);

        return dto; // You could map back from saved entity if needed
    }

    public List<ApplicationEntity> getAllApplications() {
        return applicationRepository.findAll();
    }
}