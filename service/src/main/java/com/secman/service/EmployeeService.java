package com.secman.service;

import com.secman.model.Employee;
import com.secman.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public List<Employee> getAll(){
        return this.employeeRepository.findAll();
    }

    public Employee getOne(Long id){
        Optional<Employee> optional = this.employeeRepository.findById(id);
        if (optional.isPresent())
            return optional.get();
        throw new RuntimeException("Could not found");
    }

    public Employee addOne(Employee employee){
        this.employeeRepository.save(employee);
        return employee;
    }

    public Employee deleteOneById(Long id){
        Employee employee = getOne(id);
        if (employee != null){
            this.employeeRepository.delete(employee);
            return employee;
        }
        else
            throw new RuntimeException("Could not delete");
    }

    public Employee updateOne(Employee pEmployee){
        Employee employee = getOne(pEmployee.getId());
        if (employee != null) {
            employee.copyFrom(pEmployee);
            this.employeeRepository.save(employee);
            return employee;
        }
        throw new RuntimeException("Could not update");
    }
}
