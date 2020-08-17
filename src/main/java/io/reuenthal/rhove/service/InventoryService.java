package io.reuenthal.rhove.service;

import io.reuenthal.rhove.repository.InventoryRepository;
import io.reuenthal.rhove.entities.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository repository;

    public List<Item> list() {
        return repository.findAll();
    }

    public Item insert(Item item) {
        return repository.save(item);
    }
}
