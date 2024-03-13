package com.myblog7.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)//400
public class ResourceNotFound extends RuntimeException {
    public ResourceNotFound(String msg){
        super(msg);
    }
}
