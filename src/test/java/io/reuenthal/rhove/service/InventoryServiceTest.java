package io.reuenthal.rhove.service;

import io.reuenthal.rhove.entities.Category;
import io.reuenthal.rhove.entities.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class InventoryServiceTest {

    @Autowired
    private InventoryService service;

    @Test
    public void shouldRetrieveItemsAsTheyAreStored() {
        List<Item> expected = Arrays.asList(new Item("test1"), new Item("test2"));
        expected.forEach(service::insert);
        List<Item> items = service.list();
        Assertions.assertIterableEquals(items, expected);
    }

    @Test
    public void shouldPersistEveryFieldInAnItem() {
        Item apple = new Item("id", "Honeycrisp Apple", Category.FRUIT, true);
        service.insert(apple);
        List<Item> items = service.list();
        Assertions.assertEquals(items.get(0), apple);
    }
}
