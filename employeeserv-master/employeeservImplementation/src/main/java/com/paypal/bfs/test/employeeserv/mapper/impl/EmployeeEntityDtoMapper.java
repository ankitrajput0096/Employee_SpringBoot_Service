package com.paypal.bfs.test.employeeserv.mapper.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paypal.bfs.test.employeeserv.api.model.Address;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.entity.EmployeeEntity;
import com.paypal.bfs.test.employeeserv.mapper.MapperIface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class EmployeeEntityDtoMapper implements MapperIface<EmployeeEntity, Employee> {
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public EmployeeEntity toEntity(Employee employee) {

        String sDate1=employee.getDateOfBirth();
        Date date1 = null;
        try {
            date1=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String address = "";
        try {

            address = objectMapper.writeValueAsString(employee.getAddress());
        } catch (IOException e) {
            e.printStackTrace();
        }



        EmployeeEntity employeeEntity = EmployeeEntity.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .dob(date1)
                .address(address)
                .build();


        return employeeEntity;
    }

    @Override
    public Employee toDto(EmployeeEntity employeeEntity) {
        Employee employee = new Employee();
        employee.setId(employeeEntity.getId());
        employee.setFirstName(employeeEntity.getFirstName());
        employee.setLastName(employeeEntity.getLastName());
        employee.setDateOfBirth(employeeEntity.getDob().toString());
        try {
            employee.setAddress(objectMapper.readValue(employeeEntity.getAddress(), Address.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employee;
    }
}
