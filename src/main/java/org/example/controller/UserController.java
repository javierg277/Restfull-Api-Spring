package org.example.controller;

import org.example.model.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.service.UserService;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService service;
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = service.getAllUsers();
        return ResponseEntity.ok().body(users);
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(int id){
        User user = service.getUserById(id);
        return ResponseEntity.ok().body(user);
    }
    @PostMapping
    public ResponseEntity<User> createorUpdateUser(@RequestBody User user){
        User end = service.createorUpdateUser(user);
        return ResponseEntity.ok(end);
    }
    public void deleteuser(@PathVariable("id")int id){
        service.deleteUser(id);
    }
}
