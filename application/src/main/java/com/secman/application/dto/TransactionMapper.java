package com.secman.application.dto;

import com.secman.model.Portfolio;
import com.secman.model.Transaction;
import com.secman.service.IssuerService;
import com.secman.service.SecurityService;
import com.secman.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransactionMapper {

    private final SecurityService securityService;
    private final IssuerService issuerService;

    public Transaction toEntity(TransactionDto from, Portfolio portfolio){
            return new Transaction(
                    securityService.getOne(from.getSecurityId()),
                    issuerService.getOne(1L),
                    portfolio,
                    from.getDenomination()
                    );
    }

    public TransactionDto fromEntity(Transaction from){
        return new TransactionDto(
                from.getId(),
                from.getDenomination(),
                from.getGrossValue(),
                from.getNetValue(),
                from.getYield(),
                from.getReferenceYield(),
                from.getSecurity().getId(),
                from.getIssuer().getId(),
                from.getPortfolio().getId(),
                from.getInserted()
        );
    }
}
