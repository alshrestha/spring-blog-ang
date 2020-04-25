package com.project.springblogapp.model;

import java.util.List;

public class CustomerResponse {

    private List<Customer> customers;

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
}