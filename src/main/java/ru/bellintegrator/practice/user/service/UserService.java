package ru.bellintegrator.practice.user.service;


import ru.bellintegrator.practice.user.model.User;
import ru.bellintegrator.practice.user.view.UserView;

import java.util.List;

/**
 * Сервис
 */
public interface UserService {

    /**
     * Отфильтровать пользователей
     *
     * @param user
     */
    List<UserView> filterUser(UserView user);

    /**
     * Получить пользователя по id
     *
     * @param id
     */
    User getUserById(int id);

    /**
     * Обновить пользователя
     *
     * @param user
     */
    void updateUser(UserView user);

    /**
     * Сохранить пользователя
     *
     * @param user
     */
    void saveUser(UserView user);
}
