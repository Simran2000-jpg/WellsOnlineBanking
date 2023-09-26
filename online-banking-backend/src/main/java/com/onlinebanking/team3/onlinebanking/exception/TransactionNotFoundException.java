package com.onlinebanking.team3.onlinebanking.exception;

public class TransactionNotFoundException extends RuntimeException {
    public TransactionNotFoundException(String transactionNotFound)  {
        super(transactionNotFound);
    }
}
