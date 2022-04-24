package com.secman.application.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.secman.model.Customer;
import com.secman.model.Employee;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class MessageDto {

    private String content;
    private Boolean toCustomer;

    public MessageDto(String content, Boolean toCustomer) {
        this.content = content;
        this.toCustomer = toCustomer;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getToCustomer() {
        return toCustomer;
    }

    public void setToCustomer(Boolean toCustomer) {
        this.toCustomer = toCustomer;
    }
}
