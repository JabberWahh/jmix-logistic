package org.lightspeedsnail.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.DependsOnProperties;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;
import java.util.UUID;

@JmixEntity
@Table(name = "ADDRESS", indexes = {
        @Index(name = "IDX_ADDRESS_CLIENT_ID", columnList = "CLIENT_ID")
})
@Entity
public class Address {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @Column(name = "COUNTY")
    private String county;

    @Column(name = "ZIP")
    private Integer zip;

    @Column(name = "STATE")
    private String state;

    @Column(name = "CITY")
    private String city;

    @Column(name = "ADRESS1")
    private String address1;

    @Column(name = "ADRESS2")
    private String address2;

    @JoinColumn(name = "CLIENT_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Client client;

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String adress2) {
        this.address2 = adress2;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String adress1) {
        this.address1 = adress1;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getZip() {
        return zip;
    }

    public void setZip(Integer zip) {
        this.zip = zip;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @InstanceName
    @DependsOnProperties({"zip", "state", "county", "city", "address1", "address2"})
    public String getInstanceName() {
        return String.format("%s, %s, %s, %s, %s, %s", zip, state, county, city, address1, address2);
    }
}