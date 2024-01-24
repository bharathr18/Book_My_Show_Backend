package com.example.Book_My_Show_Backend.Service;

import com.example.Book_My_Show_Backend.Dtos.MovieRequestDto;
import com.example.Book_My_Show_Backend.Models.MovieEntity;
import com.example.Book_My_Show_Backend.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public String addMovie(MovieRequestDto movieRequestDto)
    {
        MovieEntity movieEntity = MovieEntity.builder().movieName(movieRequestDto.getName()).releaseDate(movieRequestDto.getReleaseDate()).duration(movieRequestDto.getDuration()).build();

        try {
            movieRepository.save(movieEntity);
        }
        catch (Exception e)
        {
            return "Movie could not be added";
        }
        return "Movie added successfully";
    }
}
