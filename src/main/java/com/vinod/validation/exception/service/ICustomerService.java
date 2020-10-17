package com.vinod.validation.exception.service;


import com.vinod.validation.exception.exception.CustomerAlreadyExistException;
import com.vinod.validation.exception.exception.CustomerNotFoundException;
import com.vinod.validation.exception.model.Customer;

public interface ICustomerService {

    /**
     * Add new customer.
     *
     * @param customer  - Customer object.
     * @return          - Persisted customer object.
     */
    Customer addCustomer(Customer customer) throws CustomerAlreadyExistException;

    /**
     * Get customer object by customer id.
     *
     * @param id    - Customer ID.
     * @return      - Customer object.
     */
    Customer getCustomerById(Long id) throws CustomerNotFoundException;

    /**
     * Get customer object by email id.
     *
     * @param emailId    - Customer Email ID.
     * @return      - Customer object.
     */
    Customer getCustomerByEmailId(String emailId) throws CustomerNotFoundException;

    /**
     * Update customer object by new value having id as given.
     *
     * @param id        - Customer ID.
     * @param customer  - Customer Object.
     * @return          - Newly persisted customer object.
     */
    Customer updateCustomer(Long id,Customer customer) throws CustomerNotFoundException;

    /**
     * Delete customer object having particular id.
     *
     * @param id    - Customer ID.
     */
    void deleteCustomer(Long id) throws CustomerNotFoundException;

}
