package ru.bellintegrator.practice.country.view;

/**
 * Страна
 */
public class CountryView {

    /**
     * Название страны
     */
    public String name;

    /**
     * Код страны
     */
    public int code;

    /**
     * Переопределенный toString()
     */
    @Override
    public String toString() {
        return "{name:" + name + ";code:" + code + "}";
    }
}
