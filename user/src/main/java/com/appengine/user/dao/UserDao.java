package com.appengine.user.dao;

import com.appengine.user.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<User, Long> {

    User findByUserName(String username);
}
