package io.reuenthal.rhove.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Item {

    @Id
    private String id;
    private String name;

    @Enumerated(EnumType.STRING)
    private Category category;
    private Boolean perishable;

    public Item() {}

    public Item(String id) {
        this.id = id;
    }

    public Item(String id, String name, Category category, Boolean perishable) {
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
        Item item = (Item) o;
        return id.equals(item.id) &&
                Objects.equals(name, item.name) &&
                category == item.category &&
                Objects.equals(perishable, item.perishable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, category, perishable);
    }
}
