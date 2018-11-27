/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saga.airportlocator.util;

import com.saga.airportlocator.constant.DistanceUnit;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sjadhav
 */
@Slf4j
public class AirportLocatorUtilsTests {

    public AirportLocatorUtilsTests() {
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
    public void testCalculateDistanceWithLatAndLongWithZeroValues() {
        log.info("testCalculateDistanceWithLatAndLongWithZeroValues Start");
        double lat1 = 0.0;
        double lon1 = 0.0;
        double lat2 = 0.0;
        double lon2 = 0.0;
        double expResult = 0.0;
        double result = AirportLocatorUtils.calculateDistance(lat1, lon1, lat2, lon2, DistanceUnit.KM);
        assertEquals(expResult, result, 0.0);
        log.info("testCalculateDistanceWithLatAndLongWithZeroValues End");
    }

    @Test
    public void testCalculateDistanceWithSameValuesFrLatAndLong() {
        log.info("testCalculateDistanceWithSameValuesFrLatAndLong Start");
        double lat1 = 32.9697;
        double lon1 = -96.80322;
        double lat2 = 32.9697;
        double lon2 = -96.80322;
        double expResult = 0.0;
        double result = AirportLocatorUtils.calculateDistance(lat1, lon1, lat2, lon2, DistanceUnit.KM);
        assertEquals(expResult, result, 0.0);
        log.info("testCalculateDistanceWithSameValuesFrLatAndLong End");
    }

    @Test
    public void testCalculateDistanceFromLocationSadashivPethPune() {
        log.info("testCalculateDistanceFromLocationSadashivPethPune Start");
        double lat1 = 18.5083;
        double lon1 = 73.8466;
        double lat2 = 19.0886;
        double lon2 = 72.8681;
        double expResult = 122;
        double result = AirportLocatorUtils.calculateDistance(lat1, lon1, lat2, lon2, DistanceUnit.KM);
        Assert.assertEquals(expResult, result, 0.0);
        log.info("testCalculateDistanceFromLocationSadashivPethPune End");
    }

    @Test
    public void testCalculateDistanceFromLocationSadashivPethPuneInMiles() {
        log.info("testCalculateDistanceFromLocationSadashivPethPuneInMiles Start");
        double lat1 = 18.5083;
        double lon1 = 73.8466;
        double lat2 = 19.0886;
        double lon2 = 72.8681;
        double expResult = 76;
        double result = AirportLocatorUtils.calculateDistance(lat1, lon1, lat2, lon2, DistanceUnit.MI);
        Assert.assertEquals(expResult, result, 0.0);
        log.info("testCalculateDistanceFromLocationSadashivPethPuneInMiles End");
    }

    @Test
    public void testCalculateDistanceFromLocationSadashivPethPuneInNauticalMiles() {
        log.info("testCalculateDistanceFromLocationSadashivPethPuneInNauticalMiles Start");
        double lat1 = 18.5083;
        double lon1 = 73.8466;
        double lat2 = 19.0886;
        double lon2 = 72.8681;
        double expResult = 66;
        double result = AirportLocatorUtils.calculateDistance(lat1, lon1, lat2, lon2, DistanceUnit.NM);
        Assert.assertEquals(expResult, result, 0.0);
        log.info("testCalculateDistanceFromLocationSadashivPethPuneInNauticalMiles End");
    }
}
