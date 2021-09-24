package com.example.BellTestProject.dao;

import com.example.BellTestProject.model.User;

import java.util.List;

public interface UserDAO {

    List<User> getByOfficeId(int id);

    User getById(int id);

    User update(User user);

    void save(User user);
}
