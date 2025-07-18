package cfg.clg.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(schema = "collegeadmissionsystem", name = "application")
public class ApplicationEntity {
	@Id
	private String applicationId;

	// Many applications belong to one student
	@ManyToOne
	@JoinColumn(name = "sid", referencedColumnName = "sid")
	@JsonBackReference
	private StudentEntity student;

	// Many applications belong to one program
	@ManyToOne
	@JoinColumn(name = "program_id", referencedColumnName = "programId")
	@JsonBackReference
	private ProgramEntity program;

	private String status;

	// Many applications may be reviewed by one admin
	@ManyToOne
	@JoinColumn(name = "admin_id", referencedColumnName = "adminId")
	@JsonBackReference
	private AdminEntity admin;
}