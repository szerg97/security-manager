package com.secman.service;

import com.secman.model.*;
import com.secman.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Currency;
import java.util.Random;

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
    private final OpeningHoursRepository openingHoursRepository;
    private final Random rnd = new Random();

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

        for (int i = 1; i < 101; i++) {
            Address a = new Address(
                    "Test Street " + i,
                    "Door " + (i + 100),
                    cityRepository.findById(1L).get()
            );
            addressRepository.save(a);
        }

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
        customerRepository.save(c1);

        for (int i = 1; i < 101; i++) {
            Customer c = new Customer(
                    "Customer" + i,
                    "Customer" + i,
                    "customer" + i + "@customer" + i + ".customer" + i,
                    "0630563" + i,
                    "123" + i + "ED",
                    LocalDate.of((int)Math.floor(Math.random()*(2001-1960+1)+1960), 1, 30),
                    addressRepository.findById(Long.valueOf(i)).get()
            );
            customerRepository.save(c);
        }
    }
    public void seedPortfolios(){
        if (!portfolioRepository.findAll().isEmpty())
            return;

        for (int i = 1; i < 101; i++) {
            Portfolio p1 = new Portfolio(
                    Math.floor(Math.random() * (10000000 - 1500000 + 1) + 1500000),
                    customerRepository.findById(Long.valueOf(i)).get()
            );
            portfolioRepository.save(p1);
        }
    }
    public void seedOpeningHours(){
        if (!openingHoursRepository.findAll().isEmpty())
            return;

        OpeningHours h1 = new OpeningHours(
                "08:00-17:30",
                "10:00-16:00",
                null
        );
        OpeningHours h2 = new OpeningHours(
                "08:00-17:30",
                "10:00-17:30",
                null
        );
        OpeningHours h3 = new OpeningHours(
                "08:00-17:30",
                "08:00-16:00",
                "08:00-11:00"
        );
        openingHoursRepository.saveAll(Arrays.asList(h1, h2, h3));
    }
    public void seedIssuers(){
        if (!issuerRepository.findAll().isEmpty())
            return;

        Issuer c1 = new Issuer(
                "Erste Bank",
                "issuer@issuer.issuer",
                "0612456969",
                true,
                addressRepository.findById(1L).get(),
                openingHoursRepository.findById(1L).get()
        );
        issuerRepository.save(c1);

        for (int i = 1; i < 50; i++) {
            Issuer c = new Issuer(
                    "Test Issuer" + i,
                    "issuer" + i + "@issuer" + i + ".issuer" + i,
                    "06124569" + i,
                    true,
                    addressRepository.findById(Long.valueOf(i)).get(),
                    openingHoursRepository.findById(2L).get()
            );
            issuerRepository.save(c);
        }
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

        messageRepository.saveAll(Arrays.asList(m1, m2, m3, m4));
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
                0.0004,
                0.0312,
                true,
                5.0,
                LocalDateTime.of(2023, 10, 20, 23, 59),
                1.0
        );
        Security c2 = new Security(
                "Féléves Magyar Állampapír 2026/J",
                "A FMÁP egy olyan állampapír, amely...",
                Currency.getInstance("HUF"),
                5000.0,
                0.0002,
                0.0259,
                true,
                2.0,
                LocalDateTime.of(2026, 10, 20, 23, 59),
                2.0
        );
        Security c3 = new Security(
                "Prémium Magyar Állampapír 2024/J",
                "A PMÁP egy olyan állampapír, amely...",
                Currency.getInstance("HUF"),
                10000.0,
                0.0003,
                0.0235,
                false,
                3.0,
                LocalDateTime.of(2024, 10, 20, 23, 59),
                1.0
        );
        categoryRepository.saveAll(Arrays.asList(c1, c2, c3));
    }
    public void seedTransactions(){
        if (!transactionRepository.findAll().isEmpty())
            return;

        for (int i = 1; i < 1001; i++) {
            Transaction c1 = new Transaction(
                    categoryRepository.findById((long) rnd.nextInt(3 - 1 + 1) + 1).get(),
                    issuerRepository.findById((long) rnd.nextInt(50 - 1 + 1) + 1).get(),
                    portfolioRepository.findById((long) rnd.nextInt(100 - 1 + 1) + 1).get(),
                    Math.floor(Math.random() * (10 - 1 + 1) + 1) * 1000
            );
            c1.setInserted(LocalDateTime.of(2022, (int)Math.floor(Math.random()*(12-1+1)+1), 16, 17, 0));
            transactionRepository.save(c1);
        }
    }

}
