package org.eustache.management_systeme;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@OpenAPIDefinition(
        info = @Info(
                title = "Job Application Management API",
                version = "1.0",
                description = "API for managing job applications, including CRUD operations and status tracking."
        )
)
@SpringBootApplication
public class JobApplicationManagement {

    public static void main(String[] args) {
        SpringApplication.run(JobApplicationManagement.class, args);
    }

}
