package com.todo.service;

import com.todo.model.Todo;
import com.todo.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    private final TodoRepository repo;

    public TodoService(TodoRepository repo) {
        this.repo = repo;
    }

    public List<Todo> findAll() { return repo.findAll(); }

    public Todo findById(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Todo not found: " + id));
    }

    public Todo create(Todo t) { return repo.save(t); }

    public Todo update(Long id, Todo t) {
        Todo existing = findById(id);
        existing.setTitle(t.getTitle());
        existing.setDescription(t.getDescription());
        existing.setCompleted(t.isCompleted());
        return repo.save(existing);
    }

    public void delete(Long id) { repo.deleteById(id); }
}
