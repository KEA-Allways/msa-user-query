package com.allways.common.advice;

import com.allways.common.response.Response;
import com.allways.domain.blog.exception.BlogNotFoundException;
import com.allways.domain.user.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionAdvice {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response exception(Exception e) {
        //구체적인 에러 보여주기
        log.info("e= {}",e.getMessage());
        return Response.failure(-1000,"오류가 발생하였습니다.");

    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) //400
    public Response bindException(BindException e){
        return Response.failure(-1003,e.getBindingResult().getFieldError().getDefaultMessage());
    }


    @ExceptionHandler (UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)//404
    public Response memberNotFoundException() {
        return Response.failure(-1007,"요청한 회원을 찾을 수 없습니다.");
    }

    @ExceptionHandler (BlogNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)//404
    public Response BlogNotFoundException() {
        return Response.failure(-1016,"해당 블로그를 찾을 수 없습니다.");
    }

}
