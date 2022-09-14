package com.example.demo.Auth;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.Dao.TodoUserDao;
import com.example.demo.Entity.TodoUser;
@Service
public class MyUserDetailsService implements UserDetailsService{
    @Autowired
    TodoUserDao userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        List<TodoUser> listUser = userRepo.getUserByEmail(username);
        //Optional<TodoUser> user = userRepo.findAll().stream().filter(e -> e.getUsername() == username).findFirst();
        if(listUser.isEmpty()) {
        throw new UsernameNotFoundException("Username not found");
        }
        //System.out.println(user.get().getUsername() + user.get().getPassword());
        
        return new User(listUser.get(0).getUsername(), listUser.get(0).getPassword(), new ArrayList<>());
    }

}
