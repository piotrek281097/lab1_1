/*
 * Copyright 2011-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package pl.com.bottega.ecommerce.sales.domain.offer;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class OfferItem {


    private ProductSnapshot productSnapshot;

    private int quantity;

    private Money totalCost;

    private Discount discount;

    public OfferItem(ProductSnapshot productSnapshot, int quantity) {
        this(productSnapshot, quantity, null, null);
    }

    public OfferItem(ProductSnapshot productSnapshot, int quantity, Discount discount, Money totalcost) {

        this.productSnapshot = productSnapshot;
        this.quantity = quantity;
        this.discount = discount;
        this.totalCost = totalcost;

        BigDecimal discountValue = new BigDecimal(0);
        if (discount != null) {
            discountValue = discountValue.add(discount.getDiscount());
        }

        this.totalCost.setValue(productSnapshot.getMoney().getValue().multiply(new BigDecimal(quantity))
                                     .subtract(discountValue));
    }

    public ProductSnapshot getProductSnapshot() {
        return productSnapshot;
    }

    public Money getTotalCost() {
        return totalCost;
    }

    public String getTotalCostCurrency() {
        return productSnapshot.getMoney().getCurrency();
    }

    public Discount getDiscount()
    {
        return discount;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productSnapshot.getMoney().getCurrency(), discount,
                productSnapshot, quantity, totalCost);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        OfferItem other = (OfferItem) obj;
        return Objects.equals(productSnapshot.getMoney().getCurrency(), other.productSnapshot.getMoney().getCurrency())
               && Objects.equals(discount, other.discount)
               && Objects.equals(productSnapshot, other.productSnapshot)
               && quantity == other.quantity
               && Objects.equals(totalCost, other.totalCost);
    }

    /**
     *
     * @param item
     * @param delta
     *            acceptable percentage difference
     * @return
     */
    public boolean sameAs(OfferItem other, double delta) {
        if (productSnapshot== null) {
            if (other.productSnapshot != null) {
                return false;
            }
        } else if (!productSnapshot.equals(other.productSnapshot)) {
            return false;
        }

        if (quantity != other.quantity) {
            return false;
        }

        BigDecimal max;
        BigDecimal min;
        if (totalCost.compareTo(other.totalCost) > 0) {
            max = totalCost.getValue();
            min = other.totalCost.getValue();
        } else {
            max = other.totalCost.getValue();
            min = totalCost.getValue();
        }

        BigDecimal difference = max.subtract(min);
        BigDecimal acceptableDelta = max.multiply(BigDecimal.valueOf(delta / 100));

        return acceptableDelta.compareTo(difference) > 0;
    }

}
