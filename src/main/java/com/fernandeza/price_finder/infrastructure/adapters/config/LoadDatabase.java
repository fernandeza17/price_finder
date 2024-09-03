package com.fernandeza.price_finder.infrastructure.adapters.config;

import com.fernandeza.price_finder.infrastructure.adapters.output.persistence.entity.PriceEntity;
import com.fernandeza.price_finder.infrastructure.adapters.output.persistence.repository.PriceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(PriceRepository repository) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");
        return args -> {
            log.info("Preloading " + repository.save(new PriceEntity(1L, LocalDateTime.parse("2020-06-14-00.00.00", formatter),
                    LocalDateTime.parse("2020-12-31-23.59.59", formatter),1L,35455L,0,35.50,"EUR")));

            log.info("Preloading " + repository.save(new PriceEntity(1L, LocalDateTime.parse("2020-06-14-15.00.00", formatter),
                    LocalDateTime.parse("2020-06-14-18.30.00", formatter),2L,35455L,1,24.45,"EUR")));

            log.info("Preloading " + repository.save(new PriceEntity(1L, LocalDateTime.parse("2020-06-15-00.00.00", formatter),
                    LocalDateTime.parse("2020-06-15-11.00.00", formatter),3L,35455L,1,30.50,"EUR")));

            log.info("Preloading " + repository.save(new PriceEntity(1L, LocalDateTime.parse("2020-06-15-16.00.00", formatter),
                    LocalDateTime.parse("2020-12-31-23.59.59", formatter),4L,35455L,1,38.95,"EUR")));
        };
    }
}
