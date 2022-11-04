package com.techelevator.tenmo.dao;

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
    public boolean executeTransfer(int toAccount, int fromAccount, BigDecimal txfrAmount, Principal principal) {

        String currentUser = principal.getName();

        String sqlFromAccBalance = "SELECT account.balance FROM account\n" +
                "JOIN transfers ON account.account_id = transfers.from_account\n" +
                "WHERE from_account = ?;";
        BigDecimal fromBalnce = jdbcTemplate.queryForObject(sqlFromAccBalance, BigDecimal.class, fromAccount);
        Double tempFromBalance = fromBalnce.doubleValue();
        Double tempTransfer = txfrAmount.doubleValue();
        if (toAccount == fromAccount || tempTransfer <= 0 || tempTransfer <= tempFromBalance) {
            return false;
        } else {

            String sql = "INSERT INTO transfer (from_account, to_account, transfer_amount)\n" +
                    "VALUES (?,?,?);";
            String sql2 = "UPDATE account SET account_id = ?, user_id = ?, balance = ?";
            String sql3 = "UPDATE account SET account_id = ?, user_id = ?, balance = ?";
            String sqlaccountId = "SELECT account.account_id from account\n" +
                    "JOIN tenmo_user ON account.user_id = tenmo_user.user_id\n" +
                    "WHERE tenmo_user.username = ?;";



        }
        return true;
    }
    //LEFT OFF at trying to figure out if we needed int fromAccount in executeTransfer method
}
