package com.example.Book_My_Show_Backend.Repository;

import com.example.Book_My_Show_Backend.Models.TheaterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheaterRepository extends JpaRepository<TheaterEntity, Integer> {

    TheaterEntity findByNameAndCity(String name, String city);
}
