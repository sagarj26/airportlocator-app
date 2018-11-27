package com.saga.airportlocator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saga.airportlocator.dao.AirportData;
import com.saga.airportlocator.model.AirportInfo;
import com.saga.airportlocator.model.AirportRelativeDistance;
import com.saga.airportlocator.model.Distance;
import com.saga.airportlocator.model.NearestAirportRequest;
import com.saga.airportlocator.rest.impl.AirportLocatorApiImpl;
import java.util.Arrays;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.saga.airportlocator.service.AirportLocatorService;
import java.util.Optional;

@RunWith(SpringRunner.class)
//@SpringBootTest
@WebMvcTest(controllers = AirportLocatorApiImpl.class)
public class AirportlocatorApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    AirportLocatorService airportLocatorServiceImpl;

    ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setup() throws Exception {
        AirportInfo puneAirport = new AirportInfo(123, "VAPO", "small_airport", "Pune Airport", 18.582, 73.919, "In", "Pune");
        Distance puneDist = new Distance(12.00, "KM");
        AirportRelativeDistance puneAirportDistance = new AirportRelativeDistance(puneAirport, puneDist);
        AirportInfo mumbaiAirport = new AirportInfo(1234, "VABB", "big_airport", "CSI", 19.089, 72.868, "In", "Mubai");
        Distance mumbaiDist = new Distance(121.00, "KM");
        AirportRelativeDistance mumbaiAirportDistance = new AirportRelativeDistance(mumbaiAirport, mumbaiDist);
        when(airportLocatorServiceImpl.getNearestAirport(18.5204, 73.8567,Optional.of(2))).thenReturn(Arrays.asList(puneAirportDistance, mumbaiAirportDistance));
    }

    @Test
    public void getNearestAirports() throws Exception {
        NearestAirportRequest location = new NearestAirportRequest(18.5204, 73.8567, 2);
        mockMvc.perform(post("/public/references/airports/nearest").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(location)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(2))).andDo(print());
    }

    @Test
    public void getNearestAirportsWithoutLatlong() throws Exception {

        mockMvc.perform(post("/public/references/airports/nearest").contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors", hasSize(2)))
                .andExpect(jsonPath("$.errors[*]", containsInAnyOrder(
                        "latitude is required",
                        "longitude is required"
                )))
                .andDo(print());
    }

    @Test
    public void getNearestAirportsWithoutLatitude() throws Exception {

        NearestAirportRequest location = new NearestAirportRequest();
        location.setLongitude(73.8567);
        mockMvc.perform(post("/public/references/airports/nearest").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(location)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors", hasSize(1)))
                .andExpect(jsonPath("$.errors[*]", containsInAnyOrder(
                        "latitude is required"
                )))
                .andDo(print());
    }

    @Test
    public void getNearestAirportsWithoutLongitude() throws Exception {

        NearestAirportRequest location = new NearestAirportRequest();
        location.setLatitude(73.8567);
        mockMvc.perform(post("/public/references/airports/nearest").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(location)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors", hasSize(1)))
                .andExpect(jsonPath("$.errors[*]", containsInAnyOrder(
                        "longitude is required"
                )))
                .andDo(print());
    }

    @Test
    public void getNearestAirportsWithInvalidLongitude() throws Exception {

        NearestAirportRequest location = new NearestAirportRequest(18.5204, 181.90, 1);
        mockMvc.perform(post("/public/references/airports/nearest").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(location)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors", hasSize(1)))
                .andExpect(jsonPath("$.errors[*]", containsInAnyOrder(
                        "Longitude not valid"
                )))
                .andDo(print());
    }

    @Test
    public void getNearestAirportsWithInvalidLatitude() throws Exception {

        NearestAirportRequest location = new NearestAirportRequest(91.09, 73.8567, 0);
        mockMvc.perform(post("/public/references/airports/nearest").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(location)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors", hasSize(1)))
                .andExpect(jsonPath("$.errors[*]", containsInAnyOrder(
                        "Latitude not valid"
                )))
                .andDo(print());
    }

}
