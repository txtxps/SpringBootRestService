package ru.topjava.springboot;

import ru.topjava.springboot.model.Role;
import ru.topjava.springboot.model.User;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.topjava.springboot.model.AbstractBaseEntity.START_SEQ;

public class UserTestData {

    public static final int ADMIN_ID = START_SEQ;
    public static final int USER1_ID = START_SEQ + 1;
    public static final int USER2_ID = START_SEQ + 2;

    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin@gmail.com", "admin", Role.ROLE_ADMIN);
    public static final User USER_1 = new User(USER1_ID, "User", "user@yandex.ru", "12345678", Role.ROLE_USER);
    public static final User USER_2 = new User(USER2_ID, "User1", "user1@yandex.ru", "12345678", Role.ROLE_USER);

    public static User getUpdated() {
        User updated = new User(ADMIN);
        updated.setName("UpdatedName");
        updated.setRoles(Collections.singletonList(Role.ROLE_ADMIN));
        return updated;
    }

    public static User getNew() {
        return new User(null, "New", "new@gmail.com", "newPass", false, new Date(), Collections.singleton(Role.ROLE_USER));
    }

    public static void assertMatch(User actual, User expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "registered", "roles");
    }

    public static void assertMatch(Iterable<User> actual, User... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<User> actual, Iterable<User> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("registered", "roles").isEqualTo(expected);
    }

    public static TestMatchers<User> USER_MATCHERS = TestMatchers.useFieldsComparator(User.class, "registered", "password");
}
