package com.paypal.bfs.test.employeeserv.mapper.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paypal.bfs.test.employeeserv.api.model.Address;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.entity.EmployeeEntity;
import com.paypal.bfs.test.employeeserv.api.exceptions.ApplicationException;
import com.paypal.bfs.test.employeeserv.mapper.MapperIface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Component
public class EmployeeEntityDtoMapper implements MapperIface<EmployeeEntity, Employee> {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public EmployeeEntity toEntity(Employee employee) throws ApplicationException {
        Date empDob = null;
        try {
            empDob = new SimpleDateFormat("dd/MM/yyyy").parse(employee.getDateOfBirth());
        } catch (ParseException e) {
            log.error("The Date of birth format is not in appropriate " +
                    "format, with exception : {}", e.getMessage());
            throw new ApplicationException(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    e.getMessage());
        }
        String address = "";
        try {
            address = this.objectMapper.writeValueAsString(employee.getAddress());
        } catch (IOException e) {
            log.error("Exception during conversion of employee address to string : {}", e.getMessage());
            throw new ApplicationException(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    e.getMessage());
        }
        EmployeeEntity employeeEntity = EmployeeEntity.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .dob(empDob)
                .address(address)
                .build();
        return employeeEntity;
    }

    @Override
    public Employee toDto(EmployeeEntity employeeEntity) throws ApplicationException {
        Employee employee = new Employee();
        employee.setId(employeeEntity.getId());
        employee.setFirstName(employeeEntity.getFirstName());
        employee.setLastName(employeeEntity.getLastName());
        employee.setDateOfBirth(employeeEntity.getDob().toString());
        try {
            employee.setAddress(this.objectMapper.readValue(employeeEntity.getAddress(), Address.class));
        } catch (IOException e) {
            log.error("Exception during conversion of employee address : {}", e.getMessage());
            throw new ApplicationException(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    e.getMessage());
        }
        return employee;
    }
}
