package com.appengine.user.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@EqualsAndHashCode(of = "id")
@Entity
@NoArgsConstructor
@ToString
@Table(name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String username;
    @JSONField(serialize = false)
    private String password;
    @JSONField(serialize = false)
    private String salt;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}