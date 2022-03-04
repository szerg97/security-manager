package com.secman.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
public class Employee extends GSecEntity {

    @Schema(description = "First name of employee")
    @NotBlank(message = "error.employee.first-name.not-blank")
    @Size(min = 2, max = 128, message = "error.employee.first-name.size")
    private String firstName;
    @Schema(description = "Last name of employee")
    @NotBlank(message = "error.employee.last-name.not-blank")
    @Size(min = 2, max = 128, message = "error.employee.last-name.size")
    private String lastName;
    @Schema(description = "Email address of employee")
    @NotBlank(message = "error.employee.email.not-blank")
    @Size(min = 5, max = 64, message = "error.employee.first-name.size")
    @Email(message = "error.employee.email.email")
    private String email;
    @Schema(description = "Phone number of employee")
    @NotBlank(message = "error.employee.phone.not-blank")
    @Size(min = 2, max = 16, message = "error.employee.phone.size")
    private String phone;

    @JsonIgnore
    @OneToMany(targetEntity = Message.class, mappedBy = "employee")
    private List<Message> messages;

    public Employee(String firstName, String lastName, String email, String phone) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }

    public void copyFrom(Employee employee) {
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
        this.email = employee.getEmail();
        this.phone = employee.getPhone();
    }
}
