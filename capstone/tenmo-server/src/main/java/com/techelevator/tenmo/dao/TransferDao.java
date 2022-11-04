package com.techelevator.tenmo.dao;

import java.math.BigDecimal;
import java.security.Principal;

public interface TransferDao {


    boolean executeTransfer(int toAccount, int fromAccount, BigDecimal txfrAmount, Principal principal);

//    String whoAmI(Principal principal);
}