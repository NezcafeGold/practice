package ru.bellintegrator.practice.user.dao;

import ru.bellintegrator.practice.dictionary.country.model.Country;
import ru.bellintegrator.practice.dictionary.doctype.model.DocType;
import ru.bellintegrator.practice.user.model.Document;

/**
 * DAO для работы с документом
 */
public interface DocumentDao {

    /**
     * Сохранить документ
     *
     * @param document
     */
    void saveDocument(Document document);

    /**
     * Найти документ по id
     *
     * @param id
     */
    Document getDocumentById(Long id);

    /**
     * Вернуть тип документа по id
     *
     * @param id
     */
    DocType getDocTypeById(Long id);

    /**
     * Вернуть данные страны по id
     *
     * @param id
     */
    Country getCountryById(Long id);

    /**
     * Обновить документ
     *
     * @param document
     */
    void updateDocument(Document document);
}
