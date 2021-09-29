package com.example.BellTestProject.controller;

import com.example.BellTestProject.dto.UserDTO;
import com.example.BellTestProject.exception.NoSuchDataException;
import com.example.BellTestProject.model.Office;
import com.example.BellTestProject.model.User;
import com.example.BellTestProject.service.OfficeService;
import com.example.BellTestProject.service.UserService;
import com.example.BellTestProject.view.ResponseViewData;
import com.example.BellTestProject.view.ResponseViewSuccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    OfficeService officeService;
    private UserService userService;
    private Office office;
    private User user;
    private HttpHeaders headers;
    private ResponseViewSuccess responseViewSuccess;
    private ResponseViewData responseViewData;

    @Autowired
    public UserController(UserService userService, Office office, User user, HttpHeaders headers, ResponseViewSuccess responseViewSuccess,
                          ResponseViewData responseViewData) {
        this.userService = userService;
        this.office = office;
        this.user = user;
        this.headers = headers;
        this.responseViewSuccess = responseViewSuccess;
        this.responseViewData = responseViewData;
    }

    @PostMapping("/list")
    public ResponseEntity<ResponseViewData> getAllUserByOfficeId(@RequestBody UserDTO userDTO){
        office.setId(userDTO.getOfficeId());
        user.setOffice(office);
        List<User> users = userService.getAllUsersByOfficeId(userDTO.getOfficeId());
        if(users.isEmpty()){
            throw new NoSuchDataException("Нет юзеров в офисе с ID = " + userDTO.getOfficeId() + " или нет офиса с таким ID");
        }
        responseViewData.setData(users);
        return new ResponseEntity<>(responseViewData,headers, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<ResponseViewData> saveUser(@RequestBody UserDTO userDTO){

        user.setFirstName(userDTO.getFirstName());
        user.setPosition(userDTO.getPosition());
        user.setPhone(userDTO.getPhone());
        user.setIdentified(userDTO.isIdentified());
        //office.setId(userDTO.getOfficeId());
        user.setOffice(officeService.getOfficeById(userDTO.getOfficeId()));
        userService.saveUser(user);
        responseViewData.setData(responseViewSuccess);
        return new ResponseEntity<>(responseViewData,headers, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseViewData> getOfficeById(@PathVariable("id") int id){
        User user = userService.getById(id);
        if(user == null){
            throw new NoSuchDataException("Нет юзера с ID = " + id);
        }
        responseViewData.setData(user);
        return new ResponseEntity<>(responseViewData, headers, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<ResponseViewData> updateOffice(@RequestBody User user){
        User user1 = userService.getById(user.getId());
        if(user1 == null){
            throw new NoSuchDataException("Нет офиса с ID = " + office.getId());
        }else {
            userService.updateUser(user);
        }
        responseViewData.setData(responseViewSuccess);
        return new ResponseEntity<>(responseViewData,headers, HttpStatus.OK);
    }
}
