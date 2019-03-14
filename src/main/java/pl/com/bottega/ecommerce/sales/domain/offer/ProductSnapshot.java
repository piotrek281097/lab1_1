package pl.com.bottega.ecommerce.sales.domain.offer;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class ProductSnapshot {

    private String id;
    //private BigDecimal price;
    Money price;
    private String name;
    private Date snapshotDate;
    private String type;

    ProductSnapshot(String id, Money price, String name, Date snapshotDate, String type)
    {
        this.id = id;
        this.price = price;
        this.name = name;
        this.snapshotDate = snapshotDate;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public Money getMoney() {
        return price;
    }

    public String getName() {
        return name;
    }

    public Date getSnapshotDate() {
        return snapshotDate;
    }

    public String getType() {
        return type;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ProductSnapshot that = (ProductSnapshot) o;
        return Objects.equals(id, that.id) && Objects.equals(price, that.price) && Objects.equals(name, that.name) && Objects.equals(
                snapshotDate, that.snapshotDate) && Objects.equals(type, that.type);
    }

    @Override public int hashCode() {
        return Objects.hash(id, price, name, snapshotDate, type);
    }
}
