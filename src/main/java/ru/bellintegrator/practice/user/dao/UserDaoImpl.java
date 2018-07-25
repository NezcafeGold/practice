package ru.bellintegrator.practice.user.dao;

import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.user.model.User;
import ru.bellintegrator.practice.user.view.UserView;

import java.util.List;

/**
 * {@inheritDoc}
 */
@Repository
public class UserDaoImpl implements UserDao {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> filterUser(UserView userView) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateUser(User user) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveUser(User user) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User getUserById(Long id) {
        return null;
    }
}
