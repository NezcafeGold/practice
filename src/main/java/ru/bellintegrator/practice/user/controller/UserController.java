package ru.bellintegrator.practice.user.controller;

import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.practice.user.model.User;
import ru.bellintegrator.practice.user.service.UserService;
import ru.bellintegrator.practice.user.view.UserView;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api", produces = APPLICATION_JSON_VALUE)
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "Принимает данные и возвращает отфильтрованный список пользователей",
            nickname = "filterUser", httpMethod = "POST")
    @PostMapping(value = "/user/list")
    public List<UserView> filterUser(@RequestBody UserView user) {
        return userService.filterUser(user);
    }

    @ApiOperation(value = "Возвращает пользователя по id", nickname = "getUserById", httpMethod = "GET")
    @GetMapping(value = "/user/{id}")
    public User getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @ApiOperation(value = "Обновление данных пользователя", nickname = "updateUser", httpMethod = "POST")
    @PostMapping(value = "/user/update")
    public void updateUser(@RequestBody UserView user) {
        userService.updateUser(user);
    }

    @ApiOperation(value = "Сохранение данных пользователя", nickname = "saveUser", httpMethod = "POST")
    @PostMapping(value = "/user/save")
    public void saveOffice(@RequestBody UserView user) {
        userService.saveUser(user);
    }

}
