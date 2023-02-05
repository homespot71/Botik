package Botik.controller;

import Botik.model.User;
import Botik.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> listAllUser() {
        return userService.listAllUser();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable(name = "id") long id) {
        try {
            return userService.getUser(id);
        } catch (IllegalArgumentException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getLocalizedMessage());
        }
    }

    @PostMapping
    public void createUser(@RequestBody User user) {
        try {
            userService.createUser(user);
        } catch (IllegalArgumentException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getLocalizedMessage());
        }
    }

    @PutMapping
    public User updateUser(@RequestBody User user) {
        try {
            return userService.updateUser(user);
        } catch (IllegalArgumentException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getLocalizedMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable(name = "id") long id) {
        try {
            userService.deleteUser(id);
        } catch (IllegalArgumentException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, exception.getLocalizedMessage());
        }
    }

}
