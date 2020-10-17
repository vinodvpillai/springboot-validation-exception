package com.vinod.validation.exception.repository;

import com.vinod.validation.exception.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

    Optional<Customer> findByEmailId(String emailId);
}
