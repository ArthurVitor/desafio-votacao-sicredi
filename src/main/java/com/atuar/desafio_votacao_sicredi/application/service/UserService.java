package com.atuar.desafio_votacao_sicredi.application.service;

import com.atuar.desafio_votacao_sicredi.application.dto.Page.PageDto;
import com.atuar.desafio_votacao_sicredi.application.dto.User.CreateUserDto;
import com.atuar.desafio_votacao_sicredi.application.dto.User.ListUserDto;
import com.atuar.desafio_votacao_sicredi.application.exception.NotFound.NotFoundException;
import com.atuar.desafio_votacao_sicredi.application.mapper.UserMapper;
import com.atuar.desafio_votacao_sicredi.domain.entity.User;
import com.atuar.desafio_votacao_sicredi.domain.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service()
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public ListUserDto create(CreateUserDto dto) {
        User user = userMapper.toEntity(dto);

        return userMapper.toListDto(this.userRepository.save(user));
    }

    public PageDto<ListUserDto> getAll(Pageable pageable) {
        Page<ListUserDto> users = this.userRepository.findAll(pageable).map(userMapper::toListDto);

        return userMapper.toPageDto(users);
    }

    public ListUserDto getById(Long id) {
        return userMapper.toListDto(this.userRepository.findById(id).orElseThrow(() -> new NotFoundException("Couldn't find user with id: " + id)));
    }

    public void delete(Long id) {
        this.userRepository.deleteById(id);
    }
}
