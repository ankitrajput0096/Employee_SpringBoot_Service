package com.paypal.bfs.test.employeeserv.service.impl;

import com.paypal.bfs.test.employeeserv.api.exceptions.ApplicationException;
import com.paypal.bfs.test.employeeserv.api.model.Address;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.entity.EmployeeEntity;
import com.paypal.bfs.test.employeeserv.mapper.impl.EmployeeEntityDtoMapper;
import com.paypal.bfs.test.employeeserv.repo.EmployeeRepository;
import com.paypal.bfs.test.employeeserv.service.EmployeeResourceService;
import jersey.repackaged.com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class EmployeeResourceServiceImpl implements EmployeeResourceService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeEntityDtoMapper empEntityDtoMapper;

    /**
     * Retrieves the {@link Employee} resource by id.
     *
     * @param id employee id.
     * @return {@link Employee} resource.
     */
    @Override
    public Employee employeeGetById(final String id)
            throws ApplicationException {
        log.info("Received request to retrieve employee by id : {}", id);
        // adding validation for the request
        Preconditions.checkArgument(StringUtils.isNumeric(id),
                "The employee id has to be numeric.");

        Optional<EmployeeEntity> emp = this.employeeRepository
                .findById(Integer.parseInt(id));
        if (emp.isPresent())
            return this.empEntityDtoMapper.toDto(emp.get());
        else {
            log.info("Requesting details for Employee with " +
                    "id : {} is not found in DB", id);
            throw new ApplicationException(HttpStatus.NOT_FOUND.value(),
                    "Required Employee not found in DB.");
        }
    }

    /**
     * Creates the {@link Employee} resource in application
     *
     * @param employee {@link Employee} resource
     * @return {@link Employee} resource.
     */
    @Override
    public Employee employeeCreation(final Employee employee)
            throws ApplicationException {
        log.info("Received request to create employee by id: {}", employee.getId());
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
            return this.empEntityDtoMapper.toDto(this.employeeRepository.
                            save(this.empEntityDtoMapper.toEntity(employee)));
        }
    }
}
