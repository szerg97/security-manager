package com.secman.application.dto;

import com.secman.model.Security;
import com.secman.model.Transaction;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class TransactionsBySecuritiesMapper {

    public List<TransactionsBySecuritiesDto> fromEntitiesMap(Map<Security, List<Transaction>> map){

        List<TransactionsBySecuritiesDto> list = new ArrayList<>();
        map.forEach((sec, transactions) -> {
            Map<Integer, Integer> notMap = new HashMap<>();
            for (int i = 0; i < 12; i++) {
                notMap.put(i + 1, 0);
            }

            transactions.forEach(t -> {
                notMap.put(t.getInserted().getMonthValue(), notMap.get(t.getInserted().getMonthValue()) + 1);
            });
            list.add(new TransactionsBySecuritiesDto(sec.getName(), notMap));
        });

        return list;
    }
}
