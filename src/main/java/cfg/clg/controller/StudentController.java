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
    private StudentService svc;

    @PostMapping("/save")
    public ResponseData<StudentDTO> save(@RequestBody StudentDTO dto) {
        ResponseData<StudentDTO> res = new ResponseData<>();
        try {
            res.setStatus("success");
            res.setData(svc.saveStudent(dto));
        } catch (Exception e) {
            res.setStatus("failed");
            res.setMessage(e.getMessage());
        }
        return res;
    }

    @GetMapping("/applications")
    public ResponseData<StudentWithApplicationsDTO> apps(@RequestParam int sid) {
        ResponseData<StudentWithApplicationsDTO> res = new ResponseData<>();
        try {
            res.setStatus("success");
            res.setData(svc.getStudentWithApplications(sid));
        } catch (Exception e) {
            res.setStatus("failed");
            res.setMessage(e.getMessage());
        }
        return res;
    }

}