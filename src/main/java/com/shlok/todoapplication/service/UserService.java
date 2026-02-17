package com.shlok.todoapplication.service;

import com.shlok.todoapplication.model.User;
import com.shlok.todoapplication.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user){
        return userRepository.save(user);
    }

    public User findUser(Long userId){
        return userRepository.findById(userId).get();
    }

    public void deleteUser(Long userId){
        userRepository.deleteById(userId);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
}
