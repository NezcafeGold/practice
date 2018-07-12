package ru.bellintegrator.practice.country.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

/**
 * Страна
 */
@Entity
public class Country {

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
     * Название страны
     */
    @Column(nullable = false)
    private String name;

    /**
     * Код страны
     */
    @Column(nullable = false)
    private int code;

    /**
     * Пустой конструктор для hibernate
     */
    public Country() {
    }

    /**
     * Конструктор - создание объекта Country со следующими полями
     *
     * @param name - Название страны
     * @param code - Код страны
     */
    public Country(String name, int code) {
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
