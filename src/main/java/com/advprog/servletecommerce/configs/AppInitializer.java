package com.advprog.servletecommerce.configs;

import com.advprog.servletecommerce.application.service.ProductService;
import com.advprog.servletecommerce.application.service.ReviewService;
import com.advprog.servletecommerce.application.service.UserService;
import com.advprog.servletecommerce.application.service.impl.ProductServiceImpl;
import com.advprog.servletecommerce.application.service.impl.ReviewServiceImpl;
import com.advprog.servletecommerce.application.service.impl.UserServiceImpl;
import com.advprog.servletecommerce.application.validators.ReviewValidator;
import com.advprog.servletecommerce.application.validators.UserValidator;
import com.advprog.servletecommerce.domain.dao.ProductDao;
import com.advprog.servletecommerce.domain.dao.ReviewDao;
import com.advprog.servletecommerce.domain.dao.UserDao;
import com.advprog.servletecommerce.infrastructure.dao.ProductDoaImpl;
import com.advprog.servletecommerce.infrastructure.dao.ReviewDaoImpl;
import com.advprog.servletecommerce.infrastructure.dao.UserDaoImpl;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.RedisClient;

import javax.sql.DataSource;
@Slf4j
@WebListener
public class AppInitializer implements ServletContextListener {
//    private static final Logger log = LoggerFactory.getLogger(AppInitializer.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Database datasource
        DataSource dataSource = DatabaseConfig.getDataSource();

        // Redis client
        RedisClient redisClient = RedisConfig.getRedisClient();
        sce.getServletContext().setAttribute("redisClient", redisClient);

        // DAOs
        UserDao userDao = new UserDaoImpl(dataSource);
        ProductDao productDao = new ProductDoaImpl(dataSource);
        ReviewDao reviewDao = new ReviewDaoImpl(dataSource);

        // Validators
        UserValidator validator = new UserValidator(userDao);
        ReviewValidator reviewValidator = new ReviewValidator(reviewDao);

        // Services
        UserService userService = new UserServiceImpl(userDao, validator);
        ProductService productService = new ProductServiceImpl(productDao);
        ReviewService reviewService = new ReviewServiceImpl(reviewDao , reviewValidator);

        // Registering service
        sce.getServletContext().setAttribute("userService", userService);
        sce.getServletContext().setAttribute("productService", productService);
        sce.getServletContext().setAttribute("reviewService", reviewService);
    }
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("Disconnecting application connections");
        RedisConfig.close();
        DatabaseConfig.close();
    }
}
