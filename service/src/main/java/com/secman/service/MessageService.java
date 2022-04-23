package com.secman.service;

import com.secman.model.Customer;
import com.secman.model.Employee;
import com.secman.model.Message;
import com.secman.repository.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;

    public List<Message> getAll(){
        return this.messageRepository.findAll();
    }

    public List<Message> getByCustomer(String email){
        return this.messageRepository.findByCustomerEmail(email);
    }

    public List<Message> getByEmployee(Employee employee){
        return this.messageRepository.findByEmployee(employee);
    }

    public Message getOne(Long id){
        Optional<Message> optional = this.messageRepository.findById(id);
        if (optional.isPresent())
            return optional.get();
        throw new RuntimeException("Could not found");
    }

    public Message addOne(Message message){
        this.messageRepository.save(message);
        return message;
    }

    public Message deleteOneById(Long id){
        Message message = getOne(id);
        if (message != null){
            this.messageRepository.delete(message);
            return message;
        }
        else
            throw new RuntimeException("Could not delete");
    }

    public Message updateOne(Message pMessage){
        Message message = getOne(pMessage.getId());
        if (message != null) {
            message.copyFrom(pMessage);
            this.messageRepository.save(message);
            return message;
        }
        throw new RuntimeException("Could not update");
    }
}
