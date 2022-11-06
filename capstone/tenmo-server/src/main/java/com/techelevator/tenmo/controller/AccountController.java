package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.security.Principal;
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
    public boolean executeTransfer(@RequestParam int toAccount,
                                @RequestParam int fromAccount,
                                @RequestParam BigDecimal txfrAmount) {
        return this.transferDao.executeTransfer(toAccount, fromAccount, txfrAmount);
    }

    @RequestMapping(path = "/test", method = RequestMethod.POST)
    public boolean testTransfer(@RequestParam int toAccount,
                                   @RequestParam int fromAccount,
                                   @RequestParam BigDecimal txfrAmount) {
        return this.transferDao.testTransfer(toAccount, fromAccount, txfrAmount);
    }


    @RequestMapping(path = "/account/{username}/id", method = RequestMethod.GET)
    public int findAccountIdByUsername(@PathVariable String username){
        return this.accountDao.findAccountIdByUsername(username);
    }

//    @PreAuthorize("isAuthenticated")
    @RequestMapping(path = "/whoami", method = RequestMethod.GET)
    public String whoAmI(Principal principal) {
        return principal.getName();
    }

}
