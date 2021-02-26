package com.paypal.bfs.test.employeeserv.service.impl;

import com.paypal.bfs.test.employeeserv.api.EmployeeResource;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.entity.EmployeeEntity;
import com.paypal.bfs.test.employeeserv.api.exceptions.ApplicationException;
import com.paypal.bfs.test.employeeserv.mapper.impl.EmployeeEntityDtoMapper;
import com.paypal.bfs.test.employeeserv.repo.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<Employee> employeeGetById(String id)
            throws ApplicationException {
        Optional<EmployeeEntity> emp = this.employeeRepository
                .findById(Integer.parseInt(id));
        if (emp.isPresent())
            return ResponseEntity.ok().body(this.empEntityDtoMapper.toDto(emp.get()));
        else {
            log.info("Requesting details for Employee with id : {} is not found in DB", id);
            throw new ApplicationException(HttpStatus.NOT_FOUND.value(), "Required Employee not found in DB.");
        }
    }

    @Override
    public ResponseEntity<Employee> employeeCreation(final Employee employee) throws ApplicationException {
        if (this.employeeRepository.findById(employee.getId()).isPresent()) {
            log.info("Requesting to create duplicate employee resource with same emp id : {}", employee.getId());
            throw new ApplicationException(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Duplicate request for creating Employee with id : ".concat(employee.getId().toString()));
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    this.empEntityDtoMapper.toDto(this.employeeRepository.
                            save(this.empEntityDtoMapper.toEntity(employee))));
        }
    }
}
