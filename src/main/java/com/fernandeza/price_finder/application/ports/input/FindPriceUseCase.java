package com.fernandeza.price_finder.application.ports.input;

import com.fernandeza.price_finder.domain.model.Price;

import java.time.LocalDate;

public interface FindPriceUseCase {

    Price findPrice(LocalDate date, Long brandId, Long productId);
}
