package ru.bellintegrator.practice.user.service;

import org.springframework.stereotype.Service;
import ru.bellintegrator.practice.user.model.User;
import ru.bellintegrator.practice.user.view.UserView;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public String filterUser(UserView user) {
        return null;
    }

    @Override
    public User getUserById(int id) {
        return null;
    }

    @Override
    public void updateUser(UserView user) {
    }

    @Override
    public void saveUser(UserView user) {
    }
}
