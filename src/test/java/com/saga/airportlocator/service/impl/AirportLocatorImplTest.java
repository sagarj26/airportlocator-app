/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saga.airportlocator.service.impl;

import com.saga.airportlocator.model.AirportRelativeDistance;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.saga.airportlocator.service.AirportLocatorService;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author sjadhav
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Slf4j
public class AirportLocatorImplTest {

    @Autowired
    AirportLocatorService airportLocatorImpl;

    public AirportLocatorImplTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testGetNearestAirport() throws Exception {
        log.info("testGetNearestAirport start");
        Double lat = 18.5083;
        Double lng = 73.8466;
        List<AirportRelativeDistance> result = airportLocatorImpl.getNearestAirport(lat, lng, Optional.of(2));
        assertNotNull(result);
        assertEquals(2, result.size());
        log.info("testGetNearestAirport end");
    }

    @Test
    public void testGetNearestAirportWithEmptyValueFrNoOfAirPortsGivesDefaultNoOfAirPorts() throws Exception {
        log.info("testGetNearestAirportWithEmptyValueFrNoOfAirPortsGivesDefaultNoOfAirPorts start");
        Double lat = 18.5083;
        Double lng = 73.8466;
        List<AirportRelativeDistance> result = airportLocatorImpl.getNearestAirport(lat, lng, Optional.of(null));
        assertNotNull(result);
        assertEquals(10, result.size());
        log.info("testGetNearestAirportWithEmptyValueFrNoOfAirPortsGivesDefaultNoOfAirPorts end");
    }

    @Test
    public void testGetNearestAirportWithNoOfAirportsHavingValueFive() throws Exception {
        log.info("testGetNearestAirportWithNoOfAirportsHavingValueFive start");
        Double lat = 18.5083;
        Double lng = 73.8466;
        List<AirportRelativeDistance> result = airportLocatorImpl.getNearestAirport(lat, lng, Optional.of(5));
        assertNotNull(result);
        assertEquals(5, result.size());
        log.info("=====Airport details =====");
        result.forEach(airport -> {
            log.info(airport.toString()+"\n");
        });
        log.info("testGetNearestAirportWithNoOfAirportsHavingValueFive end");
    }
}
