package org.lightspeedsnail.entity;

import io.jmix.core.metamodel.datatype.impl.EnumClass;

import javax.annotation.Nullable;


public enum TypeOfContact implements EnumClass<String> {

    PHONE("phone"),
    EMAIL("email");

    private String id;

    TypeOfContact(String value) {
        this.id = value;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static TypeOfContact fromId(String id) {
        for (TypeOfContact at : TypeOfContact.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}