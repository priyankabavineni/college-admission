
package cfg.clg.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cfg.clg.entity.AdminEntity;
import cfg.clg.entity.ApplicationEntity;
import cfg.clg.repository.AdminRepository;
import cfg.clg.repository.ApplicationRepository;
import cfg.clg.service.AdminService;

@SpringBootTest
public class AdminTest {

	@Autowired
	private AdminService adminService;

	@Autowired
	private ApplicationRepository applicationRepository;

	@Autowired
	private AdminRepository adminRepository;

	@Test
	public void testUpdateStatus() {
		AdminEntity admin = new AdminEntity();
		admin.setAdminId(1);
		adminRepository.save(admin);

		ApplicationEntity app = new ApplicationEntity();
		app.setApplicationId("app123");
		app.setStatus("PENDING");
		applicationRepository.save(app);

		ApplicationEntity updatedApp = adminService.updateStatus("app123", 1, "APPROVED");

		assertNotNull(updatedApp);
		assertEquals("APPROVED", updatedApp.getStatus());
		assertEquals(admin.getAdminId(), updatedApp.getAdmin().getAdminId());
	}

	@Test
	public void testGetAllApplications() {
		applicationRepository.deleteAll();

		ApplicationEntity app1 = new ApplicationEntity();
		app1.setApplicationId("app1");
		applicationRepository.save(app1);

		List<ApplicationEntity> allApps = adminService.getAllApplications();

		assertNotNull(allApps);
		assertEquals(1, allApps.size());
		assertEquals("app1", allApps.get(0).getApplicationId());
	}
}
