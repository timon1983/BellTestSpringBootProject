package com.example.BellTestProject.service;

import com.example.BellTestProject.dao.UserDAO;
import com.example.BellTestProject.dao.impl.UserDAOImpl;
import com.example.BellTestProject.model.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;


class UserServiceTest {

    @Autowired
    private User user;

    @Mock
    UserDAO userDAO = Mockito.mock(UserDAOImpl.class);
    @Mock
    UserService userService = Mockito.mock(UserService.class);


    @Test
    void check_getAllUsersByOfficeId_Should_Return_All_Users_By_OfficeId(){
        List<User> users = new ArrayList<>();
        when(userDAO.getByOfficeId(2)).thenReturn(users);
    }

    @Test
    void check_getById_Should_Return_User_ById(){
        when(userDAO.getById(2)).thenReturn(user);
    }

    @Test
    void check_updateUser_Should_Works(){
        userService.updateUser(user);
        Mockito.verify(userService).updateUser(user);
    }

    @Test
    void check_saveUser_Should_Works(){
        userService.saveUser(user);
        Mockito.verify(userService).saveUser(user);
    }
}