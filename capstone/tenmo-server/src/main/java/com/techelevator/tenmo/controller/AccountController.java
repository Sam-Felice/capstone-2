package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.User;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController

public class AccountController {

    private AccountDao accountDao;
    private UserDao userDao;

    public AccountController(AccountDao accountDao, UserDao userDao){
        this.accountDao = accountDao;
        this.userDao = userDao;
    }


    @RequestMapping(path = ("/account"), method = RequestMethod.GET)
    public List<Account> findAllAccounts(){
        return this.accountDao.findAll();
    }

    @RequestMapping(path = ("/user"),method = RequestMethod.GET)
    public List<User> findAllUsers() {
        return this.userDao.findAll();
    }

    @RequestMapping(path = ("/account/{username}/balance"), method = RequestMethod.GET)
    public BigDecimal getBalanceByUsername(@PathVariable String username){
        return this.accountDao.getBalanceByUsername(username);
    }

}
