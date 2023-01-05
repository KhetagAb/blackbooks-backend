package com.tinkoff.web.blackbooks.server.controller;

import com.tinkoff.web.blackbooks.server.domain.dao.dto.DepositoryDto;
import com.tinkoff.web.blackbooks.server.service.DepositoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping(value = "/depository", produces = {MediaType.APPLICATION_JSON_VALUE})
@Tag(name = "depository")
@RequiredArgsConstructor
public class DepositoryController {

    // toDo no validation at the moment
    private final DepositoryService depositoryService;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public UUID createDepository(@RequestBody DepositoryDto depositoryDto) {
        return depositoryService.create(depositoryDto);
    }

    @GetMapping("/{id}")
    public Mono<DepositoryDto> getDepository(@PathVariable UUID id) {
        return depositoryService.get(id);
    }

    @GetMapping("/all")
    public Flux<DepositoryDto> getAllDepositories() {
        return depositoryService.getAll();
    }

    @PutMapping("/{id}")
    public void updateDepository(@PathVariable UUID id, @RequestBody DepositoryDto depositoryDto) {
        depositoryService.update(id, depositoryDto);
    }

    @DeleteMapping("/{id}")
    public void deleteDepository(@PathVariable UUID id) {
        depositoryService.delete(id);
    }
}
