package com.techelevator.tenmo.dao;

import java.math.BigDecimal;

public interface TransferDao {


    void executeTransfer(int toAccount, int fromAccount, BigDecimal txfrAmount);


}
