package com.appengine.user.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@EqualsAndHashCode(of = "uid")
@Entity
@NoArgsConstructor
@ToString
@Table(name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;
    private String username;
    @JSONField(serialize = false)
    private String password;
    @JSONField(serialize = false)
    private String salt;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
      public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
    
    
}
