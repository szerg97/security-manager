package com.secman.service;

import com.secman.model.*;
import com.secman.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;

@Service
@AllArgsConstructor
public class SeedService {

    private final CountryRepository countryRepository;
    private final CityRepository cityRepository;
    private final AddressRepository addressRepository;
    private final CustomerRepository customerRepository;
    private final DistributorRepository distributorRepository;
    private final EmployeeRepository            employeeRepository;
    private final FeedbackRepository            feedbackRepository;
    private final MessageRepository             messageRepository;
    private final SecurityCategoryRepository    categoryRepository;
    private final SecurityRepository            securityRepository;

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
                "Peter",
                "Horvath",
                "petihorvath@gmail.com",
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
    public void seedDistributors(){
        if (!distributorRepository.findAll().isEmpty())
            return;

        Distributor c1 = new Distributor(
                "Erste Bank",
                "distributor@distributor.distributor",
                "0612456969",
                true,
                addressRepository.findById(1L).get()
        );
        Distributor c2 = new Distributor(
                "OTP Bank",
                "info@otpbank.hu",
                "0615554545",
                true,
                addressRepository.findById(1L).get()
        );
        Distributor c3 = new Distributor(
                "MKB Bank",
                "info@mkbbank.hu",
                "06953342323",
                true,
                addressRepository.findById(1L).get()
        );
        distributorRepository.saveAll(Arrays.asList(c1, c2, c3));
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
    public void seedMessages(){}
    public void seedFeedbacks(){}
    public void seedCategories(){
        if (!categoryRepository.findAll().isEmpty())
            return;

        SecurityCategory c1 = new SecurityCategory(
                "Magyar Állampapír",
                "A MAP egy olyan állampapír, amely..."
        );
        SecurityCategory c2 = new SecurityCategory(
                "Magyar Állampapír Plusz",
                "A MAP Plusz egy olyan állampapír, amely..."
        );
        SecurityCategory c3 = new SecurityCategory(
                "Prémium Babakötvény",
                "A Prémium Babakötvény egy olyan állampapír, amely..."
        );
        categoryRepository.saveAll(Arrays.asList(c1, c2, c3));
    }
    public void seedSecurities(){
        if (!securityRepository.findAll().isEmpty())
            return;

        Security c1 = new Security(
                55000.0,
                55000.0,
                55000.0,
                55000.0,
                5.0,
                10.5,
                10.5,
                true,
                7.0,
                7.0,
                categoryRepository.findById(1L).get(),
                distributorRepository.findById(1L).get(),
                customerRepository.findById(1L).get()
        );
        Security c2 = new Security(
                35000.0,
                35000.0,
                35000.0,
                35000.0,
                5.0,
                10.5,
                10.5,
                false,
                7.0,
                7.0,
                categoryRepository.findById(2L).get(),
                distributorRepository.findById(2L).get(),
                customerRepository.findById(2L).get()
        );
        Security c3 = new Security(
                75000.0,
                75000.0,
                75000.0,
                75000.0,
                3.0,
                10.5,
                10.5,
                true,
                10.0,
                10.0,
                categoryRepository.findById(3L).get(),
                distributorRepository.findById(3L).get(),
                customerRepository.findById(3L).get()
        );
        securityRepository.saveAll(Arrays.asList(c1, c2, c3));
    }

}
