package com.dailycodebuffer.spring.data.jpa.tutorial.repository;

import com.dailycodebuffer.spring.data.jpa.tutorial.entity.Guardian;
import com.dailycodebuffer.spring.data.jpa.tutorial.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // this will persist the data after tests
//@DataJpaTest this is for testing the JpaRepository by storing data and then flushing it
class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void saveStudent(){
        Student student = Student.builder()
                .emailId("shabbir@gmail.com")
                .firstName("Shabbir")
                .lastName("Dawoodi")
//                .guardianName("Nikhil")
//                .guardianEmail("nikhil@gmail.com")
//                .guardianMobile("0123456789")
                .build();
        studentRepository.save(student);
    }

    @Test
    public void saveStudentWithGuardian(){
        Guardian guardian = Guardian.builder()
                .name("Nikhil")
                .email("nikhil@gmail.com")
                .mobile("0123456789")
                .build();

        Student student = Student.builder()
                .firstName("Shivam")
                .lastName("Kumar")
                .emailId("shivam@gmail.com")
                .guardian(guardian)
                .build();

        studentRepository.save(student);
    }

    @Test
    public void printAllStudents(){
        List<Student> studentList = studentRepository.findAll();
        System.out.println("studentList= "+studentList);
    }

    @Test
    public void printStudentByFirstName(){
        List<Student> students = studentRepository.findByFirstName("shivam");
        System.out.println("students= "+students);
    }

    @Test
    public void printStudentByFirstNameContaining(){
        List<Student> students = studentRepository.findByFirstNameContaining("sh");
        System.out.println("students= "+students);
    }

    @Test
    public void printStudentBasedOnGuardianName(){
        List<Student> students = studentRepository.findByGuardianName("nikhil");
        System.out.println("students= "+students);
    }

    @Test
    public void printStudentByEmailAddress(){
        Student student = studentRepository.getStudentByEmailAddress("shabbir@gmail.com");
        System.out.println("student= "+student);
    }

    @Test
    public void printStudentFirstNameByEmailAddress(){
        String firstName = studentRepository.getStudentFirstNameByEmailAddress("shivam@gmail.com");
        System.out.println("student= "+firstName);
    }

    @Test
    public void printStudentByEmailAddressNative(){
        Student student = studentRepository.getStudentByEmailAddressNative("shabbir@gmail.com");
        System.out.println("student= "+student);
    }

    @Test
    public void printStudentByEmailAddressNativeNamedParam(){
        Student student = studentRepository.getStudentByEmailAddressNativeNamedParam("shivam@gmail.com");
        System.out.println("student= "+student);
    }

    @Test
    public void updateStudentNameByEmailIdTest(){
        studentRepository.updateStudentNameByEmailId("shabbir dawoodi","shabbir@gmail.com");
    }
}