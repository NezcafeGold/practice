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

/**
 * Офис
 */
@Entity
public class Office {

    /**
     * Первичный ключ id
     */
    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long id;

    /**
     * Служебное поле hibernate
     */
    @Version
    private Integer version;

    /**
     * Название офиса
     */
    @Column(nullable = false)
    private String name;

    /**
     * Адрес
     */
    @Column(nullable = false)
    private String address;

    /**
     * Телефон
     */
    private String phone;

    /**
     * Активность
     */
    @Column(name = "is_active")
    private boolean isActive;

    /**
     * Связь многие к одному с организацией
     * @see Organization
     */
    @ManyToOne
    @JoinColumn(name = "org_id")
    private Organization organization;

    /**
     * Связь один ко многим с пользователями
     * @see User
     */
    @OneToMany(mappedBy = "office")
    private Set<User> users;

    /**
     * Пустой конструктор для hibernate
     */
    public Office() {
    }

    /**
     * Конструктор - создание объекта Office со следующими полями
     *
     * @param name     - Название офиса
     * @param address  - Адрес
     * @param phone    - Телефон
     * @param isActive - Активность
     */
    public Office(String name, String address, String phone, boolean isActive) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.isActive = isActive;
    }

    /**
     * Геттеры и сеттеры для полей
     */
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
