package ru.bellintegrator.practice.office.model;

import ru.bellintegrator.practice.organization.model.Organization;
import ru.bellintegrator.practice.user.model.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
    @Column(nullable = false, length = 50)
    private String name;

    /**
     * Адрес
     */
    @Column(nullable = false, length = 50)
    private String address;

    /**
     * Телефон
     */
    @Column(nullable = false, length = 12)
    private String phone;

    /**
     * Активность
     */
    @Column(name = "is_active")
    private Boolean isActive;

    /**
     * Организация офиса
     *
     * @see Organization
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "org_id")
    private Organization organization;

    /**
     * Пользователи офиса
     *
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
     * @param name     Название офиса
     * @param address  Адрес
     * @param phone    Телефон
     * @param isActive Активность
     */
    public Office(String name, String address, String phone, boolean isActive) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.isActive = isActive;
    }

    /**
     * Конструктор - создание объекта Office со следующими полями
     *
     * @param id       Ид офиса
     * @param name     Название офиса
     * @param address  Адрес
     * @param phone    Телефон
     * @param isActive Активность
     */
    public Office(Long id, String name, String address, String phone, boolean isActive) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Boolean getIsActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Organization getOrganization() {
        return organization;
    }

    public Set<User> getUsers() {
        return users;
    }
}
