package com.techelevator.tenmo.dao;

import java.math.BigDecimal;
import java.security.Principal;

public interface TransferDao {

    boolean testTransfer(int toAccount, int fromAccount, BigDecimal txfrAmount);


    boolean executeTransfer(int toAccount, int fromAccount, BigDecimal txfrAmount);

//    String whoAmI(Principal principal);
}