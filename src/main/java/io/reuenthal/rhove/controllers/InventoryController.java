package io.reuenthal.rhove.controllers;

import io.reuenthal.rhove.entities.Product;
import io.reuenthal.rhove.entities.StockedItem;
import io.reuenthal.rhove.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @PostMapping("/inventory")
    public StockedItem storeItem(@RequestParam String barcode) {
        //TODO: Lookup product barcode through some API to build a useful object
        Product product = new Product(barcode);

        return inventoryService.storeItem(product);
    }

    @GetMapping("/inventory")
    public List<StockedItem> getInventory() {
        return inventoryService.inventory();
    }

    @GetMapping("/catalog")
    public List<Product> getCatalog() {
        return inventoryService.itemCatalog();
    }
}
