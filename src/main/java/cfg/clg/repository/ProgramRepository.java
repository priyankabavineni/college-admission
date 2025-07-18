package cfg.clg.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cfg.clg.entity.ProgramEntity;

public interface ProgramRepository extends JpaRepository<ProgramEntity, Integer> {

}

