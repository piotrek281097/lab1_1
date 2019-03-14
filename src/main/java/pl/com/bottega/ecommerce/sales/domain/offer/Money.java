package pl.com.bottega.ecommerce.sales.domain.offer;

import java.math.BigDecimal;

public class Money implements Comparable<Money>{
    private BigDecimal value;
    private String currency;

    public Money(BigDecimal value, String currency) {
        this.value = value;
        this.currency = currency;
    }

    public BigDecimal getValue() {
        return value;
    }

    public String getCurrency() {
        return currency;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override public int compareTo(Money o) {
        if(this.value.compareTo(o.getValue()) < 0) {
            return -1;
        }
        else if(this.value.compareTo(o.getValue()) > 0) {
            return 1;
        }
        else {
            return 0;
        }
    }
}
