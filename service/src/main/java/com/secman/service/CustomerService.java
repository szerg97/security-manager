package com.secman.service;

import com.secman.model.Address;
import com.secman.model.Customer;
import com.secman.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public List<Customer> getAll(){
        return this.customerRepository.findAll();
    }

    public List<Customer> getByAddress(Address address){
        return this.customerRepository.findByAddress(address);
    }

    public Customer getOne(Long id){
        Optional<Customer> optional = this.customerRepository.findById(id);
        if (optional.isPresent())
            return optional.get();
        throw new RuntimeException("Could not found");
    }

    public Customer addOne(Customer customer){
        this.customerRepository.save(customer);
        return customer;
    }

    public Customer deleteOneById(Long id){
        Customer customer = getOne(id);
        if (customer != null){
            this.customerRepository.delete(customer);
            return customer;
        }
        else
            throw new RuntimeException("Could not delete");
    }

    public Customer updateOne(Customer pCustomer){
        Customer customer = getOne(pCustomer.getId());
        if (customer != null) {
            customer.copyFrom(pCustomer);
            this.customerRepository.save(customer);
            return customer;
        }
        throw new RuntimeException("Could not update");
    }
}
