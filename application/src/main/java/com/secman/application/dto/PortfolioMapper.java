package com.secman.application.dto;

import com.secman.model.Portfolio;
import com.secman.model.Transaction;
import com.secman.service.CustomerService;
import com.secman.service.IssuerService;
import com.secman.service.SecurityService;
import com.secman.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PortfolioMapper {

    private final TransactionMapper trMapper;

    public Portfolio toEntity(PortfolioDto from){
        return new Portfolio(
        );
    }

    public PortfolioDto fromEntity(Portfolio from){
        Double balance = from.getBalance();
        Double netValSum = from.getTransactions().stream().mapToDouble(Transaction::getNetValue).sum();
        Double denomSum = from.getTransactions().stream().mapToDouble(Transaction::getDenomination).sum();
        return new PortfolioDto(
                balance,
                netValSum,
                denomSum,
                from.getCustomer().getFirstName() + " " + from.getCustomer().getLastName(),
                from.getCustomer().getEmail(),
                from.getTransactions().stream().map(t -> trMapper.fromEntityExtended(t)).collect(Collectors.toList()));
    }
}
