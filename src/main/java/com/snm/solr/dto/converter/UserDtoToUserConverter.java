package com.snm.solr.dto.converter;

import com.snm.solr.dto.UserDto;
import com.snm.solr.model.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserDtoToUserConverter implements Converter<UserDto, User> {

    @Override
    public User convert(UserDto dto) {

        User user = new User();
        user.setName(dto.getName());
        user.setAge(dto.getAge());

        return user;
    }
}
