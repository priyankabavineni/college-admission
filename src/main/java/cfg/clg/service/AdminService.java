
package cfg.clg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cfg.clg.entity.AdminEntity;
import cfg.clg.entity.ApplicationEntity;
import cfg.clg.exception.AdminNotFoundException;
import cfg.clg.exception.ApplicationNotFoundException;
import cfg.clg.repository.AdminRepository;
import cfg.clg.repository.ApplicationRepository;

@Service
public class AdminService {

    @Autowired
    private ApplicationRepository applicationRepo;

    @Autowired
    private AdminRepository adminRepo;

    public ApplicationEntity updateStatus(String applicationId, int adminId, String status) {
        ApplicationEntity application = applicationRepo.findById(applicationId)
                .orElseThrow(() -> new ApplicationNotFoundException("Application id not found: "+applicationId ));

        AdminEntity admin = adminRepo.findById(adminId)
                .orElseThrow(() -> new AdminNotFoundException("Admin id not found: "+adminId));

        application.setStatus(status.toUpperCase());
        application.setAdmin(admin);

        return applicationRepo.save(application);
    }

    public List<ApplicationEntity> getAllApplications() {
        return applicationRepo.findAll();
    }
}


