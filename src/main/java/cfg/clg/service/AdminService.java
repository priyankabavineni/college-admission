package cfg.clg.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cfg.clg.dto.ApplicationActionDto;
import cfg.clg.dto.ApplicationResponseDto;
import cfg.clg.entity.AdminEntity;
import cfg.clg.entity.ApplicationEntity;
import cfg.clg.entity.StudentEntity;
import cfg.clg.exception.AdminNotFoundException;
import cfg.clg.exception.ApplicationNotFoundException;
import cfg.clg.exception.InvalidActionException;
import cfg.clg.exception.UnauthorizedException;
import cfg.clg.repository.AdminRepository;
import cfg.clg.repository.ApplicationRepository;
import cfg.clg.repository.StudentRepository;
import jakarta.transaction.Transactional;

@Service
public class AdminService {

	@Autowired
	private AdminRepository adminRepo;
	@Autowired
	private ApplicationRepository appRepo;
	@Autowired
	private StudentRepository studentRepo;

	public List<ApplicationResponseDto> getApplicationsByStatus(int adminId, String status) {
		adminRepo.findById(adminId)
				.orElseThrow(() -> new AdminNotFoundException("Admin not found with ID: " + adminId));

		List<ApplicationEntity> entities = appRepo.findByAdmin_AdminIdAndStatusIgnoreCase(adminId, status);

		List<ApplicationResponseDto> result = new ArrayList<>();
		for (ApplicationEntity app : entities) {
			result.add(toResponseDto(app));
		}
		return result;
	}

	@Transactional
	public ApplicationResponseDto actOnApplication(ApplicationActionDto dto)
	        throws InvalidActionException, UnauthorizedException {

	    AdminEntity admin = adminRepo.findById(dto.getAdminId())
	            .orElseThrow(() -> new AdminNotFoundException("Admin not found with ID: " + dto.getAdminId()));

	    ApplicationEntity app = appRepo.findById(dto.getApplicationId())
	            .orElseThrow(() -> new ApplicationNotFoundException(dto.getApplicationId()));

	    if (app.getAdmin() == null || app.getAdmin().getAdminId() != dto.getAdminId()) {
	        throw new UnauthorizedException("Not authorized");
	    }

	    StudentEntity student = app.getStudent();

	    String action = dto.getAction().toUpperCase(Locale.ROOT);

	    if ("APPROVE".equals(action)) {
	        if (!app.isPaymentDone()) {
	            // If fee not paid, reject automatically with reason
	            app.setStatus("REJECTED");
	            app.setRejectionReason("Fee not paid");
	            student.setStatus("REJECTED");
	        } else if (app.getEntranceExamRank() > 5000) {
	            // Rank too high, reject automatically
	            app.setStatus("REJECTED");
	            app.setRejectionReason("Rank too high");
	            student.setStatus("REJECTED");
	        } else {
	            app.setStatus("APPROVED");
	            student.setStatus("APPROVED");
	        }
	    } else if ("REJECT".equals(action)) {
	        if (dto.getRejectionReason() == null || dto.getRejectionReason().trim().isEmpty()) {
	            throw new InvalidActionException("Reason required");
	        }
	        app.setStatus("REJECTED");
	        app.setRejectionReason(dto.getRejectionReason());
	        student.setStatus("REJECTED");
	    } else {
	        throw new InvalidActionException("Unknown action");
	    }

	    // Save both entities explicitly to persist changes
	    studentRepo.save(student);  // Save student status update
	    app = appRepo.save(app);    // Save application updates

	    return toResponseDto(app);
	}




	private ApplicationResponseDto toResponseDto(ApplicationEntity app) {
		ApplicationResponseDto dto = new ApplicationResponseDto();
		dto.setApplicationId(app.getApplicationId());
		dto.setSid(app.getStudent().getSid());
		dto.setProgramId(app.getProgram().getProgramId());
		dto.setPaymentDone(app.isPaymentDone());
		dto.setEntranceExamRank(app.getEntranceExamRank());
		dto.setStatus(app.getStatus());
		dto.setRejectionReason(app.getRejectionReason());
		return dto;
	}

}
