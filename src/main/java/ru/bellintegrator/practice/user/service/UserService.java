package ru.bellintegrator.practice.user.service;


import ru.bellintegrator.practice.user.view.UserFilterView;
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
    List<UserFilterView> filterUser(UserView user);

    /**
     * Получить пользователя по id
     *
     * @param id
     */
    UserView getUserById(Long id);

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
