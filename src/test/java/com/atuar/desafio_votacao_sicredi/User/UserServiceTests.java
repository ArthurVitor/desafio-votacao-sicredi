package com.atuar.desafio_votacao_sicredi.User;

import com.atuar.desafio_votacao_sicredi.application.dto.User.CreateUserDto;
import com.atuar.desafio_votacao_sicredi.application.dto.User.ListUserDto;
import com.atuar.desafio_votacao_sicredi.application.exception.NotFoundException;
import com.atuar.desafio_votacao_sicredi.application.mapper.UserMapper;
import com.atuar.desafio_votacao_sicredi.application.service.UserService;
import com.atuar.desafio_votacao_sicredi.domain.entity.User;
import com.atuar.desafio_votacao_sicredi.domain.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@DisplayName("User Service")
@ExtendWith(MockitoExtension.class)
public class UserServiceTests {
    @Mock()
    private UserRepository userRepository;

    @Mock()
    private UserMapper userMapper;

    @InjectMocks()
    private UserService userService;

    private User user;
    private CreateUserDto createDto;
    private ListUserDto listUserDto;

    @BeforeEach()
    void setUp() {
        user = UserStub.validUser();
        createDto = UserStub.validCreateUserDto();
        listUserDto = UserStub.validListUserDto();
    }


    @Test()
    @DisplayName("Should succesfully create an user when data is valid")
    public void shouldSuccesfullyCreateUser() {
        when(userMapper.toEntity(createDto)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.toListDto(user)).thenReturn(listUserDto);

        ListUserDto result = userService.create(createDto);

        assertEquals(listUserDto, result);
    }

    @Test()
    @DisplayName("Should return a valid user")
    public void shouldReturnAValidUser() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userMapper.toListDto(user)).thenReturn(listUserDto);

        ListUserDto result = userService.getById(1L);

        assertEquals(listUserDto, result);
    }

    @Test()
    @DisplayName("Should not return an user")
    public void shouldNotReturnAnUser() {
        when(userRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> userService.getById(999L));
    }

    @Test()
    @DisplayName("Should delete an user")
    public void shouldDeleteAnUser() {
        doNothing().when(userRepository).deleteById(1L);

        userService.delete(1L);

        verify(userRepository, times(1)).deleteById(1L);
    }
}
