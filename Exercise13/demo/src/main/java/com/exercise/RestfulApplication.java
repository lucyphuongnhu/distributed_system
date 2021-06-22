package com.exercise;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class RestfulApplication {
    public static void main(String[] args) {
        SpringApplication.run(com.exercise.RestfulApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
        return args -> {
            System.out.println("Get data of books");
			// connect to GET the Book
			Book book = restTemplate.getForObject("http://localhost:8080/getbooks", Book.class);
			System.out.println(book);

			// modify the book and POST
			book.getAuthor().setName("Some random author");
			restTemplate.postForObject("http://localhost:8080/addbook", book, Book.class);
        };
    }

}