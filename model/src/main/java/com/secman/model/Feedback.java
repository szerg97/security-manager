package com.secman.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Entity
@Table(name = "feedbacks")
@Getter
@Setter
@NoArgsConstructor
public class Feedback extends GSecEntity {

    @Schema(description = "Rate of feedback")
    @NotBlank(message = "error.feedback.rate.not-blank")
    @Min(message = "error.feedback.rate.min", value = 1)
    @Max(message = "error.feedback.rate.max", value = 5)
    private Integer rate;
    @Schema(description = "Content of feedback")
    @Size(min = 2, max = 65535, message = "error.feedback.content.size")
    private String content;
    @Schema(description = "Customer who posted the feedback")
    @NotBlank(message = "error.feedback.customer.not-blank")
    @JsonIgnore
    @ManyToOne(targetEntity = Customer.class)
    private Customer customer;

    public Feedback(Integer rate, String content, Customer customer) {
        super();
        this.rate = rate;
        this.content = content;
        this.customer = customer;
    }

    public void copyFrom(Feedback feedback) {
        this.rate = feedback.getRate();
        this.content = feedback.getContent();
        this.customer = feedback.getCustomer();
    }
}
