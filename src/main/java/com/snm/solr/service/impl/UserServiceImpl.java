package com.snm.solr.service.impl;

import com.snm.solr.dto.UserDto;
import com.snm.solr.dto.converter.BaseConverter;
import com.snm.solr.model.User;
import com.snm.solr.repository.UserRepository;
import com.snm.solr.service.UserService;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BaseConverter converter;

    @Override
    public Page<User> list(Pageable pageable) {

        return userRepository.findAll(pageable);
    }

    @Override
    public Page<User> findAllByName(@NotNull String name, Pageable pageable) {
        return userRepository.findByName(name, pageable);
    }

    @Override
    public List<User> findAllByName(@NotNull String name) {
        return userRepository.findByName(name);
    }

    @Override
    public List<User> findAllByAge(Integer age) {
        return userRepository.findByAge(age);
    }

    @Override
    public List<User> findAllByNameOrAge(@NotNull String name, @NotNull Integer age) {
        return userRepository.findByNameAndAge(name, age);
    }

    @Override
    @NotNull
    public UserDto save(@NotNull final UserDto userDto) {

        User newUser = converter.convert(userDto, User.class);
        return converter.convert(userRepository.save(newUser), UserDto.class);
    }

    @NotNull
    @Override
    public List<User> saveAll(@NotNull Iterable<User> users) {

        return (List<User>) userRepository.saveAll(users);
    }

    @Override
    public void createTestUsers() {

        List<User> users = new ArrayList<>();
        users.add(getUser("Robert De Niro", 20));
        users.add(getUser("Robert De Niro", 24));
        users.add(getUser("Robert O Rail", 25));
        users.add(getUser("Victor O Rail", 20));
        users.add(getUser("Victor De Niro", 30));
        users.add(getUser("Victor O Niro", 20));
        users.add(getUser("De Niro", 22));
        users.add(getUser("De Rudo", 42));
        users.add(getUser("Niro De Rudo", 33));
        users.add(getUser("Victor O Rudo", 27));
        users.add(getUser("Victor De Rudo", 19));
        userRepository.saveAll(users);
    }

    private User getUser(String name, Integer age) {

        User user = new User();
        user.setName_t(name);
        user.setAge(age);
        return user;
    }
}
