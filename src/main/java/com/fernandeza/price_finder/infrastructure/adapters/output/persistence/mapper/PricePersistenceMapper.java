package com.fernandeza.price_finder.infrastructure.adapters.output.persistence.mapper;

import com.fernandeza.price_finder.domain.model.Price;
import com.fernandeza.price_finder.infrastructure.adapters.output.persistence.entity.PriceEntity;
import org.mapstruct.Mapper;

@Mapper
public interface PricePersistenceMapper {

    Price toPrice(PriceEntity priceEntity);
}
