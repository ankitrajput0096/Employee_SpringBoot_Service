package com.paypal.bfs.test.employeeserv.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paypal.bfs.test.employeeserv.api.EmployeeResource;
import com.paypal.bfs.test.employeeserv.api.model.Address;
import com.paypal.bfs.test.employeeserv.api.model.DateOfBirth;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * Implementation class for employee resource.
 */
@Slf4j
@RestController
public class EmployeeResourceImpl implements EmployeeResource {

    @Override
    public ResponseEntity<Employee> employeeGetById(String id) {

        Employee employee = new Employee();
        employee.setId(Integer.valueOf(id));
        employee.setFirstName("BFS");
        employee.setLastName("Developer");

        DateOfBirth dob = new DateOfBirth();
        dob.setDay(1);
        dob.setMonth(12);
        dob.setYear(2020);
        employee.setDateOfBirth(dob);

        Address address = new Address();
        address.setLine1("House No: 14, 2nd Cross, Sai Nagar");
        address.setLine2("Vidyaranyapura, Bangalore");
        address.setCity("Bangalore");
        address.setState("Karnataka");
        address.setCountry("India");
        address.setZipCode(560097);
        employee.setAddress(address);

        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Employee> employeeCreation(final Employee employee) {
        try {
            log.info(new ObjectMapper().writeValueAsString(employee));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok().body(employee);
    }


}
