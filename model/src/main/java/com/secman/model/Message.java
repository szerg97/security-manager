package com.secman.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Entity
@Table(name = "messages")
@Getter
@Setter
@NoArgsConstructor
public class Message extends GSecEntity {

    @Schema(description = "Content of message")
    @NotBlank(message = "error.message.content.not-blank")
    @Size(min = 2, max = 65535, message = "error.message.content.size")
    private String content;
    @Schema(description = "Represents if the sender is the customer or not")
    @NotBlank(message = "error.message.to-customer.not-blank")
    private Boolean toCustomer;
    @Schema(description = "From/to employee")
    @NotBlank(message = "error.message.employee.not-blank")
    @JsonIgnore
    @ManyToOne(targetEntity = Employee.class)
    private Employee employee;
    @Schema(description = "From/to customer")
    @NotBlank(message = "error.message.customer.not-blank")
    @JsonIgnore
    @ManyToOne(targetEntity = Customer.class)
    private Customer customer;

    public Message(String content, Boolean toCustomer, Employee employee, Customer customer) {
        super();
        this.content = content;
        this.toCustomer = toCustomer;
        this.employee = employee;
        this.customer = customer;
    }

    public void copyFrom(Message message) {
        this.content = message.getContent();
        this.toCustomer = message.getToCustomer();
        this.employee = message.getEmployee();
        this.customer = message.getCustomer();
    }
}
