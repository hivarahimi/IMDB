package com.check24.IMDB.api.user;


import com.check24.IMDB.api.dto.UserDto;
import com.check24.IMDB.api.dto.mapper.ImdbMapper;
import com.check24.IMDB.model.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ImdbMapper imdbMapper;

    @Override
    public boolean existUser(String username) {
        return userRepository.existsUser(username);
    }

    @Override
    public Optional<UserEntity> readUserByUsername(String username) {

        return userRepository.findByUsername(username);
    }

    @Override
    public UserDto getUserById(Long userId) {
        Optional<UserEntity> optionalFilm = userRepository.findById(userId);
        return optionalFilm.map(imdbMapper::userEntityToUserDto).orElse(null);
    }

}
