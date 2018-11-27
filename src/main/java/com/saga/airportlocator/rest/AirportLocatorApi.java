/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saga.airportlocator.rest;

import com.saga.airportlocator.model.NearestAirportRequest;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ExampleProperty;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;

/**
 *
 * @author sjadhav
 */
@RestController
public interface AirportLocatorApi {

    @PostMapping(value = "/public/references/airports/nearest",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Get nearest airport details", notes = "This is a public API", response = List.class)
    @ApiResponses(value = {
        @ApiResponse(code = HttpServletResponse.SC_OK, message = "Success"),
        @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Not found any nearest airport for given latitude and longitude")
    })
    ResponseEntity getNearestAirport(@Valid @RequestBody NearestAirportRequest airportRequest) throws Exception;
}
