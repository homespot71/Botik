package Botik.service;

import Botik.model.User;
import Botik.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> listAllUser() {
        return userRepository.findAll();
    }


    @Override
    public User getUser(long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Please enter correct id");
        }
        return userRepository.findById(id).orElse(null);
    }


    @Override
    public User createUser(User user) {
        if (user.getUserName().isEmpty() || userRepository.findById(user.getId()).isPresent()) {
            throw new IllegalArgumentException("Please enter correct data");
        }
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        if (userRepository.findById(user.getId()).isEmpty()) {
            throw new IllegalArgumentException("You entered incorrect ID for user");
        }
        long userId = user.getId();
        User entity = userRepository.findById(userId).orElse(null);
        if (entity != null) {
            entity.setUserName(entity.getUserName());
            userRepository.save(entity);
        }
        return entity;
    }

    @Override
    public void deleteUser(long id) {
        if (userRepository.findById(id).isEmpty()) {
            throw new IllegalArgumentException("Please enter correct id for User");
        }
        userRepository.delete(getUser(id));
    }
}
