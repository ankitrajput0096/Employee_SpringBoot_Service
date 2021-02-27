package com.paypal.bfs.test.employeeserv.test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paypal.bfs.test.employeeserv.api.exceptions.ApplicationException;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.service.EmployeeResourceService;
import com.paypal.bfs.test.employeeserv.test.utils.TestUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class EmployeeResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private EmployeeResourceService employeeResourceService;

    @Before
    public void setupBeforeEachTestCase() {
        reset(this.employeeResourceService);
    }

    /**
     * Method Name : employeeGetById
     * Description : This testcase tests the scenario where Employee
     * with requested details is present in DB.
     */
    @Test
    public void employeeGetByIdTestSuccess()
            throws Exception {
        // Test data
        Employee employeeTest = TestUtils.createTestEmployee();

        // Mocks
        given(this.employeeResourceService.employeeGetById(anyString()))
                .willReturn(employeeTest);

        // The logic we're testing in this testcase
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/bfs/employees/1")
                .accept(MediaType.ALL))
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()))
                .andExpect(MockMvcResultMatchers.header()
                        .string("Content-Type", "application/json;charset=UTF-8"));
    }

    /**
     * Method Name : employeeGetById
     * Description : This testcase tests the scenario where Employee
     * with requested details is not present in DB.
     */
    @Test
    public void employeeGetByIdTestFailure()
            throws Exception {
        // Mocks
        doThrow(new ApplicationException(HttpStatus.NOT_FOUND.value(),
                "Required Employee not found in DB.")).when(this.employeeResourceService)
                .employeeGetById(anyString());

        // The logic we're testing in this testcase
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/bfs/employees/1")
                .accept(MediaType.ALL))
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.NOT_FOUND.value()))
                .andExpect(MockMvcResultMatchers.header()
                        .string("Content-Type", "application/json;charset=UTF-8"));
    }

    /**
     * Method Name : employeeCreation
     * Description : This testcase tests the scenario where Employee
     * resource is created successfully
     */
    @Test
    public void employeeCreationTestSuccess()
            throws Exception {
        // Test data
        Employee employeeTest = TestUtils.createTestEmployee();

        // Mocks
        given(this.employeeResourceService.employeeCreation(any()))
                .willReturn(employeeTest);

        // The logic we're testing in this testcase
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/bfs/employees")
                .content(this.objectMapper.writeValueAsString(employeeTest))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.CREATED.value()))
                .andExpect(MockMvcResultMatchers.header()
                        .string("Content-Type", "application/json;charset=UTF-8"));
    }

    /**
     * Method Name : employeeCreation
     * Description : This testcase tests the scenario where while creating Employee resource
     * an exception occurred.
     */
    @Test
    public void employeeCreationTestFailure()
            throws Exception {
        // Test data
        Employee employeeTest = TestUtils.createTestEmployee();

        // Mocks
        doThrow(new ApplicationException(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Duplicate request for creating Employee with id : 1"))
                .when(this.employeeResourceService).employeeCreation(any());

        // The logic we're testing in this testcase
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/bfs/employees")
                .content(this.objectMapper.writeValueAsString(employeeTest))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status()
                        .is(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .andExpect(MockMvcResultMatchers.header()
                        .string("Content-Type", "application/json;charset=UTF-8"));
    }
}
