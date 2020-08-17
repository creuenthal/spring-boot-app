package io.reuenthal.rhove.service;

import io.reuenthal.rhove.entities.StockedItem;
import io.reuenthal.rhove.repository.InventoryRepository;
import io.reuenthal.rhove.repository.ItemRepository;
import io.reuenthal.rhove.entities.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class InventoryService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    public StockedItem storeItem(Item item) {
        insert(item);
        StockedItem stockedItem = new StockedItem(item.getId(), Instant.now().getEpochSecond());
        return inventoryRepository.save(stockedItem);
    }

    public List<StockedItem> inventory() {
        return inventoryRepository.findAll();
    }

    public List<Item> listItems() {
        return itemRepository.findAll();
    }

    private Item insert(Item item) {
        return itemRepository.findById(item.getId()).isEmpty() ? itemRepository.save(item) : item;
    }
}
