//package cfg.clg.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import cfg.clg.dto.StudentDTO;
//import cfg.clg.entity.ApplicationEntity;
//import cfg.clg.controller.*;
//import cfg.clg.service.StudentService;
//
//@RestController
//@RequestMapping("/student")
//public class StudentController {
//
//    @Autowired
//    private StudentService studentService;
//
//    @PostMapping("/save")
//    public ResponseData saveStudent(@RequestBody StudentDTO student) {
//        ResponseData response = new ResponseData();
//
//        try {
//            String msg = studentService.saveStudent(student);
//            response.setStatus("success");
//            response.setMessage(msg);
//        } catch (Exception e) {
//            response.setStatus("failed");
//            response.setMessage(e.getMessage());
//        }
//
//        return response;
//    }
//
//    @GetMapping("/status")
//    public ResponseData getApplicationStatus(@RequestParam int sid) {
//        ResponseData response = new ResponseData();
//
//        try {
//            List<ApplicationEntity> applications = studentService.getApplicationsByStudentId(sid);
//            response.setStatus("success");
//            response.setData(applications);
//        } catch (Exception e) {
//            response.setStatus("failed");
//            response.setMessage(e.getMessage());
//        }
//
//        return response;
//    }
//}


//package cfg.clg.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import cfg.clg.dto.StudentDTO;
//import cfg.clg.entity.ApplicationEntity;
//import cfg.clg.exception.InvalidEmailException;
//import cfg.clg.exception.InvalidNameException;
//import cfg.clg.service.StudentService;
//
//@RestController
//@RequestMapping("/student")
//public class StudentController {
//
//    @Autowired
//    private StudentService studentService;
//
//    @PostMapping("/save")
//    public ResponseData saveStudent(@RequestBody StudentDTO student) {
//        ResponseData response = new ResponseData();
//        try {
//            StudentDTO savedStudent = studentService.saveStudent(student);
//            response.setStatus("success");
//            response.setMessage("saved successfully");
//            response.setData(savedStudent);
//        } catch (InvalidNameException | InvalidEmailException e) {
//            response.setStatus("failed");
//            response.setMessage(e.getMessage());
//        } catch (Exception e) {
//            response.setStatus("failed");
//            response.setMessage("Internal server error");
//        }
//        return response;
//    }
//
//    @GetMapping("/status")
//    public ResponseData getApplicationStatus(@RequestParam int sid) {
//        ResponseData response = new ResponseData();
//        try {
//            List<ApplicationEntity> applications = studentService.getApplicationsByStudentId(sid);
//            response.setStatus("success");
//            response.setData(applications);
//        } catch (Exception e) {
//            response.setStatus("failed");
//            response.setMessage(e.getMessage());
//        }
//        return response;
//    }
//}
package cfg.clg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cfg.clg.dto.StudentDTO;
import cfg.clg.dto.StudentWithApplicationsDTO;
import cfg.clg.exception.InvalidEmailException;
import cfg.clg.exception.InvalidNameException;
import cfg.clg.exception.StudentNotFoundException;
import cfg.clg.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/save")
    public ResponseData saveStudent(@RequestBody StudentDTO student) {
        ResponseData response = new ResponseData();
        try {
            StudentDTO savedStudent = studentService.saveStudent(student);
            response.setStatus("success");
            response.setMessage("saved successfully");
            response.setData(savedStudent);
        } catch (InvalidNameException | InvalidEmailException e) {
            response.setStatus("failed");
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatus("failed");
            response.setMessage("Internal server error");
        }
        return response;
    }

    @GetMapping("/status")
    public ResponseData getStudentStatus(@RequestParam int sid) {
        ResponseData response = new ResponseData();
        try {
            StudentWithApplicationsDTO data = studentService.getStudentWithApplications(sid);
            response.setStatus("success");
            response.setData(data);
        } catch (StudentNotFoundException e) {
            response.setStatus("failed");
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatus("failed");
            response.setMessage("Internal server error");
        }
        return response;
    }
}
