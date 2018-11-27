/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saga.airportlocator.rest.impl;

import com.saga.airportlocator.model.AirportRelativeDistance;
import com.saga.airportlocator.model.NearestAirportRequest;
import com.saga.airportlocator.rest.AirportLocatorApi;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import com.saga.airportlocator.service.AirportLocatorService;
import java.util.Optional;
import javax.validation.Valid;

@Component
public class AirportLocatorApiImpl implements AirportLocatorApi {

    @Autowired
    AirportLocatorService airportLocatorImpl;

    @Override
    public ResponseEntity getNearestAirport(@Valid NearestAirportRequest airportRequest) throws Exception {

        Double latitude = airportRequest.getLatitude();
        Double longitude = airportRequest.getLongitude();
        Integer noOfAirports = airportRequest.getNoOfAirports();
        List<AirportRelativeDistance> nearestAirport = this.airportLocatorImpl.getNearestAirport(latitude, longitude, Optional.ofNullable(noOfAirports));
        return new ResponseEntity(nearestAirport, HttpStatus.OK);
    }

}
