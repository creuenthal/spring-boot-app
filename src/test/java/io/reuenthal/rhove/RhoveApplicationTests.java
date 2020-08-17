package io.reuenthal.rhove;

import io.reuenthal.rhove.entities.Inventory;
import io.reuenthal.rhove.entities.ProductList;
import io.reuenthal.rhove.entities.StockedItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RhoveApplicationTests {

	@Test
	void contextLoads() {
	}

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void shouldPostACokeAndRetrieveIt() {
		String description = "Coca-Cola Bottle, 2 Liters";
		String barcode = "049000050103";
		//Test Post & Product Lookup [Dependent on outside API; would mock this in test context in a real scenario]
		ResponseEntity<StockedItem> inserted = restTemplate.postForEntity("http://localhost:" + port + "/inventory?barcode=" + barcode, null, StockedItem.class);
		Assertions.assertTrue(inserted.getStatusCode().is2xxSuccessful());
		Assertions.assertEquals(barcode, inserted.getBody().getProductKey());

		//Test Catalog
		ResponseEntity<ProductList> products = restTemplate.getForEntity("http://localhost:" + port + "/catalog", ProductList.class);
		Assertions.assertTrue(products.getStatusCode().is2xxSuccessful());
		Assertions.assertEquals(1, products.getBody().getProducts().size());
		Assertions.assertEquals(description, products.getBody().getProducts().get(0).getName());

		//Test Inventory
		restTemplate.postForEntity("http://localhost:" + port + "/inventory?barcode=" + barcode, null, StockedItem.class);
		ResponseEntity<Inventory> inventory = restTemplate.getForEntity("http://localhost:" + port + "/inventory", Inventory.class);
		Assertions.assertTrue(inventory.getStatusCode().is2xxSuccessful());
		Assertions.assertEquals(2, inventory.getBody().getInventory().size());
	}
}