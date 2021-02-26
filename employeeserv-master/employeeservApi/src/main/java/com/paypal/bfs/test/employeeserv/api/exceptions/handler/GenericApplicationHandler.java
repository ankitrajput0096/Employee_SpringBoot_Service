package com.paypal.bfs.test.employeeserv.api.exceptions.handler;

import com.paypal.bfs.test.employeeserv.api.exceptions.ApplicationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Objects;

@ControllerAdvice
public class GenericApplicationHandler extends ResponseEntityExceptionHandler {

    /**
     * Controller advice method to handle ApplicationException exceptions.
     *
     * @param ex         - {@link ApplicationException}
     * @param webRequest - {@link HttpServletRequest}
     * @return - {@link ResponseEntity}
     */
    @ExceptionHandler(ApplicationException.class)
    public final ResponseEntity<ApplicationErrorSchema> handleApplicationException(
            final ApplicationException ex, final HttpServletRequest webRequest) {
        ApplicationErrorSchema e = new ApplicationErrorSchema(
                Objects.nonNull(ex.getCode()) ? ex.getCode() : HttpStatus.INTERNAL_SERVER_ERROR.value(),
                Objects.nonNull(ex.getStatus()) ? ex.getStatus() : HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                new Date().toString(),
                webRequest.getRequestURI());
        return ResponseEntity.status(e.getCode()).body(e);
    }
}
