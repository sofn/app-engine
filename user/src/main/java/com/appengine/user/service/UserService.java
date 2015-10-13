package com.appengine.user.service;

import com.appengine.user.dao.UserDao;
import com.appengine.user.domain.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author sofn
 * @version 1.0 Created at: 2015-10-13 17:02
 */
@Service
public class UserService {
    @Resource
    private UserDao dao;

    public boolean save(User user) {
        user = dao.save(user);
        return user.getId() > 0;
    }

    public User get(long id) {
        return dao.findOne(id);
    }
}
