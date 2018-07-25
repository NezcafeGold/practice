package ru.bellintegrator.practice.dictionary.doctype.dao;

import ru.bellintegrator.practice.dictionary.doctype.model.DocType;

import java.util.List;

/**
 * DAO для работы с DocType
 */
public interface DocTypeDao {

    /**
     * Сохранить DocType
     *
     * @param docType
     */
    void saveDocType(DocType docType);

    /**
     * Найти DocType по id
     *
     * @param id
     */
    DocType getDocTypeById(Long id);

    /**
     * Вернуть все типы документов
     */
    List<DocType> getAllDocTypes();

}
