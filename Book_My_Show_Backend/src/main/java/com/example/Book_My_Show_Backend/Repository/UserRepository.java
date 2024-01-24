package com.example.Book_My_Show_Backend.Repository;

import com.example.Book_My_Show_Backend.Models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Integer> {
}
