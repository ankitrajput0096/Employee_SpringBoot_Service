package com.paypal.bfs.test.employeeserv.test.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.paypal.bfs.test.employeeserv.api.exceptions.ApplicationException;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.entity.EmployeeEntity;
import com.paypal.bfs.test.employeeserv.mapper.impl.EmployeeEntityDtoMapper;
import com.paypal.bfs.test.employeeserv.repo.EmployeeRepository;
import com.paypal.bfs.test.employeeserv.service.impl.EmployeeResourceServiceImpl;
import com.paypal.bfs.test.employeeserv.test.utils.TestUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.text.ParseException;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class EmployeeResourceServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private EmployeeEntityDtoMapper empEntityDtoMapper;

    @InjectMocks
    private EmployeeResourceServiceImpl employeeResourceService;

    @Before
    public void setupBeforeEachTestCase() {
        reset(this.employeeRepository);
    }

    /**
     * Method Name : employeeGetById
     * Description : This testcase tests the scenario where Employee
     * with requested details is present in DB.
     */
    @Test
    public void employeeGetByIdTestSuccess()
            throws ParseException, ApplicationException, JsonProcessingException {
        // Test data
        Employee employeeTest = TestUtils.createTestEmployee();
        EmployeeEntity employeeEntityTest = TestUtils
                .createTestEmployeeEntity(employeeTest);

        // Mocks
        when(this.employeeRepository.findById(anyInt())).thenReturn(Optional
                .ofNullable(employeeEntityTest));
        when(this.empEntityDtoMapper.toDto(any())).thenReturn(employeeTest);

        // The logic we're testing in this testcase
        Employee testEmployee = this.employeeResourceService
                .employeeGetById(employeeTest.getId().toString());

        // Verify the times mocked component is called
        verify(this.employeeRepository, times(1)).findById(anyInt());

        // Test case Assertions
        assertNotNull("Check test employee is not null", testEmployee);
        assertTrue("Check test employee's address line1 is not empty", StringUtils
                .isNotEmpty(testEmployee.getAddress().getLine1()));
        assertTrue("Check test employee's address line2 is not empty", StringUtils
                .isNotEmpty(testEmployee.getAddress().getLine2()));
        assertFalse("Check test employee's first name is empty",
                StringUtils.isEmpty(testEmployee.getFirstName()));
        assertNotNull("Check test employee's Date of birth is not null", testEmployee
                .getDateOfBirth());
    }

    /**
     * Method Name : employeeGetById
     * Description : This testcase tests the scenario where Employee
     * with requested details is not present in DB.
     */
    @Test(expected = ApplicationException.class)
    public void employeeGetByIdTestFailureOne()
            throws ApplicationException {
        // Date
        Integer randomId = RandomUtils.nextInt(10000);

        // Mocks
        when(this.employeeRepository.findById(anyInt())).thenReturn(Optional
                .ofNullable(null));

        // The logic we're testing in this testcase
        Employee testEmployee = this.employeeResourceService
                .employeeGetById(randomId.toString());
    }

    /**
     * Method Name : employeeGetById
     * Description : This testcase tests the scenario where employee id is alphabetical.
     */
    @Test(expected = IllegalArgumentException.class)
    public void employeeGetByIdTestFailureTwo()
            throws ApplicationException {
        // The logic we're testing in this testcase
        Employee testEmployee = this.employeeResourceService
                .employeeGetById(RandomStringUtils
                        .random(2, true, false));
    }

    /**
     * Method Name : employeeCreation
     * Description : This testcase tests the scenario where Employee
     * resource is created successfully
     */
    @Test
    public void employeeCreationTestSuccess()
            throws ParseException, ApplicationException, JsonProcessingException {
        // Test data
        Employee employeeTest = TestUtils.createTestEmployee();
        EmployeeEntity employeeEntityTest = TestUtils
                .createTestEmployeeEntity(employeeTest);

        // Mocks
        when(this.employeeRepository.findById(anyInt())).thenReturn(Optional
                .ofNullable(null));
        when(this.employeeRepository.save(any())).thenReturn(employeeEntityTest);
        when(this.empEntityDtoMapper.toDto(any())).thenReturn(employeeTest);
        when(this.empEntityDtoMapper.toEntity(any())).thenReturn(employeeEntityTest);

        // The logic we're testing in this testcase
        Employee testEmployee = this.employeeResourceService
                .employeeCreation(employeeTest);

        // Verify the times mocked component is called
        verify(this.employeeRepository, times(1)).findById(anyInt());
        verify(this.employeeRepository, times(1)).save(any());

        // Test case Assertions
        assertNotNull("Check test employee is not null", testEmployee);
        assertTrue("Check test employee's address line1 is not empty", StringUtils
                .isNotEmpty(testEmployee.getAddress().getLine1()));
        assertTrue("Check test employee's address line2 is not empty", StringUtils
                .isNotEmpty(testEmployee.getAddress().getLine2()));
        assertFalse("Check test employee's first name is empty",
                StringUtils.isEmpty(testEmployee.getFirstName()));
        assertNotNull("Check test employee's Date of birth is not null", testEmployee
                .getDateOfBirth());
    }

    /**
     * Method Name : employeeCreation
     * Description : This testcase tests the scenario where Employee's first name is empty.
     */
    @Test(expected = IllegalArgumentException.class)
    public void employeeCreationTestFailureOne()
            throws ParseException, ApplicationException, JsonProcessingException {
        // Test data
        Employee employeeTest = TestUtils.createTestEmployee();
        employeeTest.setFirstName("");

        // Mocks
        when(this.employeeRepository.findById(anyInt())).thenReturn(Optional
                .ofNullable(null));

        // The logic we're testing in this testcase
        Employee testEmployee = this.employeeResourceService
                .employeeCreation(employeeTest);
    }

    /**
     * Method Name : employeeCreation
     * Description : This testcase tests the scenario where Employee resource is
     * already present with given employee id in DB.
     */
    @Test(expected = ApplicationException.class)
    public void employeeCreationTestFailureTwo()
            throws ParseException, ApplicationException, JsonProcessingException {
        // Test data
        Employee employeeTest = TestUtils.createTestEmployee();
        EmployeeEntity employeeEntityTest = TestUtils
                .createTestEmployeeEntity(employeeTest);

        // Mocks
        when(this.employeeRepository.findById(anyInt())).thenReturn(Optional
                .ofNullable(employeeEntityTest));

        // The logic we're testing in this testcase
        Employee testEmployee = this.employeeResourceService
                .employeeCreation(employeeTest);
    }
}