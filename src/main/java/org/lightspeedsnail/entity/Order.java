package org.lightspeedsnail.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.DependsOnProperties;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@JmixEntity
@Table(name = "ORDER_")
@Entity(name = "Order_")
public class Order implements DocumentsAndReferences, Numbering {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @Column(name = "STATUS", nullable = false)
    @NotNull
    private String status;

    @Column(name = "CREATED", nullable = false)
    @NotNull
    private LocalDateTime created = LocalDateTime.now();

    @NotNull
    @JoinColumn(name = "CLIENT_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Client client;

    @Column(name = "DISTANCE", nullable = false)
    @NotNull
    private Double distance;

    @Column(name = "ADDRESS", nullable = false)
    @NotNull
    private String address;

    @Column(name = "NUMBER_")
    private String number;

    @OneToMany(mappedBy = "order")
    private List<OrderDetail> order_detail;

    @Column(name = "DELETED", nullable = false)
    @NotNull
    private Boolean deleted = false;

    public List<OrderDetail> getOrder_detail() {
        return order_detail;
    }

    public void setOrder_detail(List<OrderDetail> order_detail) {
        this.order_detail = order_detail;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getNumber() {
        return number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Client getClient() {
        return client;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setUnicNumber(String number) {
        this.number = number;
    }

    public OrderStatus getStatus() {
        return status == null ? null : OrderStatus.fromId(status);
    }

    public void setStatus(OrderStatus status) {
        this.status = status == null ? null : status.getId();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @InstanceName
    @DependsOnProperties({"number", "created"})
    public String getInstanceName() {
        return String.format("%s %s", number, created);
    }
}