package cfg.clg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cfg.clg.entity.ApplicationEntity;

public interface ApplicationRepository extends JpaRepository<ApplicationEntity, String> {

	List<ApplicationEntity> findByStudentSid(int sid);

}