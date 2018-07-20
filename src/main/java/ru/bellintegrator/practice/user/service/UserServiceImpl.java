package ru.bellintegrator.practice.user.service;

import org.springframework.stereotype.Service;
import ru.bellintegrator.practice.user.model.User;
import ru.bellintegrator.practice.user.view.UserView;

import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class UserServiceImpl implements UserService {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UserView> filterUser(UserView user) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User getUserById(int id) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateUser(UserView user) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveUser(UserView user) {
    }
}
