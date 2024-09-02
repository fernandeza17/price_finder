package com.fernandeza.price_finder.infrastructure.adapters.input.rest.mapper;

import com.fernandeza.price_finder.domain.model.Price;
import com.fernandeza.price_finder.infrastructure.adapters.input.rest.data.response.FindPriceResponse;
import org.mapstruct.Mapper;

@Mapper
public interface PriceRestMapper {

    FindPriceResponse toFindPriceResponse(Price price);

}
