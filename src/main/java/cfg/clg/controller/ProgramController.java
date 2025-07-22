package cfg.clg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cfg.clg.dto.ProgramDto;
import cfg.clg.service.ProgramService;

@RestController
@RequestMapping("/program")
public class ProgramController {

    @Autowired
    private ProgramService programService;

    @PostMapping("/save")
    public ResponseData<ProgramDto> save(@RequestBody ProgramDto dto) {
        ResponseData<ProgramDto> res = new ResponseData<>();
        try {
            res.setStatus("success");
            res.setData(programService.saveProgram(dto));
        } catch (Exception e) {
            res.setStatus("failed");
            res.setMessage(e.getMessage());
        }
        return res;
    }

    @GetMapping("/all")
    public ResponseData<List<ProgramDto>> all() {
        ResponseData<List<ProgramDto>> res = new ResponseData<>();
        try {
            res.setStatus("success");
            res.setData(programService.getAllPrograms());
        } catch (Exception e) {
            res.setStatus("failed");
            res.setMessage(e.getMessage());
        }
        return res;
    }
}
