// save to database
package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration // conf file
public class StudentConfig {

    // bean to access student repo
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student ingeborga = new Student( // remove id it will be generated
                    "Ingeborga",
                    "gibo.p@xxx.com",
                    LocalDate.of(1999, Month.NOVEMBER, 1));

            Student eduardo = new Student(
                    "Eduardo",
                    "ad.p@xxx.com",
                    LocalDate.of(1989, Month.DECEMBER, 23));

            repository.saveAll(List.of(ingeborga, eduardo)); // save to database
        };
    }
}