package com.snm.solr.dto.converter;

import com.snm.solr.dto.UserDto;
import com.snm.solr.model.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserToUserDtoConverter implements Converter<User, UserDto> {

    @Override
    public UserDto convert(User user) {

        UserDto dto = new UserDto();
        dto.setName(user.getName());
        dto.setAge(user.getAge());

        return dto;
    }
}
