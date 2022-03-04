package com.secman.service;

import com.secman.model.Address;
import com.secman.model.City;
import com.secman.repository.AddressRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;

    public List<Address> getAll(){
        return this.addressRepository.findAll();
    }

    public List<Address> getByCity(City city){
        return this.addressRepository.findByCity(city);
    }

    public Address getOne(Long id){
        Optional<Address> optional = this.addressRepository.findById(id);
        if (optional.isPresent())
            return optional.get();
        throw new RuntimeException("Could not found");
    }

    public Address addOne(Address address){
        this.addressRepository.save(address);
        return address;
    }

    public Address deleteOneById(Long id){
        Address address = getOne(id);
        if (address != null){
            this.addressRepository.delete(address);
            return address;
        }
        else
            throw new RuntimeException("Could not delete");
    }

    public Address updateOne(Address pAddress){
        Address address = getOne(pAddress.getId());
        if (address != null) {
            address.copyFrom(pAddress);
            this.addressRepository.save(address);
            return address;
        }
        throw new RuntimeException("Could not update");
    }
}
