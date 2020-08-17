package io.reuenthal.rhove.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class StockedItem {

    @Id
    @GeneratedValue()
    private Long id;
    private String itemKey;
    private Long timeAcquired;

    public StockedItem() {}

    public StockedItem(String itemKey, Long timeAcquired) {
        this.itemKey = itemKey;
        this.timeAcquired = timeAcquired;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemKey() {
        return itemKey;
    }

    public void setItemKey(String itemKey) {
        this.itemKey = itemKey;
    }

    public Long getTimeAcquired() {
        return timeAcquired;
    }

    public void setTimeAcquired(Long timeAcquired) {
        this.timeAcquired = timeAcquired;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockedItem that = (StockedItem) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(itemKey, that.itemKey) &&
                Objects.equals(timeAcquired, that.timeAcquired);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, itemKey, timeAcquired);
    }
}
