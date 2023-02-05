package Botik.service;

import Botik.model.User;
import Botik.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
class UserServiceImplTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @Test
    void listAllUser() {
    }

    @Test
    void getUser() {
    }

    @Test
    void createUser() {
        List<User> all = userRepository.findAll();
        assertEquals(0, all.size());
        User user = new User("UserOne");
        userService.createUser(user);
        List<User> all1 = userRepository.findAll();
        assertEquals(1, all1.size());
        User userFromDataBase = all1.get(0);
        assertEquals(1, userFromDataBase.getId());

    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUser() {
    }
}