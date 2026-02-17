package com.shlok.todoapplication.controller;

import com.shlok.todoapplication.model.Todo;
import com.shlok.todoapplication.service.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoController {
    private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/users/{userId}/todos")
    public List<Todo> getAllTodosOfUser(@PathVariable Long userId){
        return todoService.findTodosByUserId(userId);
    }

    @GetMapping("/users/{userId}/todos/{todoId}")
    public Todo getTodo(@PathVariable Long userId, @PathVariable Long todoId){
        return todoService.findTodoByUserIdAndTodoId(userId,todoId);
    }

    @PostMapping("/users/{userId}/todos")
    public Todo createTodo(@PathVariable Long userId, @RequestBody Todo todo){
        return todoService.createTodoForParticularUser(userId,todo);
    }

    @PutMapping("/users/{userId}/todos/{todoId}")
    public Todo updateTodo(@PathVariable Long userId, @PathVariable Long todoId, @RequestBody Todo todo){
        return todoService.updateTodoForParticularUser(userId,todoId,todo);
    }

    @DeleteMapping("/users/{userId}/todos/{todoId}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long userId,
                                           @PathVariable Long todoId) {
        todoService.deleteTodoForParticularUser(userId,todoId);
        return ResponseEntity.noContent().build();
    }
}
