package com.example.Book_My_Show_Backend.Models;

//import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "theater")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class TheaterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String city;
    private String address;

    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL)
    private List<ShowEntity> listOfShows;

    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL)
    private List<TheaterSeatEntity> theaterSeatEntityList;
}
