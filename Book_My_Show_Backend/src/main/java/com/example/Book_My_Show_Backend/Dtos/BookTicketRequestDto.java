package com.example.Book_My_Show_Backend.Dtos;

import lombok.Data;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Data
public class BookTicketRequestDto {

    private List<String> requestSeats;
    private int showId;
    private int userId;
}
