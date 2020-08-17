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
    private String productKey;
    private Long timeAcquired;

    public StockedItem() {}

    public StockedItem(String productKey, Long timeAcquired) {
        this.productKey = productKey;
        this.timeAcquired = timeAcquired;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductKey() {
        return productKey;
    }

    public void setProductKey(String productKey) {
        this.productKey = productKey;
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
                Objects.equals(productKey, that.productKey) &&
                Objects.equals(timeAcquired, that.timeAcquired);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productKey, timeAcquired);
    }
}
