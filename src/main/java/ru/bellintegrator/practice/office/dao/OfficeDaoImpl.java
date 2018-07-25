package ru.bellintegrator.practice.office.dao;

import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.office.model.Office;
import ru.bellintegrator.practice.office.view.OfficeFilterView;
import ru.bellintegrator.practice.office.view.OfficeView;

import java.util.List;

/**
 * {@inheritDoc}
 */
@Repository
public class OfficeDaoImpl implements OfficeDao {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Office> list(OfficeView officeView) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Office getOfficeById(Long id) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Office office) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Office office) {

    }
}
