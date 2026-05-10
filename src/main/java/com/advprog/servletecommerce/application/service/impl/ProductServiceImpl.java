package com.advprog.servletecommerce.application.service.impl;

import java.util.List;


import com.advprog.servletecommerce.application.exceptions.NotFoundException;
import com.advprog.servletecommerce.application.exceptions.ProductAlreadyExistsException;
import com.advprog.servletecommerce.application.mappers.ProductMapper;
import com.advprog.servletecommerce.application.service.ProductService;
import com.advprog.servletecommerce.configs.RedisConfig;
import com.advprog.servletecommerce.domain.dao.ProductDao;
import com.advprog.servletecommerce.domain.dto.ProductDetailsDto;
import com.advprog.servletecommerce.domain.dto.ProductDto;
import com.advprog.servletecommerce.domain.entities.Product;
import com.advprog.servletecommerce.domain.entities.ProductDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.RedisClient;
import redis.clients.jedis.params.SetParams;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductDao productDao;
    private final RedisClient redis= RedisConfig.getRedisClient();
    private final ObjectMapper mapper = new ObjectMapper();
    private static final String PRODUCT_CACHE_KEY = "products:all";
    private static final int CACHE_EXPIRY_SECONDS = 3600; // 1 hour
    @Override
    public Product createProduct(Product product) {
        if(productDao.existsById(product.getId())){
            throw new ProductAlreadyExistsException(product.getId());
        }
        redis.del(PRODUCT_CACHE_KEY);
        log.info("Deleted products from cache");
       return productDao.save(product);
    }

    @Override
    public ProductDetailsDto getProduct(Long id) {
        String key="Product:"+id;
        try {
            String cachedData = redis.get(key);
            if (cachedData != null) {
                log.info("Retrieve product {} from cache",id);
                return mapper.readValue(cachedData, ProductDetailsDto.class);
            }
            log.info("Retrieve product {} from db",id);
            Product p = productDao.findById(id).orElseThrow(() -> new NotFoundException("Product not Found"));
            ProductDetailsDto dto=ProductMapper.toDetailsDto(p);
            redis.set(key, mapper.writeValueAsString(dto), SetParams.setParams().ex(CACHE_EXPIRY_SECONDS));
            return dto;
        }catch (Exception e) {
            // Log and handle Jackson checked exceptions
            log.error("Cache processing error", e);
            throw new RuntimeException("Error processing cache", e);
        }
    }

    @Override
    public List<ProductDto> getAllProducts() {
        try {
            String cachedData = redis.get(PRODUCT_CACHE_KEY);
            if(cachedData!=null){
                log.info("get all products from cache");
                return mapper.readValue(cachedData, new TypeReference<List<ProductDto>>(){});
            }
            List<Product> p= productDao.findAll();
            List<ProductDto> dto=  p.stream()
                                   .map(ProductMapper::toDto)
                                   .toList();
            log.info("get all products from db");
            redis.set(PRODUCT_CACHE_KEY, mapper.writeValueAsString(dto),SetParams.setParams().ex(CACHE_EXPIRY_SECONDS));
            return dto;
        }catch (Exception e) {
            // Log and handle Jackson checked exceptions
            log.error("Cache processing error", e);
            throw new RuntimeException("Error processing cache", e);
        }
    }

    @Override
    public void deleteProduct(Long id) {
       if(!productDao.existsById(id)){
           throw new NotFoundException("You are trying to delete product that doesn't exists");
       }
       if(productDao.deleteById(id)){
           log.info("Deleted product with id:{} successfully",id);
           String key="Product:"+id;
           redis.del(key);
           redis.del(PRODUCT_CACHE_KEY);
           log.info("Deleted product with id:{} successfully from cache and also all products",id);
       }else{
           throw new RuntimeException("Error trying to delete the product");
       }
    }
}
