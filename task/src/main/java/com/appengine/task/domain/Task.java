package com.appengine.task.domain;

import com.appengine.common.domain.IdEntity;
import com.appengine.user.domain.User;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

/**
 * Authors: sofn
 * Version: 1.0  Created at 2015-10-12 00:12.
 */
@Entity
@Table(name = "task")
public class Task extends IdEntity {

    public String title;
    public String description;

    @JoinColumn(name = "user_id")
//    public User user;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
