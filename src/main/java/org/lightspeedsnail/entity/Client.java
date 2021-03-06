package org.lightspeedsnail.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@JmixEntity
@Table(name = "CLIENT")
@Entity
public class Client implements DocumentsAndReferences, Numbering {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @OneToMany(mappedBy = "client")
    private List<Address> address;

    @InstanceName
    @Column(name = "NAME", nullable = false)
    @NotNull
    private String name;

    @Column(name = "COMMENTS")
    @Lob
    private String comments;

    @Column(name = "REGISTRATION_DATE")
    private LocalDateTime registrationDate;

    @Column(name = "NUMBER_", unique = true)
    private String number;

    @Column(name = "DELETED", nullable = false, columnDefinition = "boolean default false")
    @NotNull
    private Boolean deleted = false;

    @OneToMany(mappedBy = "client")
    private Set<ClientContact> clientContact;

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }

    public Set<ClientContact> getClientContact() {
        return clientContact;
    }

    public void setClientContact(Set<ClientContact> clientConnection) {
        this.clientContact = clientConnection;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
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

    public void setUnicNumber(String number) {
        this.number = number;
    }

    public void setFirstRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}