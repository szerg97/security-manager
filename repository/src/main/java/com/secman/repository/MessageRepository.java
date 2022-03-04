package com.secman.repository;

import com.secman.model.Customer;
import com.secman.model.Employee;
import com.secman.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByEmployee(Employee employee);
    List<Message> findByCustomer(Customer customer);
}
