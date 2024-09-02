package com.fernandeza.price_finder.infrastructure.adapters.output.persistence;

import com.fernandeza.price_finder.application.ports.output.PriceOutputPort;
import com.fernandeza.price_finder.domain.model.Price;
import com.fernandeza.price_finder.infrastructure.adapters.output.persistence.repository.PriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.context.annotation.RequestScope;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
public class PricePersistenceAdapter implements PriceOutputPort {

    private final PriceRepository priceRepository;

    @Override
    public Optional<Price> findPrice(LocalDateTime date, Long brandId, Long productId) {
        //TODO: implement findPrice method with specific parameters
        return null;
    }
}
