package com.secman.service;

import com.secman.model.*;
import com.secman.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Currency;

@Service
@AllArgsConstructor
public class SeedService {

    private final CountryRepository countryRepository;
    private final CityRepository cityRepository;
    private final AddressRepository addressRepository;
    private final CustomerRepository customerRepository;
    private final IssuerRepository issuerRepository;
    private final EmployeeRepository            employeeRepository;
    private final FeedbackRepository            feedbackRepository;
    private final MessageRepository             messageRepository;
    private final SecurityRepository categoryRepository;
    private final TransactionRepository transactionRepository;
    private final PortfolioRepository portfolioRepository;

    public void seedCountries(){
        if (!countryRepository.findAll().isEmpty())
            return;

        Country c1 = new Country(
                "Hungary",
                "HU"
        );
        Country c2 = new Country(
                "Slovakia",
                "SV"
        );
        Country c3 = new Country(
                "Croatia",
                "HR"
        );
        countryRepository.saveAll(Arrays.asList(c1, c2, c3));
    }

    public void seedCities(){
        if (!cityRepository.findAll().isEmpty())
            return;

        City c1 = new City(
                "Budapest",
                "1082",
                "Budapest",
                countryRepository.findById(1L).get()
        );
        City c2 = new City(
                "Budapest",
                "1034",
                "Budapest",
                countryRepository.findById(1L).get()
        );
        City c3 = new City(
                "Budapest",
                "1123",
                "Budapest",
                countryRepository.findById(1L).get()
        );
        cityRepository.saveAll(Arrays.asList(c1, c2, c3));
    }
    public void seedAddresses(){
        if (!addressRepository.findAll().isEmpty())
            return;

        Address c1 = new Address(
                "Ady Endre Street 45.",
                "Door 7.",
                cityRepository.findById(1L).get()
        );
        Address c2 = new Address(
                "Bajza Street 125.",
                "Door 23.",
                cityRepository.findById(2L).get()
        );
        Address c3 = new Address(
                "Petőfi Sándor Street 78.",
                "",
                cityRepository.findById(2L).get()
        );
        addressRepository.saveAll(Arrays.asList(c1, c2, c3));
    }
    public void seedCustomers(){
        if (!customerRepository.findAll().isEmpty())
            return;

        Customer c1 = new Customer(
                "Customer",
                "Customer",
                "customer@customer.customer",
                "06305632323",
                "123456ED",
                LocalDate.of(1989, 2, 13),
                addressRepository.findById(1L).get()
        );
        Customer c2 = new Customer(
                "Karoly",
                "Kelemen",
                "karcsibacsi@gmail.com",
                "06705542121",
                "234567LE",
                LocalDate.of(1968, 7, 23),
                addressRepository.findById(2L).get()
        );
        Customer c3 = new Customer(
                "Csaba",
                "Kovacs",
                "csabi78@gmail.com",
                "06305632323",
                "789456KM",
                LocalDate.of(1978, 11, 2),
                addressRepository.findById(3L).get()
        );
        customerRepository.saveAll(Arrays.asList(c1, c2, c3));
    }
    public void seedPortfolios(){
        if (!portfolioRepository.findAll().isEmpty())
            return;

        Portfolio p1 = new Portfolio(
                1500000.0,
                customerRepository.findById(1L).get()
        );
        Portfolio p2 = new Portfolio(
                1800000.0,
                customerRepository.findById(2L).get()
        );
        Portfolio p3 = new Portfolio(
                2500000.0,
                customerRepository.findById(3L).get()
        );
        portfolioRepository.saveAll(Arrays.asList(p1, p2, p3));
    }
    public void seedIssuers(){
        if (!issuerRepository.findAll().isEmpty())
            return;

        Issuer c1 = new Issuer(
                "Erste Bank",
                "issuer@issuer.issuer",
                "0612456969",
                true,
                addressRepository.findById(1L).get()
        );
        Issuer c2 = new Issuer(
                "OTP Bank",
                "info@otpbank.hu",
                "0615554545",
                true,
                addressRepository.findById(2L).get()
        );
        Issuer c3 = new Issuer(
                "MKB Bank",
                "info@mkbbank.hu",
                "06953342323",
                true,
                addressRepository.findById(3L).get()
        );
        issuerRepository.saveAll(Arrays.asList(c1, c2, c3));
    }
    public void seedEmployees(){
        if (!employeeRepository.findAll().isEmpty())
            return;

        Employee c1 = new Employee(
                "Admin",
                "Admin",
                "admin@admin.admin",
                "06305562323"
        );
        Employee c2 = new Employee(
                "Bela",
                "Banan",
                "bela@bela.bela",
                "06701234567"
        );
        Employee c3 = new Employee(
                "Cecilia",
                "Citrom",
                "cecilia@cecilia.cecilia",
                "06701423659"
        );
        employeeRepository.saveAll(Arrays.asList(c1, c2, c3));
    }
    public void seedMessages(){
        if (!messageRepository.findAll().isEmpty())
            return;
        Message m1 = new Message(
                "Message 1 from customer.",
                false,
                this.employeeRepository.getById(1L),
                this.customerRepository.getById(1L)
        );
        Message m2 = new Message(
                "Message 2 from customer.",
                false,
                this.employeeRepository.getById(1L),
                this.customerRepository.getById(1L)
        );
        Message m3 = new Message(
                "Message 3 to customer.",
                true,
                this.employeeRepository.getById(1L),
                this.customerRepository.getById(1L)
        );
        Message m4 = new Message(
                "Message 4 to customer.",
                true,
                this.employeeRepository.getById(1L),
                this.customerRepository.getById(1L)
        );

        messageRepository.saveAll(Arrays.asList(m1, m2));
    }
    public void seedFeedbacks(){}
    public void seedCategories(){
        if (!categoryRepository.findAll().isEmpty())
            return;

        Security c1 = new Security(
                "Egyéves Magyar Állampapír 2023/I",
                "Az 1MÁP egy olyan állampapír, amely...",
                Currency.getInstance("HUF"),
                1000.0,
                0.0021,
                0.0001,
                true,
                1.0
        );
        Security c2 = new Security(
                "Magyar Állampapír Plusz",
                "A MÁPP egy olyan állampapír, amely...",
                Currency.getInstance("HUF"),
                5000.0,
                0.0033,
                0.0001,
                true,
                2.0
        );
        Security c3 = new Security(
                "Prémium Magyar Állampapír 2021/J",
                "A PMÁP egy olyan állampapír, amely...",
                Currency.getInstance("HUF"),
                10000.0,
                0.0075,
                0.0001,
                false,
                5.0
        );
        categoryRepository.saveAll(Arrays.asList(c1, c2, c3));
    }
    public void seedTransactions(){
        if (!transactionRepository.findAll().isEmpty())
            return;

        Transaction c1 = new Transaction(
                categoryRepository.findById(1L).get(),
                issuerRepository.findById(1L).get(),
                portfolioRepository.findById(1L).get(),
                55000.0
        );
        Transaction c2 = new Transaction(
                categoryRepository.findById(2L).get(),
                issuerRepository.findById(2L).get(),
                portfolioRepository.findById(2L).get(),
                35000.0
        );
        Transaction c3 = new Transaction(
                categoryRepository.findById(3L).get(),
                issuerRepository.findById(3L).get(),
                portfolioRepository.findById(3L).get(),
                75000.0
        );
        transactionRepository.saveAll(Arrays.asList(c1, c2, c3));
    }

}
