package com.tasks.application.components;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends MongoRepository<Task, String> {

    Task findByLabel(String label);

    public Optional<List<Task>> findByAssignedTo(String assignedTo);

    public Optional<List<Task>> findAllByTag(String tag);

    public List<Task> findAll();

    void delete(String label);
}
