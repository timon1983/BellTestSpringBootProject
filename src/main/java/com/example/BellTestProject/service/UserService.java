package com.example.BellTestProject.service;

import com.example.BellTestProject.dao.UserDAO;
import com.example.BellTestProject.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Transactional
    public List<User> getAllUsersByOfficeId(int id){
        return userDAO.getByOfficeId(id);
    }

    @Transactional
    public User getById(int id){
        return userDAO.getById(id);
    }

    @Transactional
    public void updateUser(User user){
        userDAO.update(user);
    }

    @Transactional
    public void saveUser(User user){
        userDAO.save(user);
    }
}
