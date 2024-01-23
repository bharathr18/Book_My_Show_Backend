package com.example.Book_My_Show_Backend.Models;

import com.example.Book_My_Show_Backend.Enums.SeatType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "show_seats")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ShowSeatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String seatNo;

    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;

    private boolean booked;
    private Date bookedAt;

    @ManyToOne
    @JoinColumn
    private ShowEntity show;

    @ManyToOne
    @JoinColumn
    private TicketEntity ticket;
}
