package com.secman.application.test;

import com.secman.model.Customer;
import com.secman.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void expectedToReturnCustomerByEmail(){
        //given
        Customer c = new Customer(
                "Test1",
                "Test1",
                "test1@test1.test1",
                "0612345678",
                "123456XY",
                true,
                LocalDate.of(1997,1,1),
                null
        );

        customerRepository.save(c);

        Customer byEmail = customerRepository.findByEmail(c.getEmail());

        assertThat(byEmail).isNotNull();
    }

    @Test
    void expectedToReturnCustomersByAddress(){
        //given
        Customer c = new Customer(
                "Test2",
                "Test2",
                "test2@test2.test2",
                "0612345678",
                "123456XY",
                true,
                LocalDate.of(1997,1,1),
                null
        );

        customerRepository.save(c);
        //when
        List<Customer> byAddress = customerRepository.findByAddress(c.getAddress());
        //then
        assertThat(byAddress).isNotNull();
    }
}
