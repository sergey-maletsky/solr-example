package com.snm.solr.service;

import com.snm.solr.dto.UserDto;
import com.snm.solr.model.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    List<User> findAllByName(@NotNull String name);

    Page<User> list(Pageable pageable);

    Page<User> findAllByName(@NotNull String name, Pageable pageable);

    List<User> findAllByNameOrAge(@NotNull String name, @NotNull Integer age);

    @NotNull UserDto save(@NotNull UserDto userDto);

    @NotNull List<User> saveAll(@NotNull Iterable<User> users);

    List<User> findAllByAge(Integer age);

    void createTestUsers();
}
