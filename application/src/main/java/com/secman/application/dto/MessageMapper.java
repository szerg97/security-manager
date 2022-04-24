package com.secman.application.dto;

import com.secman.model.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MessageMapper {

    public Message toEntity(MessageDto from, Employee employee, Customer customer){
        return new Message(
                from.getContent(),
                false,
                employee,
                customer
        );
    }
}
