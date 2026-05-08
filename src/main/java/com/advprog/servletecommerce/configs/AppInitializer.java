package com.advprog.servletecommerce.configs;

import com.advprog.servletecommerce.application.service.UserService;
import com.advprog.servletecommerce.application.service.impl.UserServiceImpl;
import com.advprog.servletecommerce.application.validators.UserValidator;
import com.advprog.servletecommerce.domain.dao.UserDao;
import com.advprog.servletecommerce.infrastructure.dao.UserDaoImpl;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.RedisClient;

import javax.sql.DataSource;

@WebListener
public class AppInitializer implements ServletContextListener {
    private static final Logger log = LoggerFactory.getLogger(AppInitializer.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Database datasource
        DataSource dataSource = DatabaseConfig.getDataSource();

        // Redis client
        RedisClient redisClient = RedisConfig.getRedisClient();
        sce.getServletContext().setAttribute("redisClient", redisClient);

        // DAOs
        UserDao userDao = new UserDaoImpl(dataSource);

        // Validators
        UserValidator validator = new UserValidator(userDao);

        // Services
        UserService userService = new UserServiceImpl(userDao, validator);

        // Registering service
        sce.getServletContext().setAttribute("userService", userService);
    }
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("Disconnecting application connections");
        RedisConfig.close();
        DatabaseConfig.close();
    }
}
