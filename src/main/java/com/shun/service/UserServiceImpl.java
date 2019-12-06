package com.shun.service;

import com.shun.dao.UserDao;
import com.shun.entity.User;
import com.shun.util.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * @author shun
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public User findByUsername(String username) {
        List<User> users = userDao.select(new User().setUsername(username));
        return users.get(0);
    }

    @Override
    public void save(User user) {
        userDao.insertSelective(user);
    }

    @Override
    public User login(User user) {
        if (user == null) return null;
        User reUser = findByUsername(user.getUsername());
        if (reUser == null) return null;
        if (Md5Util.verify(reUser.getPassword(), reUser.getSalt(), user.getPassword())) {
            return reUser;
        } else {
            return null;
        }
    }

    @Override
    public Boolean register(User user) {
        if (user == null || user.getUsername() == null || user.getPassword() == null || user.getUsername().isEmpty() || user.getPassword().isEmpty())
            return false;
        user.setId(UUID.randomUUID().toString().replace("-", ""))
                .setSalt(UUID.randomUUID().toString().replace("-", ""));
        user.setPassword(Md5Util.toMD5(user.getPassword(), user.getSalt()));
        save(user);
        return true;
    }
}
