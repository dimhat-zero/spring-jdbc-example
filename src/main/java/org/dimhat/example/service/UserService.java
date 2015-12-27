package org.dimhat.example.service;

import java.util.List;

import org.dimhat.example.entity.User;

/**
 * 用户服务
 * @author dimhat
 * @date 2015年12月27日 下午11:30:39
 * @version 1.0
 */
public interface UserService {

    User register(User user);

    User login(User user);

    void changePassword(Long userId, String newPassword);

    List<User> findAll();
}
