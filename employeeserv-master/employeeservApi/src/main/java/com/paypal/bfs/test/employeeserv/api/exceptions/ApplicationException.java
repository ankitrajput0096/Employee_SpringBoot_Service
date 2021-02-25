package com.paypal.bfs.test.employeeserv.api.exceptions;

import lombok.*;

@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor
public class ApplicationException extends Exception {
    private int code;
    private String status;
}
