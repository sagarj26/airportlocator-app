/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saga.airportlocator.dao;

import com.saga.airportlocator.model.AirportInfo;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author sjadhav
 */
@Repository
public interface AirportData {

    public List<AirportInfo> fetchAirportsInfo() throws Exception;

}