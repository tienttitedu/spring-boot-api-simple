package org.assignment.estr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TienTTAssignmentApplication {
	public static void main(String[] args) {
		SpringApplication.run(TienTTAssignmentApplication.class, args);
	}

}
