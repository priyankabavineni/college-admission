package cfg.clg.repository;

import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cfg.clg.dto.ApplicationResponseDto;
import cfg.clg.entity.ApplicationEntity;

@Repository
public interface ApplicationRepository extends JpaRepository<ApplicationEntity, String> {

    List<ApplicationEntity> findByStudentSid(int studentId);

    List<ApplicationEntity> findByStatus(String status);

    @Query("SELECT a.program, COUNT(a) FROM ApplicationEntity a GROUP BY a.program")
    List<Object[]> countApplicationsGroupedByProgram();

    List<ApplicationEntity> findByAdminAdminIdAndStatus(int adminId, String status);

	List<ApplicationEntity> findByAdmin_AdminIdAndStatusIgnoreCase(int adminId, String status);
}
