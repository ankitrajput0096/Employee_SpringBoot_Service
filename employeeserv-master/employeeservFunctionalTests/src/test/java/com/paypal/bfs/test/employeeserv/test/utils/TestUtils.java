package com.paypal.bfs.test.employeeserv.test.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paypal.bfs.test.employeeserv.api.model.Address;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.entity.EmployeeEntity;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class TestUtils {

    @Autowired
    private ObjectMapper objectMapper;

    public static Employee createTestEmployee() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Address empAddress = new Address();
        empAddress.setLine1(RandomStringUtils.random(10, true, true));
        empAddress.setLine2(RandomStringUtils.random(10, true, true));
        empAddress.setState(RandomStringUtils.random(10, true, false));
        empAddress.setCity(RandomStringUtils.random(10, true, false));
        empAddress.setCountry(RandomStringUtils.random(10, true, false));
        empAddress.setZipCode(new Random().nextInt(10000));
        Employee employee = new Employee();
        employee.setId(new Random().nextInt(10000));
        employee.setFirstName(RandomStringUtils.random(5, true, false));
        employee.setLastName(RandomStringUtils.random(5, true, false));
        employee.setDateOfBirth(dateFormat.format(new Date()).toString());
        employee.setAddress(empAddress);
        return employee;
    }

    public static EmployeeEntity createTestEmployeeEntity(Employee employee)
            throws ParseException, JsonProcessingException {
         return EmployeeEntity.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .dob(new SimpleDateFormat("dd/MM/yyyy")
                        .parse(employee.getDateOfBirth()))
                .address(new ObjectMapper()
                        .writeValueAsString(employee.getAddress()))
                .build();
    }
}
