package com.crudbasic.student.database;

import com.crudbasic.student.models.Student;
import com.crudbasic.student.repositories.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class database {
    private static final Logger logger = LoggerFactory.getLogger(database.class);
    @Bean
    CommandLineRunner initDatabase(StudentRepository stuRepository) {

        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                Student studentA = new Student("Nhat","Ha Noi","CNTT",3.0);
                Student studentB = new Student("Minh","Ha Noi","KTPM",3.0);
                logger.info("Insert data: " + stuRepository.save(studentA));
                logger.info("Insert data: " + stuRepository.save(studentB));
            }
        };
    }
}
