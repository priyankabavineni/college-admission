
package cfg.clg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import cfg.clg.entity.ApplicationEntity;

import cfg.clg.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PutMapping("/update-status")
    public ResponseData updateStatus(
            @RequestParam String applicationId,
            @RequestParam int adminId,
            @RequestParam String status) {

        ResponseData response = new ResponseData();

        try {
            ApplicationEntity updatedApp = adminService.updateStatus(applicationId, adminId, status);
            response.setStatus("success");
            response.setData(updatedApp);
        } catch (Exception ex) {
            response.setStatus("failed");
            response.setMessage(ex.getMessage());
        }

        return response;
    }

    @GetMapping("/applications")
    public ResponseData viewAllApplications() {
        ResponseData response = new ResponseData();

        List<ApplicationEntity> applications = adminService.getAllApplications();
        response.setStatus("success");
        response.setData(applications);

        return response;
    }
}



