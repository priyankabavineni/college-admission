package cfg.clg.dto;

import lombok.Data;

@Data
public class ProgramDto {
    private int programId;
    private String program;
    private String department;
    private Integer availableSeats;
}
