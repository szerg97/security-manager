package com.secman.application.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.secman.model.Customer;
import com.secman.model.Employee;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class MessageDto {

    private Long id;
    private String content;
    private Boolean toCustomer;
    private Long customerId;
    private Long employeeId;
    private LocalDateTime inserted;

    public MessageDto() {
    }

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDateTime getInserted() {
        return inserted;
    }

    public void setInserted(LocalDateTime inserted) {
        this.inserted = inserted;
    }
}
