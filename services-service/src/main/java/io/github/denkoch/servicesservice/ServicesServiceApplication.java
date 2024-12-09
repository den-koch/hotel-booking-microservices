package io.github.denkoch.servicesservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "io.github.denkoch.servicesservice.repository")
public class ServicesServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServicesServiceApplication.class, args);
    }

}
