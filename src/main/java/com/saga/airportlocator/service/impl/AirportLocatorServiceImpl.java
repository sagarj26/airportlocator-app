/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saga.airportlocator.service.impl;

import com.saga.airportlocator.constant.DistanceUnit;
import com.saga.airportlocator.dao.AirportData;
import com.saga.airportlocator.model.AirportInfo;
import com.saga.airportlocator.model.AirportRelativeDistance;
import com.saga.airportlocator.model.Distance;
import com.saga.airportlocator.model.NearestAirportRequest;
import com.saga.airportlocator.util.AirportLocatorUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.saga.airportlocator.service.AirportLocatorService;
import java.util.Optional;
import javax.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Service
public class AirportLocatorServiceImpl implements AirportLocatorService {

    @Autowired
    AirportData airportDataExcelImpl;

    @Override
    @Validated
    public List<AirportRelativeDistance> getNearestAirport(@NotNull Double lat,@NotNull Double lng, Optional<Integer> count) throws Exception {
        Integer size = count.isPresent() ? count.get() : 10;
        List<AirportInfo> airportsInfo = airportDataExcelImpl.fetchAirportsInfo();
        List<AirportRelativeDistance> nearestAirports = new ArrayList<>();
        airportsInfo.forEach((AirportInfo airportInfo) -> {
            double distance = AirportLocatorUtils.calculateDistance(lat, lng, airportInfo.getLatitude(), airportInfo.getLongitude(), null);
            nearestAirports.add(new AirportRelativeDistance(airportInfo, new Distance(distance, DistanceUnit.KM.name())));
        });
        Collections.sort(nearestAirports, Comparator.comparingDouble((AirportRelativeDistance aiprortDistance) -> aiprortDistance.getRelativeDistance().getValue()));
        return nearestAirports.subList(0, size);
    }
}
