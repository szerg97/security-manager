package com.secman.application.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class TransactionsBySecuritiesDto {

    String securityName;
    Map<Integer, Integer> numberOfTransactions;

    public TransactionsBySecuritiesDto(String securityName, Map<Integer, Integer> numberOfTransactions) {
        this.securityName = securityName;
        this.numberOfTransactions = numberOfTransactions;
    }

    public String getSecurityName() {
        return securityName;
    }

    public void setSecurityName(String securityName) {
        this.securityName = securityName;
    }

    public Map<Integer, Integer> getNumberOfTransactions() {
        return numberOfTransactions;
    }

    public void setNumberOfTransactions(Map<Integer, Integer> numberOfTransactions) {
        this.numberOfTransactions = numberOfTransactions;
    }
}
