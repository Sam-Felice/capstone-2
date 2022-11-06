package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.security.Principal;

@Component
public class JdbcTransferDao implements TransferDao {
    private JdbcTemplate jdbcTemplate;

    public JdbcTransferDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public boolean testTransfer(int toAccount, int fromAccount, BigDecimal txfrAmount) {
        //Get the account balance of the fromAccount using the fromAccountId
        String sqlFromAccBalance = "SELECT account.balance FROM account\n" +
                "JOIN transfers ON account.account_id = transfers.from_account\n" +
                "WHERE from_account = ?;";
        BigDecimal fromAccountBalance = jdbcTemplate.queryForObject(sqlFromAccBalance, BigDecimal.class, fromAccount);

        Double tempFromBalance = fromAccountBalance.doubleValue();
        if (tempFromBalance ==  1000) {
            return true;
        }


        return false;
    }

    @Override
    public boolean executeTransfer(int toAccount, int fromAccount, BigDecimal txfrAmount) {
        //Get current Username
//        String currentUser = principal.getName();

//        //Get the accountId of the fromAccount with a username
//        String sqlaccountId = "SELECT account.account_id from account\n" +
//                "JOIN tenmo_user ON account.user_id = tenmo_user.user_id\n" +
//                "WHERE tenmo_user.username = ?;";
//        Integer fromAccountId = jdbcTemplate.queryForObject(sqlaccountId, Integer.class, currentUser);

        //Get the account balance of the fromAccount using the fromAccountId
        String sqlFromAccBalance = "SELECT account.balance FROM account\n" +
                "JOIN transfers ON account.account_id = transfers.from_account\n" +
                "WHERE from_account = ?;";
        BigDecimal fromAccountBalance = jdbcTemplate.queryForObject(sqlFromAccBalance, BigDecimal.class, fromAccount);

        //Get the account balance of the toAccount using the toAccount
        String sqlToAccBalance = "SELECT account.balance FROM account\n" +
                "JOIN transfers ON account.account_id = transfers.from_account\n" +
                "WHERE to_account = ?;";
        BigDecimal toAccountBalance = jdbcTemplate.queryForObject(sqlToAccBalance, BigDecimal.class, toAccount);

        //Changes BigDecimal values to doubles to be used in comparison statements
        Double tempFromBalance = fromAccountBalance.doubleValue();
        Double tempTransfer = txfrAmount.doubleValue();
        if (toAccount == fromAccount || tempTransfer <= 0 || tempTransfer <= tempFromBalance) {
            return false;
        } else {
            //Inserts new transfer into the transfer table
            String sql = "INSERT INTO transfer (from_account, to_account, transfer_amount)\n" +
                    "VALUES (?,?,?) RETURNING transfer_id;";
            int transferId = jdbcTemplate.update (sql, int.class, fromAccount, toAccount, txfrAmount);

            //updates the fromAccount balance
            String sql2 = "UPDATE account SET balance = balance - ?\n" +
                    "WHERE account_id = ?;";
            jdbcTemplate.update(sql2, txfrAmount, fromAccount);

            //updates the toAccount balance
            String sql3 = "UPDATE account SET balance = balance + ?\n" +
                    "WHERE account_id = ?;";
            jdbcTemplate.update(sql3, txfrAmount, toAccount);


        }
        return true;
    }
    //LEFT OFF need to comment out each method one by one to see if we can find the error 400 Can't deserialize int
}
