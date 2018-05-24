package com.z1max.lunchvotingsystem.service;

import com.z1max.lunchvotingsystem.model.User;
import com.z1max.lunchvotingsystem.repository.UserRepository;
import com.z1max.lunchvotingsystem.util.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository repository;

    public User get(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cannot find user with id = " + id));
    }

    @Override
    public User getByEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Cannot find user with email = " + email));
    }

    public List<User> getAll() {
        return repository.findAll();
    }

    @Override
    public User create(User user) {
        return repository.save(user);
    }

    @Override
    public User update(User user) {
        User updated = repository.findById(user.getId()).get();
        updated.setEmail(user.getEmail());
        updated.setPassword(user.getPassword());
        updated.setName(user.getName());
        updated.setRoles(user.getRoles());
        return repository.save(updated);
    }

    public void delete(int id) {
        repository.deleteById(id);
    }
}
