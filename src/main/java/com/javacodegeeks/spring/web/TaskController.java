package com.javacodegeeks.spring.web;

import org.reactivestreams.Publisher;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.javacodegeeks.spring.persistence.Task;
import com.javacodegeeks.spring.persistence.TaskRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class TaskController {
    private final TaskRepository repository;

    public TaskController(TaskRepository repository) {
        this.repository = repository;
    }

    @PostMapping(value = "/tasks")
    public Flux<Task> create(@RequestBody Publisher<Task> tasks) {
        return Flux
            .from(tasks)
            .doOnNext(repository::save);
    }

    @GetMapping("/tasks")
    public Flux<Task> list() {
        return Flux.fromIterable(repository.findAll());
    }

    @GetMapping("/tasks/{id}")
    public Mono<Task> findById(@PathVariable int id) {
        return Mono.justOrEmpty(repository.findById(id));
    }
    
    @DeleteMapping("/tasks/{id}")
    public Mono<Void> deleteById(@PathVariable int id) {
        repository.deleteById(id);
        return Mono.empty();
    }
}
