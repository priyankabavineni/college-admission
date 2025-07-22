package cfg.clg.dto;

import lombok.Data;

@Data
public class ApplicationActionDto {
    private String applicationId;
    private int adminId;
    private String action;            // "APPROVE" or "REJECT"
    private String rejectionReason;   // required when action is REJECT
}