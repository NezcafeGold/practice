package ru.bellintegrator.practice.user.dao;

import ru.bellintegrator.practice.dictionary.country.model.Country;
import ru.bellintegrator.practice.dictionary.doctype.model.DocType;
import ru.bellintegrator.practice.user.model.Document;

/**
 * DAO для работы с Document
 */
public interface DocumentDao {

    /**
     * Сохранить Document
     *
     * @param document
     */
    void saveDocument(Document document);

    /**
     * Найти Document по id
     *
     * @param id
     */
    Document getDocumentById(Long id);

    /**
     * Вернуть DocType по id
     *
     * @param id
     */
    DocType getDocTypeById(Long id);

    /**
     * Вернуть Country по id
     *
     * @param id
     */
    Country getCountryById(Long id);

    /**
     * Обновить Document
     *
     * @param document
     */
    void updateDocument(Document document);
}
