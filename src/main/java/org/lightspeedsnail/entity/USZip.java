package org.lightspeedsnail.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@JmixEntity
@Table(name = "US_ZIP")
@Entity
public class USZip {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @Column(name = "ZIP", nullable = false, unique = true)
    @NotNull
    private Integer zip;

    @NotNull
    @Column(name = "LAT", nullable = false)
    private Double lat;

    @Column(name = "LNG")
    private Double lng;

    @Column(name = "CITY")
    private String city;

    @NotNull
    @Column(name = "STATE_NAME", nullable = false)
    private String state_name;

    @Column(name = "POPULATION")
    private Integer population;

    @Column(name = "STATE_ID", nullable = false, length = 5)
    @NotNull
    private String state_id;

    @Column(name = "COUNTY_FIPS")
    private Integer county_fips;

    @Column(name = "COUNTY_NAME")
    private String county_name;

    @Column(name = "COUNTY_NAMES_ALL")
    private String county_names_all;

    @Column(name = "TIMEZONE")
    private String timezone;

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLat() {
        return lat;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getLng() {
        return lng;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getCounty_names_all() {
        return county_names_all;
    }

    public void setCounty_names_all(String county_names_all) {
        this.county_names_all = county_names_all;
    }

    public String getCounty_name() {
        return county_name;
    }

    public void setCounty_name(String county_name) {
        this.county_name = county_name;
    }

    public Integer getCounty_fips() {
        return county_fips;
    }

    public void setCounty_fips(Integer county_fips) {
        this.county_fips = county_fips;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public String getState_id() {
        return state_id;
    }

    public void setState_id(String state_id) {
        this.state_id = state_id;
    }

    public String getState_name() {
        return state_name;
    }

    public void setState_name(String state_name) {
        this.state_name = state_name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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
}