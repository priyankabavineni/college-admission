

package cfg.clg.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cfg.clg.entity.StudentEntity;

public interface StudentRepository extends JpaRepository<StudentEntity, Integer> {

}