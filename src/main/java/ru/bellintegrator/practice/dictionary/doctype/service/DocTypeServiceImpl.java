package ru.bellintegrator.practice.dictionary.doctype.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.dictionary.doctype.view.DocTypeView;

import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class DocTypeServiceImpl implements DocTypeService {

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public List<DocTypeView> getDocTypes() {
       return null;
    }
}
