package com.atuar.desafio_votacao_sicredi.application.service;

import com.atuar.desafio_votacao_sicredi.application.dto.User.CreateUserDto;
import com.atuar.desafio_votacao_sicredi.application.dto.User.ListUserDto;
import com.atuar.desafio_votacao_sicredi.application.mapper.UserMapper;
import com.atuar.desafio_votacao_sicredi.domain.entity.User;
import com.atuar.desafio_votacao_sicredi.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service()
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ListUserDto create(CreateUserDto dto) {
        User user = UserMapper.toEntity(dto);

        return UserMapper.toListDto(userRepository.save(user));
    }
}
