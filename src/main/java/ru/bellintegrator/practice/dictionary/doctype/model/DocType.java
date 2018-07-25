package ru.bellintegrator.practice.dictionary.doctype.model;

import ru.bellintegrator.practice.user.model.Document;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import java.util.Set;

/**
 * Документ
 */
@Entity(name = "Doc_type")
public class DocType {

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
     * Название документа
     */
    @Column(nullable = false, length = 100)
    private String name;

    /**
     * Код документа
     */
    @Column(nullable = false, length = 5)
    private String code;

    /**
     * Пустой конструктор для hibernate
     */
    public DocType() {
    }

    /**
     * Конструктор - создание объекта DocType со следующими полями
     *
     * @param name Название офиса
     * @param code Код документа
     */
    public DocType(String name, String code) {
        this.name = name;
        this.code = code;
    }

    /**
     * Конструктор - создание объекта DocType со следующим полем
     *
     * @param name Название офиса
     */
    public DocType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getId() {
        return id;
    }
}
