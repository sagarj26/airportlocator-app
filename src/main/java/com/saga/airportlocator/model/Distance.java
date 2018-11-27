/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saga.airportlocator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author sjadhav
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Distance {

    private Double value;
    private String unit;
}
