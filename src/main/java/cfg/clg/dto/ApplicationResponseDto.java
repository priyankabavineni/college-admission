package cfg.clg.dto;

import lombok.Data;

@Data
public class ApplicationResponseDto {
    private String applicationId;
    private int sid;
    private int programId;
    private boolean paymentDone;
    private int entranceExamRank;
    private String status;
    private String rejectionReason;
}
