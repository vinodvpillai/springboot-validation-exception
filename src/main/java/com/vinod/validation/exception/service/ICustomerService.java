package com.vinod.validation.exception.service;


import com.vinod.validation.exception.model.Customer;

public interface ICustomerService {

    /**
     * Add new customer.
     *
     * @param customer  - Customer object.
     * @return          - Persisted customer object.
     */
    Customer addCustomer(Customer customer);

    /**
     * Get customer object by customer id.
     *
     * @param id    - Customer ID.
     * @return      - Customer object.
     */
    Customer getCustomerById(Long id);

    /**
     * Get customer object by email id.
     *
     * @param emailId    - Customer Email ID.
     * @return      - Customer object.
     */
    Customer getCustomerByEmailId(String emailId);

    /**
     * Update customer object by new value having id as given.
     *
     * @param id        - Customer ID.
     * @param customer  - Customer Object.
     * @return          - Newly persisted customer object.
     */
    Customer updateCustomer(Long id,Customer customer);

    /**
     * Delete customer object having particular id.
     *
     * @param id    - Customer ID.
     */
    void deleteCustomer(Long id);

}
