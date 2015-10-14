package com.appengine.task.service;

import com.appengine.task.dao.TaskDao;
import com.appengine.task.domain.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Authors: sofn
 * Version: 1.0  Created at 2015-10-12 00:17.
 */
@Service
public class TaskService {
    private TaskDao taskDao;

    public Task getTask(Long id) {
        return taskDao.findOne(id);
    }

    public void saveTask(Task entity) {
        taskDao.save(entity);
    }

    public boolean deleteTask(Long id) {
        Task task = getTask(id);
        if (task == null) {
            return false;
        }
        taskDao.delete(id);
        return true;
    }

    public List<Task> getAllTask(long uid) {
        return taskDao.findAllByUserId(uid);
    }

    @Autowired
    public void setTaskDao(TaskDao taskDao) {
        this.taskDao = taskDao;
    }
}
