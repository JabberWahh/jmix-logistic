package org.lightspeedsnail.entity;

import io.jmix.core.metamodel.datatype.impl.EnumClass;

import javax.annotation.Nullable;


public enum OrderStatus implements EnumClass<String> {

    RECENT("Recent"),
    IN_PROGRESS("In progress"),
    SUCCESS("Success"),
    CANCELED("Canceled");

    private String id;

    OrderStatus(String value) {
        this.id = value;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static OrderStatus fromId(String id) {
        for (OrderStatus at : OrderStatus.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}