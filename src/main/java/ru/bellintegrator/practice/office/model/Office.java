package ru.bellintegrator.practice.office.model;

import ru.bellintegrator.practice.organization.model.Organization;
import ru.bellintegrator.practice.user.model.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import java.util.Set;

@Entity
public class Office {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long id;

    @Version
    private Integer version;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    private String phone;

    @Column(name = "is_active")
    private boolean isActive;

    @ManyToOne
    @JoinColumn(name="org_id")
    private Organization organization;

    @OneToMany(mappedBy = "office")
    private Set<User> users;

    public Office() {
    }

    public Office(String name, String address, String phone, boolean isActive) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Organization getOrganization() {
        return organization;
    }

    public Set<User> getUsers() {
        return users;
    }
}
