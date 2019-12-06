package com.shun.service;

import com.shun.entity.User;

public interface UserService {
    User findByUsername(String username);

    void save(User user);

    User login(User user);

    Boolean register(User user);
}
