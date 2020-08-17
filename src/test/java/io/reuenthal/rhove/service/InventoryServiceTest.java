package io.reuenthal.rhove.service;

import io.reuenthal.rhove.entities.Category;
import io.reuenthal.rhove.entities.Product;
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
        List<Product> expected = Arrays.asList(new Product("test1"), new Product("test2"));
        expected.forEach(service::storeItem);
        List<Product> products = service.itemCatalog();
        Assertions.assertIterableEquals(expected, products);
    }

    @Test
    public void shouldPersistEveryFieldInAnItem() {
        Product apple = new Product("id", "Honeycrisp Apple", Category.FRUIT, true);
        service.storeItem(apple);
        List<Product> products = service.itemCatalog();
        Assertions.assertEquals(apple, products.get(0));
    }

    @Test
    public void shouldNotDoubleUpOnItems() {
        Product test = new Product("id", "Test", Category.VEGETABLE, true);
        service.storeItem(test);
        service.storeItem(test);

        List<Product> products = service.itemCatalog();
        Assertions.assertEquals(1, products.size());
        Assertions.assertEquals(test, products.get(0));
    }

    @Test
    public void shouldManageInventoryByDate() {
        Product test = new Product("id", "Test", Category.VEGETABLE, true);
        StockedItem first = service.storeItem(test);
        StockedItem second = service.storeItem(test);

        List<StockedItem> inventory = service.inventory();
        Assertions.assertEquals(2, inventory.size());
        Assertions.assertEquals(first, inventory.get(0));
        Assertions.assertEquals(second, inventory.get(1));
    }
}
