package com.fernandeza.price_finder.application.ports.input;

import com.fernandeza.price_finder.domain.model.Price;

import java.time.LocalDateTime;

public interface FindPriceUseCase {

    Price findPriceByDateBrandAndProduct(LocalDateTime date, Long brandId, Long productId);
}
