package org.dimhat.example.service;

import java.io.FileNotFoundException;

import org.apache.log4j.Logger;
import org.dimhat.example.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Log4jConfigurer;

/**
 * TODO
 * @author dimhat
 * @date 2015年12月28日 上午12:29:41
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class UserServiceTest {

    static {
        try {
            Log4jConfigurer.initLogging("classpath:log4j.properties");
        } catch (FileNotFoundException ex) {
            System.err.println("Cannot Initialize log4j");
        }
    }

    @Autowired
    private UserService userService;

    private Logger      logger = Logger.getLogger(UserServiceTest.class);

    @Test
    public void testRegister() {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("123456");
        User registerUser = userService.register(user);
        logger.info(registerUser);

        User user2 = new User();
        user2.setUsername("guest");
        user2.setPassword("123");
        User registerUser2 = userService.register(user2);
        logger.info(registerUser2);
    }

    @Test
    public void testLogin() {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("123456");
        User registerUser = userService.login(user);
        logger.info(registerUser);
    }

    @Test
    public void testLogin2() {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("123");
        User registerUser = userService.login(user);
        logger.info(registerUser);
    }

    @Test
    public void testLogin3() {
        User user = new User();
        user.setUsername("adm");
        user.setPassword("123456");
        User registerUser = userService.login(user);
        logger.info(registerUser);
    }

    @Test
    public void testChangePassowrd() {
        userService.changePassword(1L, "123");
    }
}
