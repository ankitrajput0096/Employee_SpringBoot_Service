package com.paypal.bfs.test.employeeserv.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paypal.bfs.test.employeeserv.api.EmployeeResource;
import com.paypal.bfs.test.employeeserv.api.model.Address;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.entity.EmployeeEntity;
import com.paypal.bfs.test.employeeserv.mapper.impl.EmployeeEntityDtoMapper;
import com.paypal.bfs.test.employeeserv.repo.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

/**
 * Implementation class for employee resource.
 */
@Slf4j
@RestController
public class EmployeeResourceImpl implements EmployeeResource {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeEntityDtoMapper empEntityDtoMapper;

    @Override
    public ResponseEntity<Employee> employeeGetById(String id) {

//        Employee employee = new Employee();
//        employee.setId(Integer.valueOf(id));
//        employee.setFirstName("BFS");
//        employee.setLastName("Developer");
//
//        String sDate1="31/12/1998";
//        try {
//            Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        employee.setDateOfBirth(sDate1);
//        Address address = new Address();
//        address.setLine1("House No: 14, 2nd Cross, Sai Nagar");
//        address.setLine2("Vidyaranyapura, Bangalore");
//        address.setCity("Bangalore");
//        address.setState("Karnataka");
//        address.setCountry("India");
//        address.setZipCode(560097);
//        employee.setAddress(address);

        Optional<EmployeeEntity> emp = employeeRepository.findById(Integer.parseInt(id));
        if(emp.isPresent())
            return ResponseEntity.ok().body(this.empEntityDtoMapper.toDto(emp.get()));

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Employee> employeeCreation(final Employee employee) {
        try {
            log.info(new ObjectMapper().writeValueAsString(employee));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        if(employeeRepository.findById(employee.getId()).isPresent()) {
            return ResponseEntity.badRequest().body(null);
        }else {
            EmployeeEntity employeeEntity = employeeRepository.save(this.empEntityDtoMapper.toEntity(employee));
            return ResponseEntity.ok().body(empEntityDtoMapper.toDto(employeeEntity));
        }
    }


}
