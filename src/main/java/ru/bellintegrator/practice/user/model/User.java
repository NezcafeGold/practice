package ru.bellintegrator.practice.user.model;


import ru.bellintegrator.practice.office.model.Office;
import ru.bellintegrator.practice.organization.model.Organization;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
    @Column(name = "first_name", nullable = false)
    private String firstName;

    /**
     * Фамилия
     */
    @Column(name = "second_name")
    private String secondName;

    /**
     * Отчество
     */
    @Column(name = "middle_name")
    private String middleName;

    /**
     * Должность
     */
    @Column(nullable = false)
    private String position;

    /**
     * Телефон
     */
    private String phone;

    /**
     * Название документа
     */
    @Column(name = "doc_name")
    private String docName;

    /**
     * Номер документа
     */
    @Column(name = "doc_number")
    private Long docNumber;

    /**
     * Дата выдачи документа
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "doc_date")
    private Date docDate;

    /**
     * Код гражданства
     */
    @Column(name = "citizenship_code")
    private int citizenshipCode;

    /**
     * Идентификация
     */
    @Column(name = "is_identified")
    private boolean isIdentified;

    /**
     * Связь многие к одному к офису
     * @see Office
     */
    @ManyToOne
    @JoinColumn(name = "office_id")
    private Office office;

    /**
     * Пустой конструктор для hibernate
     */
    public User() {
    }

    /**
     * Конструктор - создание объекта User со следующими полями
     *
     * @param firstName       - Имя
     * @param secondName      - Фамилия
     * @param middleName      - Отчество
     * @param position        - Должность
     * @param phone           - Телефон
     * @param docName         - Название документа
     * @param docNumber       - Номер документа
     * @param docDate         - Дата выдачи документа
     * @param citizenshipCode - Код гражданства
     * @param isIdentified    - Идентификация
     */
    public User(String firstName, String secondName, String middleName, String position, String phone, String docName, Long docNumber, Date docDate, int citizenshipCode, boolean isIdentified) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.middleName = middleName;
        this.position = position;
        this.phone = phone;
        this.docName = docName;
        this.docNumber = docNumber;
        this.docDate = docDate;
        this.citizenshipCode = citizenshipCode;
        this.isIdentified = isIdentified;
    }

    /**
     * Геттеры и сеттеры для полей
     */
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

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public Long getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(Long docNumber) {
        this.docNumber = docNumber;
    }

    public Date getDocDate() {
        return docDate;
    }

    public void setDocDate(Date docDate) {
        this.docDate = docDate;
    }

    public int getCitizenshipCode() {
        return citizenshipCode;
    }

    public void setCitizenshipCode(int citizenshipCode) {
        this.citizenshipCode = citizenshipCode;
    }

    public boolean isIdentified() {
        return isIdentified;
    }

    public void setIdentified(boolean identified) {
        isIdentified = identified;
    }

    public Office getOffice() {
        return office;
    }
}
