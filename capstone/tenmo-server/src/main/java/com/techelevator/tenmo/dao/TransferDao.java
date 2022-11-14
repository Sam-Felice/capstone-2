package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

import java.math.BigDecimal;
import java.util.List;

public interface TransferDao {

//    boolean testTransfer(int toAccount, int fromAccount, BigDecimal txfrAmount);


    boolean executeTransfer(int toAccount, int fromAccount, BigDecimal txfrAmount);

    List<Transfer> findTransferByAccountId(int id);

//    String whoAmI(Principal principal);
}