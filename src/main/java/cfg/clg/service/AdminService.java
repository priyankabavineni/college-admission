package cfg.clg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cfg.clg.entity.AdminEntity;
import cfg.clg.entity.ApplicationEntity;
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
	            .orElseThrow(() -> new RuntimeException("Application not found"));
	        AdminEntity admin = adminRepo.findById(adminId)
	            .orElseThrow(() -> new RuntimeException("Admin not found"));

	        application.setStatus(status.toUpperCase());
	        application.setAdmin(admin);

	        return applicationRepo.save(application);
	    }

	    // ADMIN: View All Applications
	    public List<ApplicationEntity> getAllApplications() {
	        return applicationRepo.findAll();
	    }
	}

