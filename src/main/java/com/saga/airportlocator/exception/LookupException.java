/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saga.airportlocator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author sjadhav
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class LookupException extends Exception {

    private static final long serialVersionUID = 1123L;

    public LookupException(String exception) {
        super(exception);
    }
}
