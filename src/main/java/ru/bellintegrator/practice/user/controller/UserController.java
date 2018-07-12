package ru.bellintegrator.practice.user.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.practice.user.model.User;
import ru.bellintegrator.practice.user.service.UserService;
import ru.bellintegrator.practice.user.view.UserView;

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
    @PostMapping(value = "/user/list")
    public String addUser(@RequestBody UserView user) {
        return userService.add(user);
    }

    @ApiOperation(value = "getUserById", nickname = "getUserById", httpMethod = "GET")
    @GetMapping(value = "/user/{id}")
    public User getOrganizationById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @ApiOperation(value = "updateUser", nickname = "updateUser", httpMethod = "POST")
    @PostMapping(value = "/user/update")
    public void updateUser(@RequestBody UserView user) {
        userService.updateUser(user);
    }

    @ApiOperation(value = "saveUser", nickname = "saveUser", httpMethod = "POST")
    @PostMapping(value = "/user/save")
    public void saveOffice(@RequestBody UserView user) {
        userService.saveUser(user);
    }

}