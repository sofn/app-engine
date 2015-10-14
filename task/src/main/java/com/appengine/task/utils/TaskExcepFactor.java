package com.appengine.task.utils;


import com.appengine.common.exception.ExcepFactor;
import com.appengine.common.utils.GlobalConstants;
import org.springframework.http.HttpStatus;

public class TaskExcepFactor extends ExcepFactor {

    public static final TaskExcepFactor TASK_NOT_EXISTS = new TaskExcepFactor(HttpStatus.BAD_REQUEST, 1,
            "task not exists", "任务不存在");

    protected TaskExcepFactor(HttpStatus httpStatus, int errorCode, String errorMsg, String errorMsgCn) {
        super(GlobalConstants.TASK_ID_AUTH, httpStatus, errorCode, errorMsg, errorMsgCn);
    }

}
