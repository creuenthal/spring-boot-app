package io.reuenthal.rhove.entities;

import java.util.List;

public class Inventory {
    private List<StockedItem> inventory;

    public Inventory() {}

    public Inventory(List<StockedItem> inventory) {
        this.inventory = inventory;
    }

    public List<StockedItem> getInventory() {
        return inventory;
    }

    public void setInventory(List<StockedItem> inventory) {
        this.inventory = inventory;
    }
}
