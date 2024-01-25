package com.example.Book_My_Show_Backend.Service;

import com.example.Book_My_Show_Backend.Dtos.ShowRequestDto;
import com.example.Book_My_Show_Backend.Models.*;
import com.example.Book_My_Show_Backend.Repository.MovieRepository;
import com.example.Book_My_Show_Backend.Repository.ShowRepository;
import com.example.Book_My_Show_Backend.Repository.ShowSeatRepository;
import com.example.Book_My_Show_Backend.Repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowService {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    TheaterRepository theaterRepository;

    @Autowired
    ShowSeatRepository showSeatRepository;

    @Autowired
    ShowRepository showRepository;

    public String addShow(ShowRequestDto showRequestDto)
    {
        ShowEntity showEntity = ShowEntity.builder().showDate(showRequestDto.getShowDate()).showTime(showRequestDto.getShowTime()).multiplier(showRequestDto.getMultiplier()).build();

        MovieEntity movieEntity = movieRepository.findByMovieName(showRequestDto.getMovieName());

        TheaterEntity theaterEntity = theaterRepository.findById(showRequestDto.getTheaterId()).get();


        showEntity.setTheater(theaterEntity);
        showEntity.setMovie(movieEntity);

        movieEntity.getListOfShows().add(showEntity);
        theaterEntity.getListOfShows().add(showEntity);

        List<ShowSeatEntity> seatEntityList = createShowSeats(theaterEntity.getTheaterSeatEntityList());

        showEntity.setListOfSeats(seatEntityList);

        for (ShowSeatEntity showSeat : seatEntityList)
        {
            showSeat.setShow(showEntity);
        }

        movieRepository.save(movieEntity);
        theaterRepository.save(theaterEntity);

        return "Show added successfully";
    }

    public List<ShowSeatEntity> createShowSeats(List<TheaterSeatEntity> theaterSeatEntityList)
    {
        List<ShowSeatEntity> seats = new ArrayList<>();

        for (TheaterSeatEntity theaterSeat : theaterSeatEntityList)
        {
            ShowSeatEntity showSeat = ShowSeatEntity.builder().seatNo(theaterSeat.getSeatNo()).seatType(theaterSeat.getSeatType()).build();
            seats.add(showSeat);
        }

        showSeatRepository.saveAll(seats);

        return seats;
    }
}
