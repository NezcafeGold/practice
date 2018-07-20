package ru.bellintegrator.practice.dictionary.docType.service;

import ru.bellintegrator.practice.dictionary.docType.view.DocTypeView;

import java.util.List;

/**
 * Сервис
 */
public interface DocTypeService {

    /**
     * Получить список типов документов
     *
     */
    List<DocTypeView> getDocTypes();

}
