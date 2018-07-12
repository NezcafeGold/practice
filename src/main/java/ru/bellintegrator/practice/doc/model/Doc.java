package ru.bellintegrator.practice.doc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

/**
 * Документ
 */
@Entity
public class Doc {

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
    @Column(nullable = false)
    private String name;

    /**
     * Код документа
     */
    @Column(nullable = false, length = 2)
    private int code;

    /**
     * Пустой конструктор для hibernate
     */
    public Doc() {
    }

    /**
     * Конструктор - создание объекта Doc со следующими полями
     *
     * @param name - Название офиса
     * @param code - Код документа
     */
    public Doc(String name, int code) {
        this.name = name;
        this.code = code;
    }

    /**
     * Геттеры и сеттеры для полей
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Long getId() {
        return id;
    }
}
