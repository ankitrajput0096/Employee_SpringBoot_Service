package com.paypal.bfs.test.employeeserv.test.service;

import com.paypal.bfs.test.employeeserv.EmployeeResourceImpl;
import com.paypal.bfs.test.employeeserv.api.exceptions.ApplicationException;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.service.EmployeeResourceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * Unit test for simple App.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class AppTest {

    @Mock
    private EmployeeResourceService employeeResourceService;

    /* mocking component for which the testcase is being written */
    @InjectMocks
    private EmployeeResourceImpl springbootService;

    @Test
    public void getAllTopicsTest() {
        // Test data

        ResponseEntity<Employee> emp = null;
        Employee employee = new Employee();
        try {
            when(employeeResourceService.employeeGetById("1")).thenReturn(employee);
        } catch (ApplicationException e) {
            e.printStackTrace();
        }

        // The logic we're testing in this testcase
        try {
             emp = springbootService.employeeGetById("1");
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
        assertNotNull("not null", emp);
    }
}