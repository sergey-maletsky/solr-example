package com.snm.solr.utils;

import com.snm.solr.model.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import java.util.*;

public class UserGenerator {

    private final static int nameLength = 15;
    private final static int startAge = 18;
    private final static int endAge = 70;

    public static Set<User> generateUsers(Integer usersAmount) {

        Set<User> users = new HashSet<>();
        for (int i = 0; i < usersAmount; i++) {
            users.add(new User(generateId(), generateName(), generateAge()));
        }

        return users;
    }

    private static String generateId() {
        return UUID.randomUUID().toString();
    }

    private static String generateName() {
        return RandomStringUtils.randomAlphabetic(nameLength);
    }

    private static Integer generateAge() {
        return RandomUtils.nextInt(startAge, endAge + 1);
    }
}
