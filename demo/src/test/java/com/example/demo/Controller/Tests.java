package com.example.demo.Controller;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;

import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.mockito.Mockito.when;
// import org.springframework.boot.test.mock.mockito.MockBean;
import com.example.demo.Dao.TodoDao;
import com.example.demo.Entity.Todo;

@SpringBootTest
public class Tests {
    
    @Autowired
    TodoController f;
    @Autowired
    TodoDao repo;
    @BeforeEach
    @WithMockUser(username = "user")
    void setup() {//adds an item before each test
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Todo todo = new Todo();
        todo.setDay("Monday");
        todo.setLabel("Example");
        todo.setText("Task 1");
        f.addTodo(todo,auth);
    }
    @AfterEach
    @WithMockUser(username = "user")
    void cleanup() {//clears the db after each test
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        f.deleteAll();
        Assertions.assertEquals(0, f.home(auth).size());
    }

    @Test
    @WithMockUser(username = "user")
    void testAddTodo() {//testing add by adding an item and checking if the label field matches with the db
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Todo todo = new Todo();
        todo.setLabel("example");
        todo.setText("do this");
        todo.setDay("Monday");
        f.addTodo(todo,auth);
        assertEquals("example",f.home(auth).get(1).getLabel());
    }

    @Test
    @WithMockUser(username = "user")
    void testDeleteTodo() {//testing delete by adding and deleting todo and checking number of todos in the db
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Todo todo = new Todo();
        todo.setDay("Tuesday");
        todo.setLabel("Example");
        todo.setText("Task 2");
        f.addTodo(todo,auth);
        System.out.println(f.home(auth));
        f.deleteTodo(todo.getId());
        Assertions.assertEquals(1, f.home(auth).size());
    }

    @Test
    @WithMockUser(username = "user")
    void testEditTodo() {//tesing edit by editing a todo and checking if the label field matches in the db
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Todo t = f.home(auth).get(0);
        t.setLabel("edit example");
        f.editTodo(t, t.getId(),auth);
        Assertions.assertEquals("edit example", f.home(auth).get(0).getLabel());
    }

    @Test
    @WithMockUser(username = "user")
    void testHome() {//checking if getting all the items as expected
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        Assertions.assertEquals(1, f.home(auth).size());
    }
}
