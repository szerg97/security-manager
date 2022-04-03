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

    private Double money;
    @OneToOne(targetEntity = Customer.class)
    private Customer customer;

    @OneToMany(targetEntity = Transaction.class, mappedBy = "portfolio")
    private List<Transaction> securities;

    public Portfolio(Double money, Customer customer) {
        this.money = money;
        this.customer = customer;
    }

    public void copyFrom(Portfolio portfolio) {
        this.money = portfolio.getMoney();
        this.customer = portfolio.getCustomer();
    }
}
