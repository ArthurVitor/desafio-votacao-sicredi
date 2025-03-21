package com.atuar.desafio_votacao_sicredi.presentation.controller;

import com.atuar.desafio_votacao_sicredi.application.dto.Page.PageDto;
import com.atuar.desafio_votacao_sicredi.application.dto.User.CreateUserDto;
import com.atuar.desafio_votacao_sicredi.application.dto.User.ListUserDto;
import com.atuar.desafio_votacao_sicredi.application.service.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/user-controller")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<ListUserDto> save(@RequestBody @Valid CreateUserDto user) {
        return ResponseEntity.ok(this.userService.create(user));
    }

    @GetMapping()
    public ResponseEntity<PageDto<ListUserDto>> getAll(Pageable pageable) {
        return ResponseEntity.ok(this.userService.getAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListUserDto> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.userService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        this.userService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
