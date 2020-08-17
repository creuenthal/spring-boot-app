package io.reuenthal.rhove.service;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import io.reuenthal.rhove.entities.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class ProductLookupService {

    public Product lookup(String upcBarcode) {
        Product product = new Product(upcBarcode);
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("https://api.edamam.com/api/food-database/v2/parser");
        builder.queryParam("app_id", "01c411bb");
        builder.queryParam("app_key", "93f0f3f3759bab34facf5a6d1e792661");
        builder.queryParam("upc", upcBarcode);
        RestTemplate restTemplate = new RestTemplate();

        String json = restTemplate.getForObject(builder.toUriString(), String.class);
        DocumentContext documentContext = JsonPath.parse(json);

        List<String> label = documentContext.read("$.hints[*].food.label");
        //TODO: Find a better API with real categories. Alt: Write own catalog manager and update as needed
        //String category = documentContext.read("$.hints[*].food.category");
        product.setName(label.get(0)); //First Label should (1) be there, and (2) be what we want per docs.
        return product;
    }
}
