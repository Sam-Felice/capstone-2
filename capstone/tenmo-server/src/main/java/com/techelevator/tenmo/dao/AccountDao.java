package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;

import java.math.BigDecimal;
import java.util.List;

public interface AccountDao {

    Account findAccountByUsername(String username);

    List<Account> findAll();

    BigDecimal getBalanceByUsername(String username);

}
