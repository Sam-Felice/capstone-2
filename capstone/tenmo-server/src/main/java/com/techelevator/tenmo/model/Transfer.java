package com.techelevator.tenmo.model;

import com.techelevator.tenmo.dao.TransferDao;

import java.math.BigDecimal;

public class Transfer implements TransferDao {
    private int txfrId;
    private int fromAccount;
    private int toAccount;
    private BigDecimal txfrAmount;

    public Transfer(int txfrId, int fromAccount, int toAccount, BigDecimal txfrAmount) {
        this.txfrId = txfrId;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.txfrAmount = txfrAmount;
    }

    public int getTxfrId() {
        return txfrId;
    }

    public void setTxfrId(int txfrId) {
        this.txfrId = txfrId;
    }

    public int getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(int fromAccount) {
        this.fromAccount = fromAccount;
    }

    public int getToAccount() {
        return toAccount;
    }

    public void setToAccount(int toAccount) {
        this.toAccount = toAccount;
    }

    public BigDecimal getTxfrAmount() {
        return txfrAmount;
    }

    public void setTxfrAmount(BigDecimal txfrAmount) {
        this.txfrAmount = txfrAmount;
    }



    @Override
    public void executeTransfer(int toAccount, int fromAccount, BigDecimal txfrAmount) {

    }
}
