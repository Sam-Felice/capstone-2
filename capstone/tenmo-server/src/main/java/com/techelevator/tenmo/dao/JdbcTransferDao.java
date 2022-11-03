package com.techelevator.tenmo.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class JdbcTransferDao implements TransferDao {
    private JdbcTemplate jdbcTemplate;

    public JdbcTransferDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }



    @Override
    public void executeTransfer(int toAccount, int fromAccount, BigDecimal txfrAmount) {
        String sql = "INSERT INTO transfer (from_account, to_account, transfer_amount)\n" +
                "VALUES (?,?,?);";
        String sql2 = "UPDATE account SET account_id = ?, user_id = ?, balance = ?";
        String sql3 = "UPDATE account SET account_id = ?, user_id = ?, balance = ?";


    }
}
