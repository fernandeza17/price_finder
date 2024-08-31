package com.fernandeza.price_finder.domain.service;

import com.fernandeza.price_finder.application.ports.input.FindPriceUseCase;
import com.fernandeza.price_finder.application.ports.output.PriceOutputPort;
import com.fernandeza.price_finder.domain.exception.PriceNotFoundException;
import com.fernandeza.price_finder.domain.model.Price;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
public class PriceService implements FindPriceUseCase {

    private final PriceOutputPort priceOutputPort;

    @Override
    public Price findPrice(LocalDate date, Long brandId, Long productId) {
        return priceOutputPort.findPrice(date, brandId, productId).orElseThrow(() -> new PriceNotFoundException("Price not found"));

    }
}
