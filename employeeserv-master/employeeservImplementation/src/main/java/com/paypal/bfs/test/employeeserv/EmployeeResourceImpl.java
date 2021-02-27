package com.paypal.bfs.test.employeeserv;

import com.paypal.bfs.test.employeeserv.api.EmployeeResource;
import com.paypal.bfs.test.employeeserv.api.exceptions.ApplicationException;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.service.EmployeeResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * Implementation class for employee resource.
 */
@Slf4j
@RestController
public class EmployeeResourceImpl implements EmployeeResource {

    @Autowired
    private EmployeeResourceService employeeResourceService;

    @Override
    public ResponseEntity<Employee> employeeGetById(String id)
            throws ApplicationException {
        return ResponseEntity.ok().body(this
                .employeeResourceService.employeeGetById(id));
    }

    @Override
    public ResponseEntity<Employee> employeeCreation(final Employee employee)
            throws ApplicationException {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                this.employeeResourceService.employeeCreation(employee));

    }
}
