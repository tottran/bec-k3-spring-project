package com.example.demo.controller;

import com.example.demo.entity.Todo;
import com.example.demo.service.TodoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("")
public class MockAPIService {
    TodoService todoService = new TodoService();

    @GetMapping("/todos")
    public List<Todo> getListTodos() {
        return todoService.getList();
    }
}
