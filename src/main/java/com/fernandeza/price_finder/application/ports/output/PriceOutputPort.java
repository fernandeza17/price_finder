package com.fernandeza.price_finder.application.ports.output;

import com.fernandeza.price_finder.domain.model.Price;

import java.time.LocalDate;
import java.util.Optional;

public interface PriceOutputPort {

    Optional<Price> findPrice(LocalDate date, Long brandId, Long productId);
}
