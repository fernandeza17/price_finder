package com.fernandeza.price_finder.infrastructure.adapters.input.rest;

import com.fernandeza.price_finder.application.ports.input.FindPriceUseCase;
import com.fernandeza.price_finder.domain.model.Price;
import com.fernandeza.price_finder.infrastructure.adapters.input.rest.data.response.FindPriceResponse;
import com.fernandeza.price_finder.infrastructure.adapters.input.rest.mapper.PriceRestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class PriceRestAdapter {

    private final FindPriceUseCase findPriceUseCase;

    private final PriceRestMapper priceRestMapper;

    @GetMapping(value = "/prices/{date}/{brandId}/{productId}")
    public ResponseEntity<FindPriceResponse> getProduct(@PathVariable LocalDateTime date, @PathVariable Long brandId, @PathVariable Long productId){
        Price price = findPriceUseCase.findPrice(date, brandId, productId);
        return new ResponseEntity<>(priceRestMapper.toFindPriceResponse(price), HttpStatus.OK);
    }
}
