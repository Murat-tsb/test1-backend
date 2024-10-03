package com.example.test1.service;


import com.example.test1.model.User;

import java.util.List;

public interface UserService {
    void create (User user);
    List<User> findAll();
    User update (Long id, User user);
    void delete (Long id);


}
