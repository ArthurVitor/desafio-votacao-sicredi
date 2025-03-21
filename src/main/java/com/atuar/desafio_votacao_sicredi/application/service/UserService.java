package com.atuar.desafio_votacao_sicredi.application.service;

import com.atuar.desafio_votacao_sicredi.application.dto.Page.PageDto;
import com.atuar.desafio_votacao_sicredi.application.dto.User.CreateUserDto;
import com.atuar.desafio_votacao_sicredi.application.dto.User.ListUserDto;
import com.atuar.desafio_votacao_sicredi.application.mapper.PageMapper;
import com.atuar.desafio_votacao_sicredi.application.mapper.UserMapper;
import com.atuar.desafio_votacao_sicredi.domain.entity.User;
import com.atuar.desafio_votacao_sicredi.domain.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service()
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ListUserDto create(CreateUserDto dto) {
        User user = UserMapper.toEntity(dto);

        return UserMapper.toListDto(this.userRepository.save(user));
    }

    public PageDto<ListUserDto> getAll(Pageable pageable) {
        Page<ListUserDto> users = this.userRepository.findAll(pageable).map(UserMapper::toListDto);

        return PageMapper.toPageDto(users);
    }

    public ListUserDto getById(Long id) {
        return UserMapper.toListDto(this.userRepository.findById(id).orElseThrow(() -> new RuntimeException("Couldn't find user with id: " + id)));
    }

    public void delete(Long id) {
        this.userRepository.deleteById(id);
    }
}
