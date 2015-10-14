package com.appengine.task.domain;

import lombok.*;

import javax.persistence.*;

/**
 * Authors: sofn
 * Version: 1.0  Created at 2015-10-12 00:12.
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String title;
    public String description;
    @Column(name = "user_id", nullable = false)
    public long userId;

    public Task(String title, String description, long userId) {
        this.title = title;
        this.description = description;
        this.userId = userId;
    }
}
