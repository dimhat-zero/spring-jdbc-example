package org.dimhat.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.dimhat.example.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

/**
 * 用户dao实现类
 * @author dimhat
 * @date 2015年12月27日 下午10:45:47
 * @version 1.0
 */
@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /** 
     * @see org.dimhat.example.dao.UserDao#save(org.dimhat.example.entity.User)
     */
    @Override
    public User save(final User user) {
        //sql语句
        final String sql = "insert into sys_user(username,password,locked,last_login) values(?,?,?,?)";

        //使用jdbcTemplate执行sql语句，避免sql注入
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, new String[] { "id" });
                int index = 1;
                ps.setString(index++, user.getUsername());
                ps.setString(index++, user.getPassword());
                ps.setBoolean(index++, user.getLoacked());
                ps.setTimestamp(index++, user.getLastLogin());
                return ps;
            }
        }, keyHolder);
        //回填主键
        user.setId(keyHolder.getKey().longValue());
        return user;
    }

    /** 
     * @see org.dimhat.example.dao.UserDao#update(org.dimhat.example.entity.User)
     */
    @Override
    public User update(User user) {
        String sql = "update sys_user set username=?,password=?,locked=?,last_login=? where id=?";
        jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getLoacked(), user.getLastLogin(),
            user.getId());
        return user;
    }

    /** 
     * @see org.dimhat.example.dao.UserDao#delete(java.lang.Long)
     */
    @Override
    public void delete(Long id) {
        String sql = "delete from sys_user where id = ?";
        jdbcTemplate.update(sql, id);
    }

    /** 
     * @see org.dimhat.example.dao.UserDao#findOne(java.lang.Long)
     */
    @Override
    public User findOne(Long id) {
        String sql = "select * from sys_user where id = ?";
        User user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper(User.class), id);
        return user;
    }

    /** 
     * @see org.dimhat.example.dao.UserDao#findByUsername(java.lang.String)
     */
    @Override
    public User findByUsername(String username) {
        String sql = "select * from sys_user where username=?";
        List<User> users = jdbcTemplate.query(sql, new RowMapper<User>() {

            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setLoacked(rs.getBoolean("locked"));
                user.setLastLogin(rs.getTimestamp("last_login"));
                return user;
            }

        }, username);
        if (users.size() == 0)
            return null;
        return users.get(0);
    }

    /** 
     * @see org.dimhat.example.dao.UserDao#findAll()
     */
    @Override
    public List<User> findAll() {
        String sql = "select * from sys_user";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper(User.class));
    }

}
