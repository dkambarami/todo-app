package com.delvin.rest.webservices.todo;

import java.net.URI;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ToDoJPAResource {

    @Autowired
    private TodoJPARepository todoJpaRepository;

    @GetMapping("/users/{username}/todos")
    public List<Todo> getAllTodos(@PathVariable String username) {
        return todoJpaRepository.findByUsername(username);
    }

    @GetMapping("/users/{username}/todos/{id}")
    public Todo getTodo(@PathVariable String username, @PathVariable Long id) {
        return todoJpaRepository.getOne(id);
    }

    @DeleteMapping("/users/{username}/todos/{id}")
    ResponseEntity<Void> deleteTodo(@PathVariable String username, @PathVariable Long id) {
        todoJpaRepository.deleteById(id);
        
        return ResponseEntity.noContent().build();

    }

    @PutMapping("/users/{username}/todos/{id}")
    ResponseEntity<Todo> updateTodo(@PathVariable String username, @PathVariable Long id,
    @RequestBody Todo todo) {
        Todo todoUpdate = todoJpaRepository.save(todo);

        return new ResponseEntity<Todo> (todoUpdate, HttpStatus.OK);

    }

    @PostMapping("/users/{username}/todos")
    ResponseEntity<Void> updateTodo(@PathVariable String username,
    @RequestBody Todo todo) {
        todo.setUsername(username);
        Todo todoUpdate = todoJpaRepository.save(todo);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(todoUpdate.getId()).toUri();

        return ResponseEntity.created(uri).build();

    }

}
