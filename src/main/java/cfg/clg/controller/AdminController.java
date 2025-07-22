package cfg.clg.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import cfg.clg.dto.*;
import cfg.clg.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired 
    private AdminService adminService;

    // ✅ FIXED: Added @GetMapping here
    @GetMapping("/{adminId}/applications/{status}")
    public ResponseData getByStatus(@PathVariable int adminId, @PathVariable String status) {
        ResponseData resp = new ResponseData();
        try {
            List<ApplicationResponseDto> list = adminService.getApplicationsByStatus(adminId, status);
            resp.setStatus("success");
            resp.setData(list);
        } catch (Exception e) {
            resp.setStatus("failed");
            resp.setMessage(e.getMessage());
        }
        return resp;
    }

    @PostMapping("/action")
    public ResponseData actOnApplication(@RequestBody ApplicationActionDto dto) {
        ResponseData resp = new ResponseData();
        try {
            ApplicationResponseDto updated = adminService.actOnApplication(dto);
            resp.setStatus("success");
            resp.setData(updated);
        } catch (Exception e) {
            resp.setStatus("failed");
            resp.setMessage(e.getMessage());
        }
        return resp;
    }
}
