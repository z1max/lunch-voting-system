package com.z1max.lunchvotingsystem.service;

import com.z1max.lunchvotingsystem.model.User;

import java.util.List;

public interface UserService {
    User get(int id);

    User getByEmail(String email);

    List<User> getAll();

    User create(User user);

    User update(User user);

    void delete(int id);
}
