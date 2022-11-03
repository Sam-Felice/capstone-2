package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcAccountDao implements AccountDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcAccountDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Account findAccountByUsername(String username) {
        String sql = "SELECT account_id, balance, username, account.user_id FROM account\n" +
                "JOIN tenmo_user ON tenmo_user.user_id = account.user_id\n" +
                "WHERE username = ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, username);
        if (rowSet.next()){
            return mapRowToAccounts(rowSet);
        }
        throw new UsernameNotFoundException("User: " + username + " was not found.");
    }

    @Override
    public List<Account> findAll() {
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT * FROM account;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()){
            accounts.add(mapRowToAccounts(results));
            //Account account = mapRowToAccounts(results);
            //account.add(accounts);
        }

        return accounts;
    }

    private Account mapRowToAccounts(SqlRowSet rs) {
        Account accounts = new Account();
        accounts.setAccountId(rs.getInt("account_id"));
        accounts.setUserId(rs.getInt("user_id"));
        accounts.setBalance(rs.getBigDecimal("balance"));
        return accounts;
    }

}
