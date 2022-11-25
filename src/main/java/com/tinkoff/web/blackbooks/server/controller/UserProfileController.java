package com.tinkoff.web.blackbooks.server.controller;

import com.tinkoff.web.blackbooks.server.domain.dao.dto.UserProfileDto;
import com.tinkoff.web.blackbooks.server.service.UserProfileService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping(value = "/user", produces = {MediaType.APPLICATION_JSON_VALUE})
@Tag(name = "user")
@RequiredArgsConstructor
public class UserProfileController {

    // toDo no validation at the moment
    private final UserProfileService userProfileService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public UUID createUser(@RequestBody UserProfileDto userProfileDto) {
        return userProfileService.create(userProfileDto);
    }

    @GetMapping("/{id}")
    public Mono<UserProfileDto> getUser(@PathVariable UUID id) {
        return userProfileService.get(id);
    }

    @GetMapping("/all")
    public Flux<UserProfileDto> getAllUsers() {
        return userProfileService.getAll();
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable UUID id, @RequestBody UserProfileDto userProfileDto) {
        userProfileService.update(id, userProfileDto);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable UUID id) {
        userProfileService.delete(id);
    }

}
