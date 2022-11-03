package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController

public class AccountController {

    private AccountDao accountDao;
    private UserDao userDao;
    private TransferDao transferDao;

    public AccountController(AccountDao accountDao, UserDao userDao, TransferDao transferDao){
        this.accountDao = accountDao;
        this.userDao = userDao;
        this.transferDao = transferDao;
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

    @RequestMapping(path =  "/transfer", method = RequestMethod.POST)
    public void executeTransfer(@RequestParam int toAccount, @RequestParam BigDecimal txfrAmount) {
//        return this.transferDao.executeTransfer(txfrAmount);

    }


}
