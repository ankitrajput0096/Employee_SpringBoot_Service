package com.paypal.bfs.test.employeeserv.test.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.paypal.bfs.test.employeeserv.api.exceptions.ApplicationException;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.entity.EmployeeEntity;
import com.paypal.bfs.test.employeeserv.mapper.impl.EmployeeEntityDtoMapper;
import com.paypal.bfs.test.employeeserv.repo.EmployeeRepository;
import com.paypal.bfs.test.employeeserv.service.impl.EmployeeResourceServiceImpl;
import com.paypal.bfs.test.employeeserv.test.utils.TestUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.After;
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
     *     with requested details is present in DB.
     */
    @Test
    public void employeeGetByIdTest()
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
        assertNotNull("Check test employee is not null", employeeTest);
        assertTrue("Check test employee's address line1 is not empty", StringUtils
                .isNotEmpty(employeeTest.getAddress().getLine1()));
        assertTrue("Check test employee's address line2 is not empty", StringUtils
                .isNotEmpty(employeeTest.getAddress().getLine2()));
        assertFalse("Check test employee's first name is empty",
                StringUtils.isEmpty(employeeTest.getFirstName()));
        assertNotNull("Check test employee's Date of birth is not null", employeeTest
                .getDateOfBirth());
    }

    @After
    public void tearDownAfterEachTestCase() {
        verifyNoMoreInteractions(employeeRepository);
    }
}