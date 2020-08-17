package io.reuenthal.rhove.service;

import io.reuenthal.rhove.entities.Product;
import io.reuenthal.rhove.entities.StockedItem;
import io.reuenthal.rhove.repository.InventoryRepository;
import io.reuenthal.rhove.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class InventoryService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    public StockedItem storeItem(Product product) {
        insert(product);
        StockedItem stockedItem = new StockedItem(product.getId(), Instant.now().getEpochSecond());
        return inventoryRepository.save(stockedItem);
    }

    public List<StockedItem> inventory() {
        return inventoryRepository.findAll();
    }

    public List<Product> itemCatalog() {
        return productRepository.findAll();
    }

    private Product insert(Product product) {
        return productRepository.findById(product.getId()).isEmpty() ? productRepository.save(product) : product;
    }
}
