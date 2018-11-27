/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saga.airportlocator.model;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.annotation.Validated;

/**
 *
 * @author sjadhav
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class NearestAirportRequest {

    @NotNull(message = "latitude is required")
    @Range(min = -90, max = 90, message = "Latitude not valid")
    Double latitude;
    @NotNull(message = "longitude is required")
    @Range(min = -180, max = 180, message = "Longitude not valid")
    Double longitude;
    Integer noOfAirports;
}
