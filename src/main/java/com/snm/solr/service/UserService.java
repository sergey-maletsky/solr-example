package com.snm.solr.service;

import com.snm.solr.dto.UserDto;
import com.snm.solr.model.User;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface UserService {

    @NotNull UserDto save(@NotNull UserDto userDto);

    @NotNull List<User> saveAll(@NotNull Iterable<User> users);
}
