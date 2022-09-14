package com.example.demo.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Todo;

@Repository
public interface TodoDao extends JpaRepository<Todo,Integer>{
    
}
