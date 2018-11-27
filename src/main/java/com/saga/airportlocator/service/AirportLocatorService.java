/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saga.airportlocator.service;

import com.saga.airportlocator.model.AirportRelativeDistance;
import java.util.List;
import java.util.Optional;
import javax.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

/**
 *
 * @author sjadhav
 */
@Service
public interface AirportLocatorService {

    public List<AirportRelativeDistance> getNearestAirport(@NotNull Double lat, @NotNull Double lng, Optional<Integer> count) throws Exception;
}
