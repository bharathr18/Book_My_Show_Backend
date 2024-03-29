package com.example.Book_My_Show_Backend.Service;

import com.example.Book_My_Show_Backend.Dtos.BookTicketRequestDto;
import com.example.Book_My_Show_Backend.Models.ShowEntity;
import com.example.Book_My_Show_Backend.Models.ShowSeatEntity;
import com.example.Book_My_Show_Backend.Models.TicketEntity;
import com.example.Book_My_Show_Backend.Models.UserEntity;
import com.example.Book_My_Show_Backend.Repository.ShowRepository;
import com.example.Book_My_Show_Backend.Repository.TicketRepository;
import com.example.Book_My_Show_Backend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    ShowRepository showRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TicketRepository ticketRepository;
    public String bookTicket(BookTicketRequestDto bookTicketRequestDto) throws Exception
    {
        //Get the requested seats
        List<String> requestedSeats = bookTicketRequestDto.getRequestSeats();

        ShowEntity showEntity = showRepository.findById(bookTicketRequestDto.getShowId()).get();

        UserEntity userEntity = userRepository.findById(bookTicketRequestDto.getUserId()).get();

        //Get the showSeats from showEntity
        List<ShowSeatEntity> showSeats = showEntity.getListOfSeats();

        //Validate if I can allocate these seats to the requested by user.
        List<ShowSeatEntity> bookedSeats = new ArrayList<>();

        for(ShowSeatEntity showSeat : showSeats)
        {
            String seatNo = showSeat.getSeatNo();

            if(showSeat.isBooked() == false && requestedSeats.contains(seatNo))
            {
                bookedSeats.add(showSeat);
            }
        }

        //FAILURE CASE
        if(bookedSeats.size() != requestedSeats.size())
        {
            //Some of the seats the userRequested are not available
            throw new Exception("Requested seats are not available");
        }

        //SUCCESS CASE
        //This means the bookedSeats actually contains the booked Seats.

        TicketEntity ticketEntity = new TicketEntity();

        double totalAmount = 0;
        double multiplier = showEntity.getMultiplier();

        String allotedSeats = "";

        int rate = 0;

        //Calculating amount,setting bookedStatus, setting
        for(ShowSeatEntity bookedSeat: bookedSeats)
        {
            bookedSeat.setBooked(true);
            bookedSeat.setBookedAt(new Date());
            bookedSeat.setTicket(ticketEntity);
            bookedSeat.setShow(showEntity);

            String seatNo = bookedSeat.getSeatNo();

            allotedSeats = allotedSeats + seatNo + ",";

            if(seatNo.charAt(0) == '1')
                rate = 100;
            else
                rate = 200;

            totalAmount = totalAmount + multiplier * rate;
        }

        ticketEntity.setBooked_at(new Date());
        ticketEntity.setAmount((int)totalAmount);
        ticketEntity.setShow(showEntity);
        ticketEntity.setUser(userEntity);

        ticketEntity.setBookedSeats(bookedSeats);
        ticketEntity.setAlloted_seats(allotedSeats);

        //Bidirectional mapping part is pending

        ticketRepository.save(ticketEntity);

        return "Ticket created successfully";
    }
}
