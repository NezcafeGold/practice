package ru.bellintegrator.practice.user.model;


import ru.bellintegrator.practice.office.model.Office;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Version;
import java.util.Date;

/**
 * Пользователь
 */
@Entity
public class User {

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
     * Имя
     */
    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    /**
     * Фамилия
     */
    @Column(name = "second_name", length = 50)
    private String secondName;

    /**
     * Отчество
     */
    @Column(name = "middle_name", length = 50)
    private String middleName;

    /**
     * Должность
     */
    @Column(nullable = false, length = 50)
    private String position;

    /**
     * Телефон
     */
    @Column(length = 12)
    private String phone;

    /**
     * Идентификация
     */
    @Column(name = "is_identified")
    private Boolean isIdentified;

    /**
     * Офис пользователя
     *
     * @see Office
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "office_id")
    private Office office;

    /**
     * Документ пользователя
     *
     * @see Document
     */
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
    private Document document;

    /**
     * Пустой конструктор для hibernate
     */
    public User() {
    }

    /**
     * Конструктор - создание объекта User со следующими полями
     *
     * @param firstName    Имя
     * @param secondName   Фамилия
     * @param middleName   Отчество
     * @param position     Должность
     * @param phone        Телефон
     * @param isIdentified Идентификация
     */
    public User(String firstName, String secondName, String middleName, String position, String phone, boolean isIdentified) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.middleName = middleName;
        this.position = position;
        this.phone = phone;
        this.isIdentified = isIdentified;
        this.office = office;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean getIdentified() {
        return isIdentified;
    }

    public void setIdentified(boolean identified) {
        isIdentified = identified;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }
}
