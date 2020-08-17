package io.reuenthal.rhove.service;

import io.reuenthal.rhove.entities.Category;
import io.reuenthal.rhove.entities.Item;
import io.reuenthal.rhove.entities.StockedItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class InventoryServiceTest {

    @Autowired
    private InventoryService service;

    @Test
    public void shouldRetrieveItemsAsTheyAreStored() {
        List<Item> expected = Arrays.asList(new Item("test1"), new Item("test2"));
        expected.forEach(service::storeItem);
        List<Item> items = service.listItems();
        Assertions.assertIterableEquals(expected, items);
    }

    @Test
    public void shouldPersistEveryFieldInAnItem() {
        Item apple = new Item("id", "Honeycrisp Apple", Category.FRUIT, true);
        service.storeItem(apple);
        List<Item> items = service.listItems();
        Assertions.assertEquals(apple, items.get(0));
    }

    @Test
    public void shouldNotDoubleUpOnItems() {
        Item test = new Item("id", "Test", Category.VEGETABLE, true);
        service.storeItem(test);
        service.storeItem(test);

        List<Item> items = service.listItems();
        Assertions.assertEquals(1, items.size());
        Assertions.assertEquals(test, items.get(0));
    }

    @Test
    public void shouldManageInventoryByDate() {
        Item test = new Item("id", "Test", Category.VEGETABLE, true);
        StockedItem first = service.storeItem(test);
        StockedItem second = service.storeItem(test);

        List<StockedItem> inventory = service.inventory();
        Assertions.assertEquals(2, inventory.size());
        Assertions.assertEquals(first, inventory.get(0));
        Assertions.assertEquals(second, inventory.get(1));
    }
}
