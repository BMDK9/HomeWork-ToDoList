package com.example.homework2_todolist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class HomeWork2ToDoListApplication {

    public static void main(String[] args) {
        SpringApplication.run(HomeWork2ToDoListApplication.class, args);
    }

}
