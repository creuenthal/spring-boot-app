package io.reuenthal.rhove.repository;

import io.reuenthal.rhove.entities.StockedItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<StockedItem, Long> {
}
