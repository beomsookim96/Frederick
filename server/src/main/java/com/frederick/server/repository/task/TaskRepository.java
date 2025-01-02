package com.frederick.server.repository.task;

import com.frederick.server.document.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends MongoRepository<Task, String> {
    List<Task> findByUserId(String userId); // 특정 유저의 Task 조회
}
