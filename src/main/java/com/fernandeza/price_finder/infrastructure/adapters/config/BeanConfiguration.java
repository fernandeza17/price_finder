package com.fernandeza.price_finder.infrastructure.adapters.config;

import com.fernandeza.price_finder.application.ports.output.PriceOutputPort;
import com.fernandeza.price_finder.domain.service.PriceService;
import com.fernandeza.price_finder.infrastructure.adapters.output.persistence.PricePersistenceAdapter;
import com.fernandeza.price_finder.infrastructure.adapters.output.persistence.repository.PriceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public PricePersistenceAdapter pricePersistenceAdapter(PriceRepository priceRepository) {
        return new PricePersistenceAdapter(priceRepository);
    }

    @Bean
    public PriceService priceService(PriceOutputPort priceOutputPort) {
        return new PriceService(priceOutputPort);

    }
}
