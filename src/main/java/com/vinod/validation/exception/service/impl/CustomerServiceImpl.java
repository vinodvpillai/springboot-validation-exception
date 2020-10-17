package com.vinod.validation.exception.service.impl;

import com.vinod.validation.exception.model.Customer;
import com.vinod.validation.exception.repository.CustomerRepository;
import com.vinod.validation.exception.service.ICustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    /**
     * Add new customer.
     *
     * @param customer  - Customer object.
     * @return          - Persisted customer object.
     */
    @Override
    public Customer addCustomer(Customer customer) {
        log.trace("Request came to add new customer : {}",customer);
        customer.setStatus(true);
        Customer persistedCustomer=customerRepository.save(customer);
        log.trace("Successfully saved customer object and persisted object: {}",persistedCustomer);
        return persistedCustomer;
    }

    /**
     * Get customer object by customer id.
     *
     * @param id    - Customer ID.
     * @return      - Customer object.
     */
    @Override
    public Customer getCustomerById(Long id) {
        log.trace("Request came to fetch the customer having customer id : {}",id);
        Optional<Customer> optionalCustomer=customerRepository.findById(id);
        if(optionalCustomer.isPresent()){
            Customer customer=optionalCustomer.get();
            log.trace("Successfully fetched customer object : {} having customer id: {}",customer,id);
            return customer;
        }
        return null;
    }

    /**
     * Get customer object by email id.
     *
     * @param emailId    - Customer Email ID.
     * @return      - Customer object.
     */
    @Override
    public Customer getCustomerByEmailId(String emailId) {
        log.trace("Request came to fetch the customer having customer email id : {}",emailId);
        Optional<Customer> optionalCustomer=customerRepository.findByEmailId(emailId);
        if(optionalCustomer.isPresent()){
            Customer customer=optionalCustomer.get();
            log.trace("Successfully fetched customer object : {} having customer email id: {}",customer,emailId);
            return customer;
        }
        return null;
    }

    /**
     * Update customer object by new value having id as given.
     *
     * @param id        - Customer ID.
     * @param customer  - Customer Object.
     * @return          - Newly persisted customer object.
     */
    @Override
    public Customer updateCustomer(Long id, Customer customer) {
        log.trace("Request came to update new customer id: {}, with new value: {} ",id,customer);
        Optional<Customer> optionalCustomer=customerRepository.findById(id);
        if(optionalCustomer.isPresent()) {
            customer.setId(optionalCustomer.get().getId());
            Customer persistedCustomer=customerRepository.save(customer);
            log.trace("Successfully updated customer object and persisted object: {}",persistedCustomer);
            return persistedCustomer;
        }
        return null;
    }

    /**
     * Delete customer object having particular id.
     *
     * @param id    - Customer ID.
     */
    @Override
    public void deleteCustomer(Long id) {
        log.trace("Request came to delete customer id: {}",id);
        if(customerRepository.findById(id).isPresent()) {
            customerRepository.deleteById(id);
            log.trace("Successfully deleted the customer object id: {}",id);
        }
        log.warn("Unable to find the customer object id: {}",id);
    }
}
