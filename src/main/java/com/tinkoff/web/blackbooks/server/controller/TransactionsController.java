package com.tinkoff.web.blackbooks.server.controller;

import com.tinkoff.web.blackbooks.server.controller.util.SortType;
import com.tinkoff.web.blackbooks.server.domain.dao.dto.TransactionDto;
import com.tinkoff.web.blackbooks.server.service.TransactionsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/transactions")
@Tag(name = "transaction", description = "endpoint for operations with transactions")
public class TransactionsController {

    // toDo no validation at the moment
    private final TransactionsService transactionsService;

    public TransactionsController(TransactionsService transactionsService) {
        this.transactionsService = transactionsService;
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public UUID createTransaction(@RequestBody TransactionDto transactionDto) {
        return transactionsService.create(transactionDto);
    }

    @GetMapping("/{id}")
    public Mono<TransactionDto> getTransaction(@PathVariable UUID id) {
        return transactionsService.get(id);
    }

    @GetMapping("/all")
    public Flux<TransactionDto> getAllTransactions() {
        return transactionsService.getAll();
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable UUID id, @RequestBody TransactionDto transactionDto) {
        transactionsService.update(id, transactionDto);
    }

    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable UUID id) {
        transactionsService.delete(id);
    }

    @GetMapping("/")
    public Flux<TransactionDto> getTransactions(
            @RequestParam(required = false) UUID bookDepositId,
            @RequestParam(required = false) UUID bookHunterId,
            @RequestParam(required = false, defaultValue = "10") Long amount,
            @RequestParam(required = false, name = "sortBy", defaultValue = "desc") SortType type
    ) {
        return transactionsService.getTransactions(bookDepositId, bookHunterId, amount, type);
    }
}
