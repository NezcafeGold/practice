package ru.bellintegrator.practice.doc.view;

public class DocView {

    public String name;

    public int code;

    @Override
    public String toString() {
        return "{name:" + name + ";code:" + code + "}";
    }
}
