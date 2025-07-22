package cfg.clg.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ApplicationDto {

	 private String applicationId;
	    private Integer programId;
	    private Integer sid;  
	    private Integer adminId;
	    private String status;    
	    private int entranceexam;
	    private boolean paymentdone;
	    
    
}