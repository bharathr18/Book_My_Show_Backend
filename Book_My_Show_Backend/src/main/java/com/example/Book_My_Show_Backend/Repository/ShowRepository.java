package com.example.Book_My_Show_Backend.Repository;

import com.example.Book_My_Show_Backend.Models.ShowEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowRepository extends JpaRepository<ShowEntity, Integer> {
}
