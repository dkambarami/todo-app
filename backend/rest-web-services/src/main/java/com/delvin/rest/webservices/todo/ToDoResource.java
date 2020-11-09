package com.delvin.rest.webservices.todo;

import java.net.URI;
import java.util.List;

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
public class ToDoResource {

    @Autowired
    private TodoHardcodedService hardTodoService;

    @GetMapping("/tusers/{username}/todos")
    public List<Todo> getAllTodos(@PathVariable String username) {
        return hardTodoService.findAll();
    }

    @GetMapping("/tusers/{username}/todos/{id}")
    public Todo getTodo(@PathVariable String username, @PathVariable Long id) {
        return hardTodoService.findById(id);
    }

    @DeleteMapping("/tusers/{username}/todos/{id}")
    ResponseEntity<Void> deleteTodo(@PathVariable String username, @PathVariable Long id) {
        Todo todo = hardTodoService.deleteById(id);
        if (todo != null) {
            return ResponseEntity.noContent().build();
        }

        else
            return ResponseEntity.notFound().build();

    }

    @PutMapping("/tusers/{username}/todos/{id}")
    ResponseEntity<Todo> updateTodo(@PathVariable String username, @PathVariable Long id,
    @RequestBody Todo todo) {
        Todo todoUpdate = hardTodoService.save(todo);

        return new ResponseEntity<Todo> (todoUpdate, HttpStatus.OK);

    }

    @PostMapping("/tusers/{username}/todos")
    ResponseEntity<Void> updateTodo(@PathVariable String username,
    @RequestBody Todo todo) {
        Todo todoUpdate = hardTodoService.save(todo);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(todoUpdate.getId()).toUri();

        return ResponseEntity.created(uri).build();

    }

}
