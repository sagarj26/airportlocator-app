/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saga.airportlocator.util;

import com.saga.airportlocator.model.AirportInfo;
import java.util.List;

/**
 *
 * @author sjadhav
 */
public interface AirportDataReader {

    List<AirportInfo> readAirPortsInfo() throws Exception;
}
