package com.paypal.bfs.test.employeeserv.service.impl;

import com.paypal.bfs.test.employeeserv.api.EmployeeResource;
import com.paypal.bfs.test.employeeserv.api.model.Address;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.entity.EmployeeEntity;
import com.paypal.bfs.test.employeeserv.api.exceptions.ApplicationException;
import com.paypal.bfs.test.employeeserv.mapper.impl.EmployeeEntityDtoMapper;
import com.paypal.bfs.test.employeeserv.repo.EmployeeRepository;
import jersey.repackaged.com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
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
        // adding validation for the request
        Preconditions.checkArgument(StringUtils.isNumeric(id),
                "The employee id has to be numeric.");

        Optional<EmployeeEntity> emp = this.employeeRepository
                .findById(Integer.parseInt(id));
        if (emp.isPresent())
            return ResponseEntity.ok().body(this
                    .empEntityDtoMapper.toDto(emp.get()));
        else {
            log.info("Requesting details for Employee with " +
                    "id : {} is not found in DB", id);
            throw new ApplicationException(HttpStatus.NOT_FOUND.value(),
                    "Required Employee not found in DB.");
        }
    }

    @Override
    public ResponseEntity<Employee> employeeCreation(final Employee employee)
            throws ApplicationException {
        //adding validation for request
        Preconditions.checkArgument(!StringUtils.isEmpty(employee
                .getFirstName()), "The employee first name cannot be empty");
        Preconditions.checkArgument(!StringUtils.isEmpty(employee
                .getLastName()), "The employee last name cannot be empty");
        Preconditions.checkArgument(!StringUtils.isEmpty(employee
                .getAddress().getLine1()), "The address line 1 cannot be empty");
        Preconditions.checkArgument(!StringUtils.isEmpty(employee
                .getAddress().getCity()), "The city cannot be empty");
        Preconditions.checkArgument(!StringUtils.isEmpty(employee
                .getAddress().getState()), "The state cannot be empty");
        Preconditions.checkArgument(!StringUtils.isEmpty(employee
                .getAddress().getCountry()), "The country cannot be empty");
        Preconditions.checkNotNull(employee.getAddress()
                .getZipCode(), "The zipcode cannot be null");

        // Handle scenario, when client don't add `line2` in request body
        if (Objects.isNull(employee.getAddress().getLine2())) {
            Address empAddress = employee.getAddress();
            empAddress.setLine2("");
            employee.setAddress(empAddress);
        }

        if (this.employeeRepository.findById(employee.getId()).isPresent()) {
            log.info("Requesting to create duplicate employee resource with " +
                    "same emp id : {}", employee.getId());
            throw new ApplicationException(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Duplicate request for creating Employee with id : "
                            .concat(employee.getId().toString()));
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    this.empEntityDtoMapper.toDto(this.employeeRepository.
                            save(this.empEntityDtoMapper.toEntity(employee))));
        }
    }
}
