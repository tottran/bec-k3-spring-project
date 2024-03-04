package com.example.demo.service;

import com.example.demo.entity.Todo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {
    private List<Todo> todos = new ArrayList<>();

    private void initFakeList() {
        Integer id;
        for (int i = 0; i <= 1; i++) {
            id = i;
            todos.add(new Todo(id, "code", false));
        }
    }

    public TodoService() {
        this.initFakeList();
    }

    public List<Todo> getList() {
        return todos;
    }
}
