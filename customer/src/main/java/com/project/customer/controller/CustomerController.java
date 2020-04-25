package com.project.customer.controller;

import com.project.customer.model.Customer;
import com.project.customer.model.CustomerResponse;
import com.project.customer.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public CustomerResponse getCustomers (){
        CustomerResponse response = new CustomerResponse();
        response.setCustomers(customerService.getAllCustomers());
        return response;
    }

    @GetMapping("/customers/{id}")
    public Customer getCustomer(@PathVariable int id) {
        return customerService.getCustomerById(id);
    }

    @PostMapping("/customer")
    public void saveCustomer(@RequestBody Customer customer){
        customerService.addCustomer(customer);
    }

    @GetMapping("/customer/{userName}")
    public Customer getCustomerByUsername(@PathVariable String userName){
        return customerService.findByUserName(userName);
    }

    @PutMapping("/customer/{id}")
    public void updateCustomer(@PathVariable int id, @RequestBody Customer customer){
        customerService.updateCustomer(id, customer);
    }

    @DeleteMapping("/customer/{id}")
    public void deleteCustomer(@PathVariable int id){
        customerService.deleteCustomerById(id);
    }

}
