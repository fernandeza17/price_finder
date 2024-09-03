package com.fernandeza.price_finder.infrastructure.adapters.output.persistence;

import com.fernandeza.price_finder.application.ports.output.PriceOutputPort;
import com.fernandeza.price_finder.domain.model.Price;
import com.fernandeza.price_finder.infrastructure.adapters.output.persistence.entity.PriceEntity;
import com.fernandeza.price_finder.infrastructure.adapters.output.persistence.mapper.PricePersistenceMapper;
import com.fernandeza.price_finder.infrastructure.adapters.output.persistence.repository.PriceRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class PricePersistenceAdapter implements PriceOutputPort {

    private final PriceRepository priceRepository;

    private final PricePersistenceMapper pricePersistenceMapper;

    @Override
    public List<Price> findPriceByDateBrandAndProduct(LocalDateTime date, Long brandId, Long productId) {
        Collection<PriceEntity> priceEntityCollection = priceRepository.findPriceByDateBrandAndProduct(date, brandId, productId);

        return priceEntityCollection.stream()
                .map(priceEntity -> pricePersistenceMapper.toPrice(priceEntity))
                .collect(Collectors.toList());
    }
}
