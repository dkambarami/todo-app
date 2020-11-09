package com.delvin.rest.webservices.todo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TodoHardcodedService {

    private static List<Todo> todos = new ArrayList<>();
    private static Long idCounter = 0L;

    // static {
    //     todos.add(new Todo(++idCounter, "NewTodo", "Leant Karate", new Date(), false));
    //     todos.add(new Todo(++idCounter, "NewTodo", "Leant Kumfu", new Date(), false));
    //     todos.add(new Todo(++idCounter, "NewTodo", "Leant Dance", new Date(), false));
    //     todos.add(new Todo(++idCounter, "NewTodo", "Leant Java", new Date(), false));
    //     todos.add(new Todo(++idCounter, "NewTodo", "Leant MicroServices", new Date(), false));
    //     todos.add(new Todo(++idCounter, "NewTodo", "Leant Angular", new Date(), false));
    //     todos.add(new Todo(++idCounter, "NewTodo", "Leant English", new Date(), false));
    //     todos.add(new Todo(++idCounter, "NewTodo", "Leant French", new Date(), false));
    // }

    public List<Todo> findAll() {
        return todos;
    }

    public Todo save(Todo todo) {
        if (todo.getId() == -1 || todo.getId() == 0) {
            todo.setId(++idCounter);
            todos.add(todo);
        } else {
            deleteById(todo.getId());
            todos.add(todo);
        }

        return todo;

    }

    public Todo deleteById(Long id) {
        Todo todo = findById(id);
        if (todos.remove(todo)) {
            return todo;
        }
        return null;
    }

    public Todo findById(Long id) {
        for (Todo todo : todos) {
            if (todo.getId() == id) {
                return todo;
            }
        }
        return null;
    }

}
