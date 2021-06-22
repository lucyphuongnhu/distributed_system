package com.exercise;

import com.example.demo.Greeting;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    @GetMapping("/getbooks")
    public Greeting greeting(@RequestParam(value = "title", defaultValue = "Hello world!") String title, @RequestParam(value = "name", defaultValue = "Nhu") String name) {
        Author author = new Author(name, 21);
        Book book = new Book("Hihi", "VGU", author);
        return book;
    }

    @PostMapping("/addbook")
    public void addbook(@RequestBody Book book)
    {
        System.out.println("Successfully adding book");
        System.out.println(book);
    }

}
