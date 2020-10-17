package com.vinod.validation.exception.controller;

import com.vinod.validation.exception.exception.CustomerAlreadyExistException;
import com.vinod.validation.exception.exception.CustomerNotFoundException;
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
        try {
            log.trace("Request came to add new customer with following details: {}", customer);
            Customer persistedCustomer = customerService.addCustomer(customer);
            return buildResponseForSuccess(HttpStatus.SC_OK, "Successfully added new customer", persistedCustomer);
        } catch (CustomerAlreadyExistException e) {
            log.warn("Unable to create the customer for email id: {}, error msg: {}", customer.getEmailId(), e.getMessage(), e);
            return buildResponseForError(HttpStatus.SC_BAD_REQUEST, String.valueOf(HttpStatus.SC_PRECONDITION_FAILED), e.getMessage(), null);
        } catch (Exception e) {
            log.error("Unable to create the customer for email id: {}, error msg: {}", customer.getEmailId(), e.getMessage(), e);
            return buildResponseForError(HttpStatus.SC_INTERNAL_SERVER_ERROR, String.valueOf(HttpStatus.SC_INTERNAL_SERVER_ERROR), "Oops! Something went wrong.", null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getCustomer(@PathVariable("id") Long id) {
        try {
            log.trace("Request came to get the customer details having the id: {}", id);
            Customer customer = customerService.getCustomerById(id);
            return buildResponseForSuccess(HttpStatus.SC_OK, "Successfully fetched customer", customer);
        } catch (CustomerNotFoundException e) {
            log.warn("Unable to get the customer details for id: {}, error msg: {}", id, e.getMessage(), e);
            //throw new ResponseStatusException(org.springframework.http.HttpStatus.NOT_FOUND, e.getMessage());
            return buildResponseForError(HttpStatus.SC_BAD_REQUEST, String.valueOf(HttpStatus.SC_NOT_FOUND), e.getMessage(), null);
        } catch (Exception e) {
            log.error("Unable to get the customer details for id: {}, error msg: {}", id, e.getMessage(), e);
            return buildResponseForError(HttpStatus.SC_INTERNAL_SERVER_ERROR, String.valueOf(HttpStatus.SC_INTERNAL_SERVER_ERROR), "Oops! Something went wrong.", null);
        }
    }

    @GetMapping("/byEmailId/{id}")
    public ResponseEntity<Response> getCustomerMyEmailId(@PathVariable("id") String emailId) {
        try {
            log.trace("Request came to get the customer details having the email id: {}", emailId);
            Customer customer = customerService.getCustomerByEmailId(emailId);
            return buildResponseForSuccess(HttpStatus.SC_OK, "Successfully fetched customer", customer);
        } catch (CustomerNotFoundException e) {
            log.warn("Unable to get the customer details for email id: {}, error msg: {}", emailId, e.getMessage(), e);
            return buildResponseForError(HttpStatus.SC_BAD_REQUEST, String.valueOf(HttpStatus.SC_NOT_FOUND), e.getMessage(), null);
        } catch (Exception e) {
            log.error("Unable to get the customer details for email id: {}, error msg: {}", emailId, e.getMessage(), e);
            return buildResponseForError(HttpStatus.SC_INTERNAL_SERVER_ERROR, String.valueOf(HttpStatus.SC_INTERNAL_SERVER_ERROR), "Oops! Something went wrong.", null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> updateCustomer(@PathVariable("id") Long id, @RequestBody Customer customer) {
        try {
            log.trace("Request came to update the customer id: {}, with following details: {}", id, customer);
            Customer persistedCustomer = customerService.updateCustomer(id, customer);
            return buildResponseForSuccess(HttpStatus.SC_OK, "Successfully update the customer", persistedCustomer);
        } catch (CustomerNotFoundException e) {
            log.warn("Unable to update the customer details for id: {}, error msg: {}", id, e.getMessage(), e);
            return buildResponseForError(HttpStatus.SC_BAD_REQUEST, String.valueOf(HttpStatus.SC_NOT_FOUND), e.getMessage(), null);
        } catch (Exception e) {
            log.error("Unable to update the customer details for id: {}, error msg: {}", id, e.getMessage(), e);
            return buildResponseForError(HttpStatus.SC_INTERNAL_SERVER_ERROR, String.valueOf(HttpStatus.SC_INTERNAL_SERVER_ERROR), "Oops! Something went wrong.", null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteCustomer(@PathVariable("id") Long id) {
        try {
            log.trace("Request came to delete the customer having the id: {}", id);
            customerService.deleteCustomer(id);
            return buildResponseForSuccess(HttpStatus.SC_OK, "Successfully deleted the customer", null);
        } catch (CustomerNotFoundException e) {
            log.warn("Unable to delete the customer for id: {}, error msg: {}", id, e.getMessage(), e);
            return buildResponseForError(HttpStatus.SC_BAD_REQUEST, String.valueOf(HttpStatus.SC_NOT_FOUND), e.getMessage(), null);
        } catch (Exception e) {
            log.error("Unable to delete the customer for id: {}, error msg: {}", id, e.getMessage(), e);
            return buildResponseForError(HttpStatus.SC_INTERNAL_SERVER_ERROR, String.valueOf(HttpStatus.SC_INTERNAL_SERVER_ERROR), "Oops! Something went wrong.", null);
        }
    }

}
