package cfg.clg.entity;

import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(schema = "collegeadmissionsystem", name = "collegeadmin")
public class AdminEntity {

	@Id
	private int adminId;

	private String status;
	@Column
	private String adminname;
	@Column(name = "rejection_reason")
	private String rejectionReason;

	@Column(name = "entrance_exam_rank")
	private Integer entranceExamRank;

	
}
