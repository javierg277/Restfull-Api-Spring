package org.example.service;

import org.example.exception.RecordNotFoundException;
import org.example.model.User;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository repo;
    public List<User> getAllUsers(){
        List<User> users = repo.findAll();
        return users;
    }
    public User getUserById(int id){
        Optional<User> user = repo.findById(id);
        if(user.isPresent()){
            return user.get();
        } else {
            throw new RecordNotFoundException("User not found with id:"+ id);
        }
    }

    public User createorUpdateUser(User user){
        User end;
        if (user.getId() != -1){
           Optional<User> result = repo.findById(user.getId());
            if(result.isPresent()){
                User user2 = result.get();
                user2.setName(user.getName());
                user2.setAge(user.getAge());
                end=repo.save(user);
            }else{
                throw new RecordNotFoundException("User not found with id:"+ user.getId());
            }
        }else{
            user = repo.save(user);
        }
        return user;
    }
    public void deleteUser(int id){
        Optional<User> user = repo.findById(id);
        if(user.isPresent()){
            repo.delete(user.get());
        }else{
            throw new RecordNotFoundException("User not found with id:"+ id);
        }
    }
}
