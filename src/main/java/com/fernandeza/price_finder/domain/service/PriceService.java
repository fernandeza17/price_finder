package com.fernandeza.price_finder.domain.service;

import com.fernandeza.price_finder.application.ports.input.FindPriceUseCase;
import com.fernandeza.price_finder.application.ports.output.PriceOutputPort;
import com.fernandeza.price_finder.domain.exception.PriceNotFoundException;
import com.fernandeza.price_finder.domain.model.Price;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class PriceService implements FindPriceUseCase {

    private final PriceOutputPort priceOutputPort;

    @Override
    public Price findPriceByDateBrandAndProduct(LocalDateTime date, Long brandId, Long productId) {
        List<Price> prices = priceOutputPort.findPriceByDateBrandAndProduct(date, brandId, productId);

        checkPriceNotFound(prices);

        return getResultPriceByPriority(prices);
    }

    private void checkPriceNotFound(List<Price> prices) {
        if(prices.isEmpty()) {
            throw new PriceNotFoundException("Price not found");
        }
    }

    private Price getResultPriceByPriority(List<Price> prices) {
        // When having prices with the same priority we will return the first in the list since no other criteria was provided.
        return prices.stream()
                .sorted(Comparator.comparingInt(Price::getPriority))
                .collect(Collectors.toList())
                .get(0);
    }
}
