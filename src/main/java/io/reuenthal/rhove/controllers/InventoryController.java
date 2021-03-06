package io.reuenthal.rhove.controllers;

import io.reuenthal.rhove.entities.Inventory;
import io.reuenthal.rhove.entities.Product;
import io.reuenthal.rhove.entities.ProductList;
import io.reuenthal.rhove.entities.StockedItem;
import io.reuenthal.rhove.service.InventoryService;
import io.reuenthal.rhove.service.ProductLookupService;
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

    @Autowired
    private ProductLookupService productLookupService;

    @PostMapping("/inventory")
    public StockedItem storeItem(@RequestParam String barcode) {
        Product product = productLookupService.lookup(barcode);
        return inventoryService.storeItem(product);
    }

    @GetMapping("/inventory")
    public Inventory getInventory() {
        return new Inventory(inventoryService.inventory());
    }

    @GetMapping("/catalog")
    public ProductList getCatalog() {
        return new ProductList(inventoryService.itemCatalog());
    }
}
