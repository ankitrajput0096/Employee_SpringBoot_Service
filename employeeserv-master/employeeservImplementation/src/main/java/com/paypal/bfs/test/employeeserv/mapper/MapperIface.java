package com.paypal.bfs.test.employeeserv.mapper;

public interface MapperIface <S, T> {

    S toEntity(T t);
    T toDto(S s);

}
