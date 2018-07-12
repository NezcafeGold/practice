package ru.bellintegrator.practice.organization.model;

import ru.bellintegrator.practice.office.model.Office;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import java.util.Set;

/**
 * Организация
 */
@Entity
public class Organization {

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
     * Название организации
     */
    @Column(nullable = false)
    private String name;

    /**
     * Полное название организации
     */
    @Column(name = "full_name", nullable = false)
    private String fullName;

    /**
     * ИНН
     */
    @Column(nullable = false)
    private String inn;

    /**
     * КПП
     */
    @Column(nullable = false)
    private String kpp;

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
     * Связь один ко многим с офисом
     *
     * @see Office
     */
    @OneToMany(mappedBy = "organization")
    private Set<Office> offices;

    /**
     * Пустой конструктор для hibernate
     */
    public Organization() {
    }

    /**
     * Конструктор - создание объекта Office со следующими полями
     *
     * @param name     - Название организации
     * @param fullName - Полное название организации
     * @param inn      - ИНН
     * @param kpp      - КПП
     * @param address  - Адрес
     * @param phone    - Телефон
     * @param isActive - Активность
     */
    public Organization(String name, String fullName, String inn, String kpp, String address, String phone, boolean isActive) {
        this.name = name;
        this.fullName = fullName;
        this.inn = inn;
        this.kpp = kpp;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getKpp() {
        return kpp;
    }

    public void setKpp(String kpp) {
        this.kpp = kpp;
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

    public Set<Office> getOffices() {
        return offices;
    }
}
