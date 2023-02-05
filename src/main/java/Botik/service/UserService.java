package Botik.service;

import Botik.model.User;

import java.util.List;

public interface UserService {

    List<User> listAllUser();


    User getUser(long id);


    User createUser(User user);


    User updateUser(User user);


    void deleteUser(long id);
}
