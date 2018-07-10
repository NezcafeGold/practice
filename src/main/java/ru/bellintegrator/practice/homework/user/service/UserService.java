package ru.bellintegrator.practice.homework.user.service;


import ru.bellintegrator.practice.homework.organization.model.Organization;
import ru.bellintegrator.practice.homework.organization.view.OrganizationView;
import ru.bellintegrator.practice.homework.user.model.User;
import ru.bellintegrator.practice.homework.user.view.UserView;

import java.util.List;

public interface UserService {

    String add(UserView user);

    User getUserById(int id);

    String updateUser(UserView user);

    String saveUser(UserView user);
}
