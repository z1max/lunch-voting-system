package com.z1max.lunchvotingsystem.service;

import com.z1max.lunchvotingsystem.model.User;
import com.z1max.lunchvotingsystem.repository.UserRepository;
import com.z1max.lunchvotingsystem.util.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    public User get(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Cannot find user with id = " + id));
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Cannot find user with email = " + email));
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        User updated = userRepository.findById(user.getId()).get();
        updated.setEmail(user.getEmail());
        updated.setPassword(user.getPassword());
        updated.setName(user.getName());
        updated.setRoles(user.getRoles());
        return userRepository.save(updated);
    }

    public void delete(int id) {
        userRepository.deleteById(id);
    }
}
