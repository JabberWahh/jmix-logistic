package org.lightspeedsnail.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@JmixEntity
@Table(name = "ORDER_DETAIL", indexes = {
        @Index(name = "IDX_ORDERDETAIL_PRODUCT_ID", columnList = "PRODUCT_ID"),
        @Index(name = "IDX_ORDERDETAIL_ORDER_ID", columnList = "ORDER_ID")
})
@Entity
public class OrderDetail {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @JoinColumn(name = "PRODUCT_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Product product;

    @Column(name = "QUANTITY", nullable = false)
    @NotNull
    private Integer quantity;

    @Column(name = "PRICE", nullable = false)
    @NotNull
    private Double price;

    @JoinColumn(name = "ORDER_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

    @NotNull
    @Column(name = "SUM_")
    private Double sum;

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}