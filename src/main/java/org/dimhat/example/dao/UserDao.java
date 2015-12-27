package org.dimhat.example.dao;

import java.util.List;

import org.dimhat.example.entity.User;

/**
 * 用户dao
 * @author dimhat
 * @date 2015年12月27日 下午10:37:19
 * @version 1.0
 */
public interface UserDao {

    User save(User user);

    User update(User user);

    void delete(Long id);

    User findOne(Long id);

    User findByUsername(String username);

    List<User> findAll();

}
