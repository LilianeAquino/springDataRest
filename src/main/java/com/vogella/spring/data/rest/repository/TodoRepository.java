package com.vogella.spring.data.rest.repository;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.scheduling.annotation.Async;

import com.vogella.spring.data.rest.Todo;

@RepositoryRestResource(collectionResourceRel="tasks", path="tasks")

public interface TodoRepository extends JpaRepository<Todo, Long> {

    List<Todo> getBySummary(String summary);
    
    Optional<Todo> findBySummaryAndDueDate(String summary, Date date);
    
    Stream<Todo> readBySummaryOrDescription(String summary, String description);
    
    long countBySummary(String summary);
    
    @Query("Select t from Todo t where t.dueDate BETWEEN '2016-01-01' AND '2016-12-31'")
    List<Todo> getTodosOf2016();
    
    @Async
    CompletableFuture<List<Todo>> findAsyncJava8BySummary(String summary);

    @Async
    Future<List<Todo>> findAsyncBeforeJava8BySummary(String summary);
    
}
