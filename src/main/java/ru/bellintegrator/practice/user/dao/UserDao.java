package ru.bellintegrator.practice.user.dao;

import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.user.model.User;
import ru.bellintegrator.practice.user.view.UserView;

import java.util.List;

/**
 * DAO для работы с User
 */
public interface UserDao {

    /**
     * Фильтр пользователя
     *
     * @param userView
     */
    List<User> filterUser(UserView userView);

    /**
     * Обновить пользователя
     *
     * @param user
     */
    void updateUser(User user);

    /**
     * Сохранить User
     *
     * @param user
     */
    void saveUser(User user);

    /**
     * Вернуть пользователя по id
     *
     * @param id
     */
    User getUserById(Long id);
}
