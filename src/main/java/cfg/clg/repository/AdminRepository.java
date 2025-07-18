package cfg.clg.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cfg.clg.entity.AdminEntity;

public interface AdminRepository extends JpaRepository<AdminEntity, Integer>{

}

