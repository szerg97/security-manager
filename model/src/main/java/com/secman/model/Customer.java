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
import javax.validation.constraints.Email;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
public class Customer extends GSecEntity {

    @Schema(description = "First name of customer")
    @NotBlank(message = "error.customer.first-name.not-blank")
    @Size(min = 2, max = 128, message = "error.customer.first-name.size")
    private String firstName;
    @Schema(description = "Last name of customer")
    @NotBlank(message = "error.customer.last-name.not-blank")
    @Size(min = 2, max = 128, message = "error.customer.last-name.size")
    private String lastName;
    @Schema(description = "Email of customer")
    @NotBlank(message = "error.customer.email.not-blank")
    @Size(min = 2, max = 64, message = "error.customer.email.size")
    @Email(message = "error.customer.email.email")
    private String email;
    @Schema(description = "Phone number of customer")
    @NotBlank(message = "error.customer.phone.not-blank")
    @Size(min = 2, max = 16, message = "error.customer.phone.size")
    private String phone;
    @Schema(description = "ID card number of customer")
    @NotBlank(message = "error.customer.id-card.not-blank")
    private String idCard;
    @Schema(description = "Date of birth of customer")
    @NotBlank(message = "error.customer.date-of-birth.not-blank")
    @Past(message = "error.customer.date-of-birth.past")
    private LocalDate dateOfBirth;
    @Schema(description = "Address of customer")
    @NotBlank(message = "error.customer.address.not-blank")
    @JsonIgnore
    @ManyToOne(targetEntity = Address.class)
    private Address address;

    @JsonIgnore
    @OneToMany(targetEntity = Message.class, mappedBy = "customer")
    private List<Message> messages;
    @JsonIgnore
    @OneToMany(targetEntity = Feedback.class, mappedBy = "customer")
    private List<Feedback> feedbacks;
    @JsonIgnore
    @OneToMany(targetEntity = Security.class, mappedBy = "customer")
    private List<Security> securities;

    public Customer(String firstName, String lastName, String email, String phone, String idCard, LocalDate dateOfBirth, Address address) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.idCard = idCard;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
    }

    public void copyFrom(Customer customer) {
        this.firstName = customer.getFirstName();
        this.lastName = customer.getLastName();
        this.email = customer.getEmail();
        this.phone = customer.getPhone();
        this.idCard = customer.getIdCard();
        this.dateOfBirth = customer.getDateOfBirth();
        this.address = customer.getAddress();
    }
}
