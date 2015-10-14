package com.appengine.task.dao;

import com.appengine.task.domain.Task;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface TaskDao extends PagingAndSortingRepository<Task, Long>, JpaSpecificationExecutor<Task> {

    @Modifying
    @Query("delete from Task task where task.userId=?1")
    void deleteByUserId(Long id);

    List<Task> findAllByUserId(long uid);
}
