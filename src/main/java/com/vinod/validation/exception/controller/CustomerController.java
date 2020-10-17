package com.vinod.validation.exception.controller;

import com.vinod.validation.exception.model.Customer;
import com.vinod.validation.exception.service.ICustomerService;
import com.vinod.validation.exception.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.vinod.validation.exception.util.GlobalUtility.buildResponseForError;
import static com.vinod.validation.exception.util.GlobalUtility.buildResponseForSuccess;


@RestController
@RequestMapping("/customers")
@Slf4j
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @PostMapping
    public ResponseEntity<Response> addNewCustomer(@RequestBody Customer customer) {
        log.trace("Request came to add new customer with following details: {}", customer);
        Customer persistedCustomer = customerService.addCustomer(customer);
        if (null != persistedCustomer) {
            return buildResponseForSuccess(HttpStatus.SC_OK, "Successfully added new customer", persistedCustomer);
        }
        return buildResponseForError(HttpStatus.SC_INTERNAL_SERVER_ERROR, String.valueOf(HttpStatus.SC_INTERNAL_SERVER_ERROR), "Unable to add the customer.", null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getCustomer(@PathVariable("id") Long id) {
        log.trace("Request came to get the customer details having the id: {}", id);
        Customer customer = customerService.getCustomerById(id);
        if (null != customer) {
            return buildResponseForSuccess(HttpStatus.SC_OK, "Successfully fetched customer", customer);
        }
        return buildResponseForError(HttpStatus.SC_BAD_REQUEST, String.valueOf(HttpStatus.SC_BAD_REQUEST), "No customer detail found for the given id.", null);
    }

    @GetMapping("/byEmailId/{id}")
    public ResponseEntity<Response> getCustomerMyEmailId(@PathVariable("id") String emailId) {
        log.trace("Request came to get the customer details having the email id: {}", emailId);
        Customer customer = customerService.getCustomerByEmailId(emailId);
        if (null != customer) {
            return buildResponseForSuccess(HttpStatus.SC_OK, "Successfully fetched customer", customer);
        }
        return buildResponseForError(HttpStatus.SC_BAD_REQUEST, String.valueOf(HttpStatus.SC_BAD_REQUEST), "No customer detail found for the given email id.", null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> updateCustomer(@PathVariable("id") Long id, @RequestBody Customer customer) {
        log.trace("Request came to update the customer id: {}, with following details: {}", id, customer);
        Customer persistedCustomer = customerService.updateCustomer(id,customer);
        if (null != persistedCustomer) {
            return buildResponseForSuccess(HttpStatus.SC_OK, "Successfully update the customer", persistedCustomer);
        }
        return buildResponseForError(HttpStatus.SC_INTERNAL_SERVER_ERROR, String.valueOf(HttpStatus.SC_INTERNAL_SERVER_ERROR), "Unable to update the customer.", null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteCustomer(@PathVariable("id") Long id) {
        log.trace("Request came to delete the customer having the id: {}", id);
        customerService.deleteCustomer(id);
        return buildResponseForSuccess(HttpStatus.SC_OK, "Successfully deleted the customer", null);
    }

}
