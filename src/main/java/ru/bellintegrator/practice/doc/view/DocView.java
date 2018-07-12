package ru.bellintegrator.practice.doc.view;


/**
 * Документ
 */
public class DocView {

    /**
     * Название документа
     */
    public String name;

    /**
     * Код документа
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
