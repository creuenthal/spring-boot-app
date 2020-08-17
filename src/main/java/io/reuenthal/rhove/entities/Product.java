package io.reuenthal.rhove.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Product {

    @Id
    private String id;
    private String name;

    @Enumerated(EnumType.STRING)
    private Category category;
    private Boolean perishable;

    public Product() {}

    public Product(String id) {
        this.id = id;
    }

    public Product(String id, String name, Category category, Boolean perishable) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.perishable = perishable;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Boolean getPerishable() {
        return perishable;
    }

    public void setPerishable(Boolean perishable) {
        this.perishable = perishable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id.equals(product.id) &&
                Objects.equals(name, product.name) &&
                category == product.category &&
                Objects.equals(perishable, product.perishable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, category, perishable);
    }
}
