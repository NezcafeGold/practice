package ru.bellintegrator.practice.dictionary.country.model;

import ru.bellintegrator.practice.user.model.Document;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import java.util.Set;

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
    @Column(nullable = false, length = 100)
    private String name;

    /**
     * Код страны
     */
    @Column(nullable = false)
    private int code;

    /**
     * Связь один ко многим с документом
     * @see Document
     */
    @OneToMany(mappedBy = "country")
    private Set<Document> document;

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
