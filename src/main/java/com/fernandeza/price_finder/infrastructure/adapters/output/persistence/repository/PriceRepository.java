package com.fernandeza.price_finder.infrastructure.adapters.output.persistence.repository;

import com.fernandeza.price_finder.infrastructure.adapters.output.persistence.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends JpaRepository<PriceEntity, Long> {

}
