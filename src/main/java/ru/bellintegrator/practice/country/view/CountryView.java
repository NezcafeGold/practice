package ru.bellintegrator.practice.country.view;

public class CountryView {

    public String name;

    public int code;

    @Override
    public String toString() {
        return "{name:" + name + ";code:" + code + "}";
    }
}
