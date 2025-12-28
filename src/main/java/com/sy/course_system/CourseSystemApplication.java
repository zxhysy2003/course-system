package com.sy.course_system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.sy.course_system.mapper")
public class CourseSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(CourseSystemApplication.class, args);
	}

}
