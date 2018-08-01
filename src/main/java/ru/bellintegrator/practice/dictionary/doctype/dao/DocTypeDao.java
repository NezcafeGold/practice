package ru.bellintegrator.practice.dictionary.doctype.dao;

import ru.bellintegrator.practice.dictionary.doctype.model.DocType;

import java.util.List;

/**
 * DAO для работы с DocType
 */
public interface DocTypeDao {

    /**
     * Найти тип документа по имени
     *
     * @param docName
     */
    DocType getDocTypeByName(String docName);

    /**
     * Найти все типы документов
     *
     */
    List<DocType> getAllDocTypes();
}
