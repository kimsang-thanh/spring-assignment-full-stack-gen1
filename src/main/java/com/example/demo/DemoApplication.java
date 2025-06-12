package com.example.demo;

import com.example.demo.Student;
import com.example.demo.StudentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	private final StudentService studentService;

	public DemoApplication(StudentService studentService) {
		this.studentService = studentService;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) {
		System.out.println("=== All Students ===");
		studentService.selectAll().forEach(System.out::println);

		System.out.println("\n=== Insert a new student ===");
		studentService.insert(new Student(11, "Kevin", "Male", 77.0));
		studentService.selectAll().forEach(System.out::println);

		System.out.println("\n=== Select by ID 3 ===");
		System.out.println(studentService.selectById(3));

		System.out.println("\n=== Update ID 5 ===");
		boolean updated = studentService.updateById(5, new Student(5, "Ethan Updated", "Male", 80.0));
		System.out.println("Updated: " + updated);

		System.out.println("\n=== Delete ID 4 ===");
		boolean deleted = studentService.deleteById(4);
		System.out.println("Deleted: " + deleted);

		System.out.println("\n=== Final Student List ===");
		studentService.selectAll().forEach(System.out::println);
	}
}
