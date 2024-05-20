package com.codmind.orderapi.config;

import com.codmind.orderapi.exceptions.GeneralServiceException;
import com.codmind.orderapi.exceptions.NoDataFoundException;
import com.codmind.orderapi.exceptions.ValidateServiceException;
import com.codmind.orderapi.utils.WrapperResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class ErrorHandlerConfig extends ResponseEntityExceptionHandler {



    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> all(Exception e, WebRequest request){
        log.error(e.getMessage(), e);//error de origen desconocido

        WrapperResponse<?> wrapperResponse = new WrapperResponse<>(false, "Internal server error", null);

        return new ResponseEntity<>(wrapperResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ValidateServiceException.class)
    public ResponseEntity<?> validateServiceException(ValidateServiceException e, WebRequest request){
        log.info(e.getMessage(), e);

        WrapperResponse<?> wrapperResponse = new WrapperResponse<>(false, e.getMessage(), null);
        return new ResponseEntity<>(wrapperResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<?> noDataFoundException(NoDataFoundException e, WebRequest request){
        log.info(e.getMessage(), e);

        WrapperResponse<?> wrapperResponse = new WrapperResponse<>(false, e.getMessage(), null);
        return new ResponseEntity<>(wrapperResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(GeneralServiceException.class)
    public ResponseEntity<?> generalServiceException(GeneralServiceException e, WebRequest request){
        log.error(e.getMessage(), e);//error de origen desconocido

        WrapperResponse<?> wrapperResponse = new WrapperResponse<>(false, "Internal server error", null);
        return new ResponseEntity<>(wrapperResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
