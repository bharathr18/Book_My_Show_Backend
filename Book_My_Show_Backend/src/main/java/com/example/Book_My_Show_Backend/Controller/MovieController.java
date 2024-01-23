package com.example.Book_My_Show_Backend.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/movie")
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping("/add")
    public String addMovie(@RequestBody MovieRequestDto movieRequestDto)
    {
        return movieService.addMovie(movieRequestDto);
    }
}
