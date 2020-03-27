package com.openx.recruitment.task.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.MathContext;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeoLocation {

    private BigDecimal lat;
    private BigDecimal lng;

    public BigDecimal calculateDistance(GeoLocation geoLocation) {
        var precision = MathContext.DECIMAL64;
        var x = getLat().subtract(geoLocation.getLat(), precision);
        var y = getLng().subtract(geoLocation.getLng(), precision);
        return (x.pow(2).add(y.pow(2))).sqrt(precision);
    }

}
