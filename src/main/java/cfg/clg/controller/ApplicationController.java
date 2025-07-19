package cfg.clg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import cfg.clg.dto.ApplicationDto;
import cfg.clg.entity.ApplicationEntity;
import cfg.clg.exception.AdminNotFoundException;
import cfg.clg.exception.ProgramNotFoundException;
import cfg.clg.exception.StudentNotFoundException;
import cfg.clg.service.ApplicationService;
import cfg.clg.controller.ResponseData;

@RestController
@RequestMapping("/api")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @PostMapping("/applicationsave")
    public ResponseData saveApplication(@RequestBody ApplicationDto dto) {
        ResponseData response = new ResponseData();

        try {
            ApplicationDto savedDto = applicationService.processApplication(dto);
            response.setStatus("success");
            response.setData(savedDto);
        } catch (ProgramNotFoundException | StudentNotFoundException | AdminNotFoundException ex) {
            response.setStatus("failed");
            response.setMessage(ex.getMessage());
        } catch (Exception ex) {
            response.setStatus("failed");
            response.setMessage("Internal error: " + ex.getMessage());
        }

        return response;
    }

    @GetMapping("/allApplications")
    public ResponseData getAllApplications() {
        ResponseData response = new ResponseData();

        try {
            List<ApplicationEntity> applications = (List<ApplicationEntity>) applicationService.getAllApplications();
            response.setStatus("success");
            response.setData(applications);
        } catch (Exception ex) {
            response.setStatus("failed");
            response.setMessage("Error fetching applications: " + ex.getMessage());
        }

        return response;
    }
}
