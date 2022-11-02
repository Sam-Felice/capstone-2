//package com.techelevator.tenmo.controller;
//
//import com.techelevator.tenmo.dao.UserDao;
//import com.techelevator.tenmo.model.User;
//import org.springframework.security.core.parameters.P;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//import java.util.List;
//
//@RestController
//@RequestMapping("/tenmo_user")
//public class TenmoController {
//
//
//    private UserDao UserDao;
//
//    public TenmoController(UserDao userDao) {
//        UserDao = userDao;
//    }
//
//    @GetMapping("")
//    public List<User> getListOfUsers() {
//        return this.UserDao.findAll();
//    }
//
//    @GetMapping(path = "/{username}")
//    public User findByUsername(@PathVariable String username) {
//        return this.UserDao.findByUsername(username);
//    }
//
//    @GetMapping(path = "/{username}")
//    public int findIdByUsername(@PathVariable String username) {
//        return this.UserDao.findIdByUsername(username);
//    }
//
//    @PostMapping(path = "")
//    public boolean create(@Valid @RequestBody String username, String password ) {
//        return UserDao.create(username, password);
//    }
//}
