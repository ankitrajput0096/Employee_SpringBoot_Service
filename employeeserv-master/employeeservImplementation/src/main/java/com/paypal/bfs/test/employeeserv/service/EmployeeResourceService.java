package com.paypal.bfs.test.employeeserv.service;

import com.paypal.bfs.test.employeeserv.api.exceptions.ApplicationException;
import com.paypal.bfs.test.employeeserv.api.model.Employee;

public interface EmployeeResourceService {

    Employee employeeGetById(final String id)
            throws ApplicationException;

    Employee employeeCreation(final Employee employee)
            throws ApplicationException;

}
