package com.example.test1.service.impl;

import com.example.test1.model.User;
import com.example.test1.repository.UserRepository;
import com.example.test1.service.TelegramService;
import com.example.test1.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public void create(User user) {
        userRepository.save(user);


    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User update(Long id, User user) {
        User newUser = userRepository.getReferenceById(id);
        if(Objects.nonNull(user.getFIO())){
            newUser.setFIO(user.getFIO());}
        if(Objects.nonNull(user.getEmail())){
            newUser.setEmail(user.getEmail());}
        if(Objects.nonNull(user.getPhoneNumber())){
            newUser.setPhoneNumber(user.getPhoneNumber());}
        userRepository.save(newUser);
        return newUser;
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
