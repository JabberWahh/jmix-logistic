package org.lightspeedsnail.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@JmixEntity
@Table(name = "CLIENT_CONTACT")
@Entity
public class ClientContact {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;


    @Column(name = "TYPE_OF_CONTACT", nullable = false)
    @NotNull
    private String typeOfContact;

    @Column(name = "INFO", nullable = false)
    @NotNull
    private String info;

    @Column(name = "PRIMARY_TYPE")
    private Boolean primaryType = false;

    @JoinColumn(name = "CLIENT_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Client client;

    public TypeOfContact getTypeOfContact() {
        return typeOfContact == null ? null : TypeOfContact.fromId(typeOfContact);
    }

    public void setTypeOfContact(TypeOfContact typeOfContact) {
        this.typeOfContact = typeOfContact == null ? null : typeOfContact.getId();
    }


    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Boolean getPrimaryType() {
        return primaryType;
    }

    public void setPrimaryType(Boolean primaryType) {
        this.primaryType = primaryType;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}