package com.users.application;

import com.users.application.components.Address;
import com.users.application.components.Role;
import com.users.application.components.User;
import com.users.application.components.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.LocalDateTime;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class UsersApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsersApplication.class, args);
    }

    /**
     * Kafka test message
     */
//    @Bean
//    CommandLineRunner commandLineRunner(KafkaTemplate<String, String> kafkaTemplate) {
//        return args -> {
//            kafkaTemplate.send("users", "user 1 was created");
//        };
//    }
    /**
     * MongoDb test insert
     */
//    @Bean
//    CommandLineRunner commandLineRunner(UserRepository userRepository) {
//        return args -> {
//            User user = new User(
//                    "Dmitriy",
//                    "Shevchenko",
//                    "tykto@gmail.com",
//                    Role.DEFAULT,
//                    new Address(
//                            "Astana",
//                            "Republic av 01"
//                    ),
//                    LocalDateTime.now()
//            );
//            userRepository.insert(user);
//            try {
//                System.out.println(userRepository.findUserByEmail("tykto@gmail.com"));
//            } catch (Exception e) {
//                throw new Exception(e.getMessage());
//            }
//        };
//    }
}