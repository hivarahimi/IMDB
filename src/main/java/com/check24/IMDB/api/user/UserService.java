package com.check24.IMDB.api.user;


import com.check24.IMDB.api.dto.UserDto;
import com.check24.IMDB.model.UserEntity;

import java.util.Optional;


public interface UserService {
    boolean existUser(String username);

    Optional<UserEntity> readUserByUsername(String username);

    UserDto getUserById(Long userId);


}
