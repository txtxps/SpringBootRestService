package ru.topjava.springboot.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import ru.topjava.springboot.model.Role;
import ru.topjava.springboot.model.User;
import ru.topjava.springboot.util.exception.NotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static ru.topjava.springboot.UserTestData.*;

@Transactional
@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserServiceTest {

    @Autowired
    protected UserService service;

    @Autowired
    private CacheManager cacheManager;

    @BeforeEach
    public void setUp() throws Exception {
        cacheManager.getCache("users").clear();
    }

    @Test
    void create() throws Exception {
        User newUser = getNew();
        User created = service.create(new User(newUser));
        Integer newId = created.getId();
        newUser.setId(newId);
        USER_MATCHERS.assertMatch(created, newUser);
        USER_MATCHERS.assertMatch(service.get(newId), newUser);
    }

    @Test
    void delete() throws Exception {
        service.delete(USER1_ID);
        assertThrows(NotFoundException.class, () ->
                service.delete(USER1_ID));
    }

    @Test
    void deletedNotFound() throws Exception {
        assertThrows(NotFoundException.class, () ->
                service.delete(1));
    }

    @Test
    void get() throws Exception {
        User user = service.get(ADMIN_ID);
        USER_MATCHERS.assertMatch(user, ADMIN);
    }

    @Test
    void getNotFound() throws Exception {
        assertThrows(NotFoundException.class, () ->
                service.get(1));
    }

    @Test
    void getByEmail() {
    }

    @Test
    void duplicateMailCreate() throws Exception {
        assertThrows(DataAccessException.class, () ->
                service.create(new User(null, "Duplicate", "user@yandex.ru", "newPass", Role.ROLE_USER)));
    }

    @Test
    void getAll() {
    }

    @Test
    void update() {
    }

    @Test
    void loadUserByUsername() {
    }
}