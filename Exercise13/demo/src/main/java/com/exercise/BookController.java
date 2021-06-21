package com.exercise;

import com.example.demo.Greeting;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {


    @GetMapping("/getbooks")
    public Greeting greeting(@RequestParam(value = "title") String title,
                             @RequestParam(value = "publisher") String publisher)) {
        return new Book();
    }
}
