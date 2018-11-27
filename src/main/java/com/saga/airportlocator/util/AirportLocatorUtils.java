/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saga.airportlocator.util;

import com.saga.airportlocator.constant.DistanceUnit;

/**
 *
 * @author sjadhav
 */
public class AirportLocatorUtils {

    /* M=Miles; K=Kilometers; N=Nautical Miles ; By default it gives distance in Kilometers*/
    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2, DistanceUnit unit) {
        if ((lat1 == lat2) && (lon1 == lon2)) {
            return 0;
        } else {
            double theta = lon1 - lon2;
            double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1))
                    * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
            dist = Math.acos(dist);
            dist = Math.toDegrees(dist);
            dist = dist * 60 * 1.1515;
            if (null == unit) {
                dist = dist * 1.609344;
            } else switch (unit) {
                case NM:
                    dist = dist * 0.8684;
                    break;
                case MI:
                    return (Math.round(dist));
                default:
                    dist = dist * 1.609344;
                    break;
            }
            return (Math.round(dist));
        }
    }
}
