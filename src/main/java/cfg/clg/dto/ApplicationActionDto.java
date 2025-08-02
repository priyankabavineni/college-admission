package cfg.clg.dto;

import lombok.Data;

@Data
public class ApplicationActionDto {
    private int applicationId;
    private int adminId;
    private String action;            // "APPROVE" or "REJECT"
    private String rejectionReason;   // required when action is REJECT
}