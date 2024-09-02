package com.fernandeza.price_finder.application.ports.output;

import com.fernandeza.price_finder.domain.model.Price;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceOutputPort {

    Optional<Price> findPrice(LocalDateTime date, Long brandId, Long productId);
}
