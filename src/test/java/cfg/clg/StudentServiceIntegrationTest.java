package cfg.clg;

import cfg.clg.dto.StudentDTO;
import cfg.clg.dto.StudentWithApplicationsDTO;
import cfg.clg.entity.ApplicationEntity;
import cfg.clg.exception.InvalidEmailException;
import cfg.clg.exception.InvalidNameException;
import cfg.clg.exception.StudentNotFoundException;
import cfg.clg.service.StudentService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

@SpringBootTest
public class StudentServiceIntegrationTest {

    @Autowired
    private StudentService studentService;

    @Test
    public void testSaveStudent_Success() {
        StudentDTO student = new StudentDTO();
        student.setSid(100);  // use unique id or let DB generate
        student.setSname("Valid Student");  // length >= 10 chars
        student.setEmail("valid@gmail.com");

        StudentDTO savedStudent = studentService.saveStudent(student);

        assertNotNull(savedStudent);
        assertEquals("valid@gmail.com", savedStudent.getEmail());
        assertEquals("Valid Student", savedStudent.getSname());
    }

    @Test
    public void testSaveStudent_InvalidName() {
        StudentDTO student = new StudentDTO();
        student.setSid(101);
        student.setSname("Short"); // less than 10 chars
        student.setEmail("valid@gmail.com");

        assertThrows(InvalidNameException.class, () -> {
            studentService.saveStudent(student);
        });
    }

    @Test
    public void testSaveStudent_InvalidEmail() {
        StudentDTO student = new StudentDTO();
        student.setSid(102);
        student.setSname("Valid Student");
        student.setEmail("invalidemail@yahoo.com"); // invalid domain

        assertThrows(InvalidEmailException.class, () -> {
            studentService.saveStudent(student);
        });
    }

    @Test
    public void testGetStudentById_Success() {
        StudentDTO student = new StudentDTO();
        student.setSid(103);
        student.setSname("Another Student");
        student.setEmail("another@gmail.com");
        studentService.saveStudent(student);

        StudentDTO found = studentService.getStudentById(103);

        assertNotNull(found);
        assertEquals("Another Student", found.getSname());
        assertEquals("another@gmail.com", found.getEmail());
    }

    @Test
    public void testGetStudentById_NotFound() {
        int nonExistentId = 999999;
        assertThrows(StudentNotFoundException.class, () -> {
            studentService.getStudentById(nonExistentId);
        });
    }

    @Test
    public void testGetStudentWithApplications() {
        // Save a student first
        StudentDTO student = new StudentDTO();
        student.setSid(104);
        student.setSname("Student With Apps");
        student.setEmail("apps@gmail.com");
        studentService.saveStudent(student);

        // Assuming your ApplicationRepository and ApplicationEntity
        // have data or you insert test data here.
        // This test mainly verifies that the service method runs without errors.

        StudentWithApplicationsDTO dto = studentService.getStudentWithApplications(104);

        assertNotNull(dto);
        assertNotNull(dto.getStudent());
        assertEquals(104, dto.getStudent().getSid());

        List<ApplicationEntity> applications = dto.getApplications();
        assertNotNull(applications);
        // You can assert more here if you have test applications inserted
    }
}
