package com.fernandeza.price_finder.application.ports.output;

import com.fernandeza.price_finder.domain.model.Price;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceOutputPort {

    List<Price> findPriceByDateBrandAndProduct(LocalDateTime date, Long brandId, Long productId);
}
