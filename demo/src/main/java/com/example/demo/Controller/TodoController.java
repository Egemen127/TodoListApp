package com.example.demo.Controller;
import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dao.TodoDao;
import com.example.demo.Entity.Todo;


@RestController
@CrossOrigin(origins = "http://127.0.0.1:5173", allowedHeaders = "*")
public class TodoController {
    @Autowired
    TodoDao repo;
    
    @GetMapping("/")
    public List<Todo> home(Authentication auth) {
        System.out.println("current user is: "+auth.getName());
        return repo.findAll().stream().filter(e -> e.getUserName().equals(auth.getName()))
        .collect(Collectors.toList());
    }
    //add a new todo
    @PostMapping("/add")
    public ResponseEntity<?> addTodo(@Valid @RequestBody Todo body,Authentication auth) {
        body.setUserName(auth.getName());
        repo.save(body);
        return new ResponseEntity<>(body,HttpStatus.OK);
    }
    //deletes the todo with given id in the path
    @DeleteMapping("/delete/{id}")
    public String deleteTodo(@PathVariable Integer id) {
        Optional<Todo> t = repo.findAll().stream().filter(e -> e.getId() == id).findFirst();
        if(t.isPresent())
        repo.delete(t.get());
        return "deleted";
    }
    //edits todo with given id in the path
    @PutMapping("/edit/{id}")
    public String editTodo(@Valid @RequestBody Todo t, @PathVariable int id,Authentication auth) {
        if(repo.findById(id).isPresent()){
        t.setId(id);
        t.setUserName(auth.getName());
        repo.save(t);
        } else {
        throw new IllegalArgumentException("Task with id "+ id + " does not exist.");
        }
        return "edited";
    }
    //deletes all todos in the db
    @DeleteMapping("/deleteAll")
    public String deleteAll() {
        repo.deleteAll();
        return "deleted everything";
    }

}
