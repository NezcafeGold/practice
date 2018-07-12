package ru.bellintegrator.practice.user.service;


import ru.bellintegrator.practice.user.model.User;
import ru.bellintegrator.practice.user.view.UserView;

public interface UserService {

    User filterUser(UserView user);

    User getUserById(int id);

    void updateUser(UserView user);

    void saveUser(UserView user);
}
