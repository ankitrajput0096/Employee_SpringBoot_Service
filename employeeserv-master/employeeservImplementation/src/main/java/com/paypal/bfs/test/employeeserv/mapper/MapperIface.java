package com.paypal.bfs.test.employeeserv.mapper;

import com.paypal.bfs.test.employeeserv.api.exceptions.ApplicationException;

public interface MapperIface<S, T> {
    S toEntity(T t) throws ApplicationException;

    T toDto(S s) throws ApplicationException;
}
