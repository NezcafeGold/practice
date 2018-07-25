package ru.bellintegrator.practice.user.model;

import ru.bellintegrator.practice.dictionary.country.model.Country;
import ru.bellintegrator.practice.dictionary.doctype.model.DocType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Version;
import java.util.Date;

/**
 * Документ
 */
@Entity
public class Document {

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
     * Номер документа
     */
    @Column(name = "doc_number", length = 20)
    private Long docNumber;

    /**
     * Дата выдачи документа
     */
    @Column(name = "doc_date", length = 11)
    private Date docDate;

    /**
     * Пользователь документа
     */
    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private User user;

    /**
     * Тип документа
     *
     * @see DocType
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doc_type_id", nullable = false)
    private DocType docType;

    /**
     * Код гражданства
     *
     * @see Country
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "citizenship_id", nullable = false)
    private Country country;

    /**
     * Пустой конструктор для hibernate
     */
    public Document() {
    }

    /**
     * Конструктор создания объекта Document
     *
     * @param docNumber Номер документа
     * @param docDate   Дата регистрации документа
     * @param user      Пользователь документа
     * @param docType   Тип документа
     * @param country   Страна проживания
     */
    public Document(Long docNumber, Date docDate, User user, DocType docType, Country country) {
        this.docNumber = docNumber;
        this.docDate = docDate;
        this.user = user;
        this.docType = docType;
        this.country = country;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public DocType getDocType() {
        return docType;
    }

    public void setDocType(DocType docType) {
        this.docType = docType;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
