package com.secman.application.test;

import com.secman.model.Customer;
import com.secman.repository.CustomerRepository;
import com.secman.service.CustomerService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.verify;

public class CustomerServiceTest {

    @Mock
    private CustomerRepository repository;
    private CustomerService service;
    private AutoCloseable autoCloseable;

    @BeforeEach
    void setUp(){
        autoCloseable = MockitoAnnotations.openMocks(this);
        service = new CustomerService(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllCustomers(){
        service.getAll();
        verify(repository).findAll();
    }

    @Test
    void canAddCustomer(){
        Customer c = new Customer();
        service.addOne(c);
        verify(repository).save(c);
    }
}
