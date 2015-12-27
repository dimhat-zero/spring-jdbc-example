package org.dimhat.example.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.dimhat.example.dao.UserDao;
import org.dimhat.example.entity.User;
import org.dimhat.example.exception.MyAppException;
import org.dimhat.example.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户服务实现类
 * @author dimhat
 * @date 2015年12月27日 下午11:34:04
 * @version 1.0
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    /** 
     * @see org.dimhat.example.service.UserService#register(org.dimhat.example.entity.User)
     */
    @Override
    public User register(User user) {
        user.setPassword(encryptPassword(user.getPassword()));
        return userDao.save(user);
    }

    //对密码进行加密
    private String encryptPassword(String password) {
        return MD5Util.MD5(password);
    }

    /** 
     * @see org.dimhat.example.service.UserService#login(org.dimhat.example.entity.User)
     */
    @Override
    public User login(User userForm) {
        User dbUser = userDao.findByUsername(userForm.getUsername());
        if (dbUser == null) {
            throw new MyAppException("找不到用户名");
        }
        boolean equals = dbUser.getPassword().equals(encryptPassword(userForm.getPassword()));
        if (equals == false) {
            throw new MyAppException("密码错误");
        }
        //更新最后登录时间
        dbUser.setLastLogin(new Timestamp(new Date().getTime()));
        userDao.update(dbUser);
        return dbUser;
    }

    /** 
     * @see org.dimhat.example.service.UserService#changePassword(java.lang.Long, java.lang.String)
     */
    @Override
    public void changePassword(Long userId, String newPassword) {
        User user = userDao.findOne(userId);
        user.setPassword(encryptPassword(newPassword));
        userDao.update(user);
    }

    /** 
     * @see org.dimhat.example.service.UserService#findAll()
     */
    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

}
