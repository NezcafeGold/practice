package ru.bellintegrator.practice.dictionary.doctype.service;

import ru.bellintegrator.practice.dictionary.doctype.view.DocTypeView;

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
