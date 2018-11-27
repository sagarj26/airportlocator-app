/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saga.airportlocator.dao.impl;

import com.saga.airportlocator.dao.AirportData;
import com.saga.airportlocator.model.AirportInfo;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
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

/**
 *
 * @author sjadhav
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Slf4j
public class AirportDataExcelImplTest {

    @Autowired
    private AirportData airportDataExcelImpl;

    public AirportDataExcelImplTest() {
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
    public void testFetchAirportsInfo() throws Exception {
        log.info("testFetchAirportsInfo start");
        List<AirportInfo> result = airportDataExcelImpl.fetchAirportsInfo();
        assertNotNull(result);
        assertEquals(54795, result.size());
        log.info("testFetchAirportsInfo end");
    }

}
