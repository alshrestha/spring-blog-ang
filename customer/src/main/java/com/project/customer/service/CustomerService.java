package com.project.customer.service;

import com.project.customer.exception.CustomerNotFoundException;
import com.project.customer.model.Customer;
import com.project.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers () {

        List<Customer> customerList = new ArrayList<>();
        customerRepository.findAll().forEach(customerList::add);
        return customerList;
    }

    public Customer getCustomerById(int id) {
        Customer customer =  customerRepository.findById(id).orElseThrow(()->
                new CustomerNotFoundException("For ID: " + id));
        return customer;
    }

    public Customer findByUserName(String userName){
        return customerRepository.findByUserName(userName);
    }

    public void addCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public Customer updateCustomer(int id, Customer customer){
        if(customerRepository.existsById(customer.getId()))
            return customerRepository.save(customer);
        return null;
    }

    public void deleteCustomerById(int id){
        customerRepository.deleteById(id);

    }

}
