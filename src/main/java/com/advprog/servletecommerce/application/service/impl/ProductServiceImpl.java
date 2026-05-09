package com.advprog.servletecommerce.application.service.impl;

import java.util.List;

import com.advprog.servletecommerce.application.enums.HttpStatus;
import com.advprog.servletecommerce.application.exceptions.AppException;
import com.advprog.servletecommerce.application.exceptions.NotFoundException;
import com.advprog.servletecommerce.application.service.ProductService;
import com.advprog.servletecommerce.domain.dao.ProductDao;
import com.advprog.servletecommerce.domain.entities.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductDao productDao;
    @Override
    public Product createProduct(Product product) {
        if(productDao.existsById(product.getId())){
            throw new RuntimeException("Product with this id already exists");
        }
       return productDao.save(product);
    }

    @Override
    public Product getProduct(Long id) {
        return productDao.findById(id).orElseThrow(()->new NotFoundException("Product not Found"));
    }

    @Override
    public List<Product> getAllProducts() {
        return productDao.findAll();
    }

    @Override
    public void deleteProduct(Long id) {
       if(!productDao.existsById(id)){
           throw new NotFoundException("You are trying to delete product that doesn't exists");
       }
       if(productDao.deleteById(id)){
           log.info("Deleted product with id:{} successfully",id);
       }else{
           throw new RuntimeException("Error trying to delete the product");
       }
    }
}
