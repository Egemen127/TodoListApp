package com.example.demo.Dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.TodoUser;

@Repository
public interface TodoUserDao extends JpaRepository<TodoUser,String>{
    @Query(value = "SELECT todo_user " +
            "FROM TodoUser todo_user " +
            "WHERE todo_user.username=:email")
    List<TodoUser> getUserByEmail(@Param("email") String email);
}
