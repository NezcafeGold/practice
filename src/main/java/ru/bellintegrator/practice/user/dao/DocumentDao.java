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
     * Обновить документ
     *
     * @param document
     */
    void updateDocument(Document document);
}
