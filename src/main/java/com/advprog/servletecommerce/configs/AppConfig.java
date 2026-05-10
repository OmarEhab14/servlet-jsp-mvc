//package com.advprog.servletecommerce.configs;
//
//import com.advprog.servletecommerce.application.service.ReviewService;
//import com.advprog.servletecommerce.application.service.UserService;
//import com.advprog.servletecommerce.application.service.impl.ReviewServiceImpl;
//import com.advprog.servletecommerce.application.service.impl.UserServiceImpl;
//import com.advprog.servletecommerce.application.validators.ReviewValidator;
//import com.advprog.servletecommerce.application.validators.UserValidator;
//import com.advprog.servletecommerce.domain.dao.ReviewDao;
//import com.advprog.servletecommerce.domain.dao.UserDao;
//import com.advprog.servletecommerce.infrastructure.dao.ReviewDaoImpl;
//import com.advprog.servletecommerce.infrastructure.dao.UserDaoImpl;
//import redis.clients.jedis.RedisClient;
//
//import javax.sql.DataSource;
//
/////  DI
//public class AppConfig {
//
//    private static DataSource dataSource;
//    private static RedisClient redisClient;
//
//    private static UserService userService;
//    private static ReviewService reviewService;
//    ///  inject dependencies
//    public static void init (){
//
//        dataSource = DatabaseConfig.getDataSource();
//        redisClient = RedisConfig.getRedisClient();
//
//        UserDao userDao = new UserDaoImpl(dataSource);
//        ReviewDao reviewDao = new ReviewDaoImpl(dataSource);
//
//        UserValidator userValidator = new UserValidator(userDao);
//        ReviewValidator reviewValidator = new ReviewValidator(reviewDao);
//
//        userService = new UserServiceImpl(userDao, userValidator);
//        reviewService = new ReviewServiceImpl(reviewDao, reviewValidator);
//    }
//
//    public static UserService getUserService() {
//        return userService;
//    }
//
//    public static ReviewService getReviewService() {
//        return reviewService;
//    }
//
//    public static RedisClient getRedisClient() {
//        return redisClient;
//    }
//}