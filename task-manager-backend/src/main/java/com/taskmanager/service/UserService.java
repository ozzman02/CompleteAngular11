package com.taskmanager.service;

import com.taskmanager.entity.User;

public interface UserService {

    User findByUsername(String username);
}
