package com.vinod.validation.exception.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Log4j2
@Entity
@Table(name="customer",schema = "validation-customer")
public class Customer implements Serializable {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message="first name is mandatory field. Please provide first name")
    @Size(min = 2, message = "First name should have atleast 2 characters")
    @Column
    private String firstName;
    @Column
    private String lastName;
    @NotEmpty(message="Email id is mandatory field. Please provide Email address")
    @Column
    private String emailId;
    @Column
    private String address;
    @Column
    private Boolean status;
}
