/*package cfg.clg.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
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

    @ManyToOne
    @JoinColumn(name = "sid", referencedColumnName = "sid")
    @JsonBackReference
    private StudentEntity student;

    @ManyToOne
    @JoinColumn(name = "program_id", referencedColumnName = "programId")
    @JsonBackReference
    private ProgramEntity program;

    private String status;

    @ManyToOne
    @JoinColumn(name = "admin_id", referencedColumnName = "adminId")
    @JsonBackReference
    private AdminEntity admin;

    @Column(name = "payment_done")
    private boolean paymentDone;

    private String rejectionReason;

    private Integer entranceExamRank;  // used in AdminService for rank check
}*/

package cfg.clg.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(schema = "collegeadmissionsystem", name = "application")
public class ApplicationEntity {

	@Id
	@Column(name = "application_id")
	private String applicationId;

	@ManyToOne
	@JoinColumn(name = "sid", referencedColumnName = "sid")
	@JsonBackReference
	private StudentEntity student;

	@ManyToOne
	@JoinColumn(name = "program_id", referencedColumnName = "programId")
	@JsonBackReference
	private ProgramEntity program;

	@ManyToOne
	@JoinColumn(name = "admin_id", referencedColumnName = "adminId")
	@JsonBackReference
	private AdminEntity admin;

	@Column(name = "status")
	private String status;

	@Column(name = "payment_done")
	private boolean paymentDone;

	@Column(name = "entrance_exam_rank", unique = true)
	private Integer entranceExamRank; // Unique column, but not a foreign key

	private String rejectionReason;

}
