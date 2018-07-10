package ru.bellintegrator.practice.homework.user.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.practice.homework.user.model.User;
import ru.bellintegrator.practice.homework.user.service.UserService;
import ru.bellintegrator.practice.homework.user.view.UserView;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api", produces = APPLICATION_JSON_VALUE)
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "addUser", nickname = "addUser", httpMethod = "POST")
    @GetMapping(value = "/user/list")
    public String addUser(@RequestBody UserView user) {
        return userService.add(user);

    }

    @ApiOperation(value = "getUserById", nickname = "getUserById", httpMethod = "GET")
    @GetMapping(value = "/user/{id}")
    public User getOrganizationById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @ApiOperation(value = "updateUser", nickname = "updateUser", httpMethod = "POST")
    @GetMapping(value = "/user/update")
    public String updateUser(@RequestBody UserView user) {
        return userService.updateUser(user);

    }

    @ApiOperation(value = "saveUser", nickname = "saveUser", httpMethod = "POST")
    @GetMapping(value = "/user/save")
    public String saveOffice(@RequestBody UserView user) {
        return userService.saveUser(user);
    }

}
