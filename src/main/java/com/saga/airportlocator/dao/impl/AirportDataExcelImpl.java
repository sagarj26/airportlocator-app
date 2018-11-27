/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saga.airportlocator.dao.impl;

import com.saga.airportlocator.dao.AirportData;
import com.saga.airportlocator.model.AirportInfo;
import com.saga.airportlocator.util.AirportDataReader;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


/**
 *
 * @author sjadhav
 */
@Repository
public class AirportDataExcelImpl implements AirportData {

    @Autowired
    AirportDataReader airportDataReader;

    @Override
    public List<AirportInfo> fetchAirportsInfo() throws Exception {
        return airportDataReader.fetchAirPortsInfo();
    }
}
