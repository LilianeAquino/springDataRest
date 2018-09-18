package com.vogella.spring.data.rest;

import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.vogella.spring.data.rest.repository.TodoRepository;

@SpringBootApplication
public class Application {
	public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner jpaSample(TodoRepository todoRepo)  {
        return (args) -> {

            todoRepo.save(new Todo("Jpa Sample"));

            Todo todo = new Todo("Usando data.rest");
            todo.setDueDate(new Date());
            todo.setDescription("Descrição data.rest");
            todoRepo.save(todo);
            
            RestTemplate restTemplate = new RestTemplate();
            Todo firstTodo = restTemplate.getForObject("http://localhost:8080/tasks/1", Todo.class);
            Todo secondTodo = restTemplate.getForObject("http://localhost:8080/tasks/2", Todo.class);

            System.out.println(firstTodo);
            System.out.println(secondTodo);
            
            Todo newTodo = new Todo("New Todo");
            newTodo.setDescription("Todo added by rest API");
            newTodo.setDone(true);

            ResponseEntity<Todo> postForEntity = restTemplate.postForEntity("http://localhost:8080/tasks/", newTodo, Todo.class);
            System.out.println(postForEntity);
        };
    }

}
