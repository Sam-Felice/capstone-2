package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTransferDao implements TransferDao {
    private JdbcTemplate jdbcTemplate;

    public JdbcTransferDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }



    @Override
    public boolean executeTransfer(int toAccount, int fromAccount, BigDecimal txfrAmount) {
         String sqlFromAccBalance = "SELECT account.balance FROM account\n" +
//                "JOIN transfers ON account.account_id = transfers.from_account\n" +
                "WHERE account.account_id = ?;";
        BigDecimal fromAccountBalance = jdbcTemplate.queryForObject(sqlFromAccBalance, BigDecimal.class, fromAccount);

        //Get the account balance of the toAccount using the toAccount TESTED
        String sqlToAccBalance = "SELECT account.balance FROM account\n" +
//                "JOIN transfers ON account.account_id = transfers.from_account\n" +
                "WHERE account.account_id = ?;";
        BigDecimal toAccountBalance = jdbcTemplate.queryForObject(sqlToAccBalance, BigDecimal.class, toAccount);

        //Changes BigDecimal values to doubles to be used in comparison statements
        Double tempFromBalance = fromAccountBalance.doubleValue();
        Double tempTransfer = txfrAmount.doubleValue();
        if (toAccount == fromAccount || tempTransfer <= 0 || tempFromBalance <= tempTransfer) {
            return false;
        } else {
            //Inserts new transfer into the transfer table
            String sql = "INSERT INTO transfers (to_account, from_account, transfer_amount)\n" +
                    "VALUES (?,?,?) RETURNING transfer_id;";

            int transferId = jdbcTemplate.queryForObject(sql, Integer.class, toAccount, fromAccount, txfrAmount);

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

    @Override
    public List<Transfer> findTransferByAccountId(int id) {
        List<Transfer> transfers = new ArrayList<>();
        String sql = "SELECT transfer_id, from_Account, to_Account, transfer_amount FROM transfers\n" +
                "JOIN account on account.account_id = transfers.from_account\n" +
                "JOIN tenmo_user on tenmo_user.user_id = account.user_id\n" +
                "WHERE transfers.from_account = ? OR transfers.to_account = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id, id);
        while (results.next()) {
            transfers.add(mapRowToTransfers(results));
        }
        return transfers;
    }

    private Transfer mapRowToTransfers(SqlRowSet rs) {
        Transfer transfers = new Transfer();
        transfers.setTxfrId(rs.getInt("transfer_id"));
        transfers.setFromAccount(rs.getInt("from_account"));
        transfers.setToAccount(rs.getInt("to_account"));
        transfers.setTxfrAmount(rs.getBigDecimal("transfer_amount"));
        return transfers;
    }

}
