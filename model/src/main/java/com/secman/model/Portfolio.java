package com.secman.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "portfolios")
@Getter
@Setter
@NoArgsConstructor
public class Portfolio extends GSecEntity{

    private Double balance;
    @OneToOne(targetEntity = Customer.class)
    private Customer customer;

    @OneToMany(targetEntity = Transaction.class, mappedBy = "portfolio")
    private List<Transaction> transactions;

    public Portfolio(Double balance, Customer customer) {
        this.balance = balance;
        this.customer = customer;
    }

    public void copyFrom(Portfolio portfolio) {
        this.balance = portfolio.getBalance();
        this.customer = portfolio.getCustomer();
    }
}
