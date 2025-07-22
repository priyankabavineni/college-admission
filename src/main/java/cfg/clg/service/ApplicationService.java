package cfg.clg.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cfg.clg.dto.ApplicationDto;
import cfg.clg.dto.ProgramApplicationCountDto;
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
	private ApplicationRepository repo;

	@Autowired
	private ProgramRepository programRepo;

	@Autowired
	private StudentRepository studentRepo;

	@Autowired
	private AdminRepository adminRepo;

	// Create or update an application based on the DTO
//  

	public ApplicationDto processApplication(ApplicationDto dto) {
		ProgramEntity program = programRepo.findById(dto.getProgramId())
				.orElseThrow(() -> new ProgramNotFoundException("Program not found with ID: " + dto.getProgramId()));

		StudentEntity student = studentRepo.findById(dto.getSid())
				.orElseThrow(() -> new StudentNotFoundException("Student not found with ID: " + dto.getSid()));

		AdminEntity admin = adminRepo.findById(dto.getAdminId())
				.orElseThrow(() -> new AdminNotFoundException("Admin not found with ID: " + dto.getAdminId()));

		ApplicationEntity app = new ApplicationEntity();
		BeanUtils.copyProperties(dto, app, "student", "program", "admin", "entranceExamRank", "paymentDone", "status");

		app.setStudent(student);
		app.setProgram(program);
		app.setAdmin(admin);

		String status = dto.getStatus() != null ? dto.getStatus().toLowerCase() : "applied";
		app.setStatus(status);

		app.setEntranceExamRank(dto.getEntranceexam());
		app.setPaymentDone(dto.isPaymentdone());

		if ("approved".equalsIgnoreCase(status)) {
			Integer currentSeats = program.getAvailableSeats();

			System.out.println("----[DEBUG] Approving Application----");
			System.out.println("Program ID       : " + program.getProgramId());
			System.out.println("Program Name     : " + program.getProgram());
			System.out.println("Available Seats  : " + currentSeats);

			if (currentSeats == null || currentSeats <= 0) {
				System.out.println("No seats left in program. Cannot approve.");
				throw new IllegalStateException("No seats available in the selected program: " + program.getProgram());
			}

			int newSeats = currentSeats - 1;
			program.setAvailableSeats(newSeats);
			programRepo.save(program);

			System.out.println("Updated Seats    : " + newSeats);
			System.out.println("----[DEBUG] Approval Complete----");
		}

		app = repo.save(app);

		return convertToDto(app);
	}

	// Convert entity to DTO
	private ApplicationDto convertToDto(ApplicationEntity app) {
		ApplicationDto dto = new ApplicationDto();
		BeanUtils.copyProperties(app, dto, "student", "program", "admin", "entranceexam", "paymentdone");

		// Set IDs from related entities
		dto.setProgramId(app.getProgram() != null ? app.getProgram().getProgramId() : null);
		dto.setSid(app.getStudent() != null ? app.getStudent().getSid() : null);
		dto.setAdminId(app.getAdmin() != null ? app.getAdmin().getAdminId() : null);

		dto.setEntranceexam(app.getEntranceExamRank() != null ? app.getEntranceExamRank() : 0);
		dto.setPaymentdone(app.isPaymentDone());

		return dto;
	}

	// Get all applications
	public List<ApplicationEntity> getAll() {
		return repo.findAll();
	}

	// Get application by ID
	public ApplicationEntity getById(String id) {
		return repo.findById(id).orElse(null);
	}

	// Get applications by status
	public List<ApplicationEntity> getByStatus(String status) {
		return repo.findByStatus(status);
	}

	// Group applications by program
	public List<ProgramApplicationCountDto> countByProgram() {
		var raw = repo.countApplicationsGroupedByProgram();
		return raw.stream().map(row -> {
			ProgramEntity p = (ProgramEntity) row[0];
			Long cnt = (Long) row[1];
			var dto = new ProgramApplicationCountDto();
			dto.setProgramId(p.getProgramId());
			dto.setProgramName(p.getProgram());
			dto.setApplicationCount(cnt);
			return dto;
		}).collect(Collectors.toList());
	}
}
