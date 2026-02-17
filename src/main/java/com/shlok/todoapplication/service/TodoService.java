package com.shlok.todoapplication.service;

import com.shlok.todoapplication.model.Todo;
import com.shlok.todoapplication.model.User;
import com.shlok.todoapplication.repository.TodoRepository;
import com.shlok.todoapplication.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    private TodoRepository todoRepository;
    private UserRepository userRepository;

    public TodoService(TodoRepository todoRepository, UserRepository userRepository) {
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
    }

    public Todo createTodoForParticularUser(Long userId, Todo todo){
        User user = userRepository.findById(userId).get();
        Todo newTodo = todoRepository.save(todo);
        user.getTodos().add(newTodo);
        userRepository.save(user);
        return newTodo;
    }

    public List<Todo> findTodosByUserId(Long userId){
        User user = userRepository.findById(userId).get();
        List<Todo> todos = user.getTodos();
        return todos;
    }

    public Todo findTodoByUserIdAndTodoId(Long userId,Long todoId){
        User user = userRepository.findById(userId).get();
        List<Todo> todos = user.getTodos();
        for (Todo todo:todos){
            if (todo.getId()==todoId){
                return todo;
            }
        }
        return null;
    }

    public Todo updateTodoForParticularUser(Long userId, Long todoId, Todo todo){
        User user = userRepository.findById(userId).get();
        List<Todo> todos = user.getTodos();
        for (Todo todo1:todos){
            if (todo1.getId()==todoId){
                user.getTodos().remove(todo1);
                break;
            }
        }
        Todo updatedTodo = todoRepository.save(todo);
        user.getTodos().add(updatedTodo);
        userRepository.save(user);
        return updatedTodo;
    }

    public void deleteTodoForParticularUser(Long userId, Long todoId){
        //we will not require userid for deletion, because every todoId will be unique.
        todoRepository.deleteById(todoId);
    }
}
