package com.fernandeza.price_finder.infrastructure.adapters.output.persistence.repository;

import com.fernandeza.price_finder.infrastructure.adapters.output.persistence.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collection;

@Repository
public interface PriceRepository extends JpaRepository<PriceEntity, Long> {

    @Query("select p from PriceEntity p where (?1 between p.startDate and p.endDate) and p.brandId = ?2 and p.productId = ?3")
    Collection<PriceEntity> findPriceByDateBrandAndProduct(LocalDateTime date, Long brandId, Long productId);

}
