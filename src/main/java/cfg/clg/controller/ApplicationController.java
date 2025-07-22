/*package cfg.clg.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cfg.clg.dto.ApplicationDto;
import cfg.clg.dto.ProgramApplicationCountDto;
import cfg.clg.entity.ApplicationEntity;
import cfg.clg.entity.ProgramEntity;
import cfg.clg.exception.AdminNotFoundException;
import cfg.clg.exception.ProgramNotFoundException;
import cfg.clg.exception.StudentNotFoundException;
import cfg.clg.repository.ApplicationRepository;
import cfg.clg.service.ApplicationService;

@RestController
@RequestMapping("/api")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;
    
    @Autowired
    private ApplicationRepository applicationRepository;

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
            List<ApplicationEntity> applications = (List<ApplicationEntity>) applicationService.getAll();
            response.setStatus("success");
            response.setData(applications);
        } catch (Exception ex) {
            response.setStatus("failed");
            response.setMessage("Error fetching applications: " + ex.getMessage());
        }

        return response;
    }
    @GetMapping("/application/{id}")
    public ApplicationEntity getApplicationById(@PathVariable String id) {
        ApplicationEntity app = applicationService.getById(id);
        // returns null if not found
        return app;
    }

    @GetMapping("/applications/status/{status}")
    public List<ApplicationEntity> getApplicationsByStatus(@PathVariable String status) {
        return applicationService.getByStatus(status);
    }

    @GetMapping("/applications/countByProgram")
    public List<ProgramApplicationCountDto> countApplicationsByProgram() {
        List<Object[]> results = applicationRepository.countApplicationsGroupedByProgram();
        List<ProgramApplicationCountDto> dtoList = new ArrayList<>();
        for (Object[] row : results) {
            ProgramEntity program = (ProgramEntity) row[0];
            Long count = (Long) row[1];

            ProgramApplicationCountDto dto = new ProgramApplicationCountDto();
            dto.setProgramId(program.getProgramId());
            dto.setProgramName(program.getProgram());
            dto.setApplicationCount(count);

            dtoList.add(dto);
        }
        return dtoList;
    }
}*/


/*package cfg.clg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cfg.clg.dto.ApplicationDto;
import cfg.clg.entity.ApplicationEntity;
import cfg.clg.service.ApplicationService;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    // Create or update application
    @PostMapping
    public ResponseEntity<ApplicationDto> processApplication(@RequestBody ApplicationDto applicationDto) {
        ApplicationDto savedDto = applicationService.processApplication(applicationDto);
        return ResponseEntity.ok(savedDto);
    }

    // Get all applications (returns entities, can be adapted to return DTOs)
    @GetMapping
    public ResponseEntity<List<ApplicationEntity>> getAllApplications() {
        List<ApplicationEntity> applications = applicationService.getAll();
        return ResponseEntity.ok(applications);
    }

    // Get application by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApplicationEntity> getApplicationById(@PathVariable String id) {
        ApplicationEntity app = applicationService.getById(id);
        if (app == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(app);
    }

    // Get applications by status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<ApplicationEntity>> getApplicationsByStatus(@PathVariable String status) {
        List<ApplicationEntity> apps = applicationService.getByStatus(status);
        return ResponseEntity.ok(apps);
    }
}*/





package cfg.clg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import cfg.clg.dto.ApplicationDto;
import cfg.clg.entity.ApplicationEntity;
import cfg.clg.service.ApplicationService;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    // Create or update application
    @PostMapping
    public ResponseData processApplication(@RequestBody ApplicationDto applicationDto) {
        ResponseData response = new ResponseData();
        try {
            ApplicationDto savedDto = applicationService.processApplication(applicationDto);
            response.setStatus("success");
            response.setMessage("Application processed successfully");
            response.setData(savedDto);
        } catch (Exception e) {
            response.setStatus("error");
            response.setMessage(e.getMessage());
        }
        return response;
    }

    // Get all applications
    @GetMapping
    public ResponseData getAllApplications() {
        ResponseData response = new ResponseData();
        try {
            List<ApplicationEntity> applications = applicationService.getAll();
            response.setStatus("success");
            response.setMessage("All applications retrieved");
            response.setData(applications);
        } catch (Exception e) {
            response.setStatus("error");
            response.setMessage(e.getMessage());
        }
        return response;
    }

    // Get application by ID
    @GetMapping("/{id}")
    public ResponseData getApplicationById(@PathVariable String id) {
        ResponseData response = new ResponseData();
        ApplicationEntity app = applicationService.getById(id);
        if (app == null) {
            response.setStatus("error");
            response.setMessage("Application not found for ID: " + id);
        } else {
            response.setStatus("success");
            response.setMessage("Application retrieved successfully");
            response.setData(app);
        }
        return response;
    }

    // Get applications by status
    @GetMapping("/status/{status}")
    public ResponseData getApplicationsByStatus(@PathVariable String status) {
        ResponseData response = new ResponseData();
        try {
            List<ApplicationEntity> apps = applicationService.getByStatus(status);
            response.setStatus("success");
            response.setMessage("Applications with status '" + status + "' retrieved");
            response.setData(apps);
        } catch (Exception e) {
            response.setStatus("error");
            response.setMessage(e.getMessage());
        }
        return response;
    }
}
