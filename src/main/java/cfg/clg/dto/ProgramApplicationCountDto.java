package cfg.clg.dto;

import lombok.Data;

@Data
public class ProgramApplicationCountDto {
    private int programId;
    private String programName;
    private long applicationCount;
}

