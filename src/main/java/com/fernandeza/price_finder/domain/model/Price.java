package com.fernandeza.price_finder.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class Price {

    private long id;
    private long brandId;
    private LocalDate startDate;
    private LocalDate endDate;
    private long productId;
    private int priority;
    private double value;
    private String currency;
}
