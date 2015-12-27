##spring jdbc template example
使用spring-jdbc操作数据库<br/>
写示例的时候更方便，实际生产中可能更多用hibernate和mybatis

###jdbcTemplate.query
查询返回类型的映射
```
query(String sql, RowMapper<T> rowMapper, Object... args)
```

1. 使用默认
```
new BeanPropertyRowMapper(User.class)
```

2. 使用自定义
```
 new RowMapper<User>() {

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

}
```

