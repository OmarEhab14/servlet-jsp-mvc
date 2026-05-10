package com.advprog.servletecommerce.application.service.impl;

import java.util.List;


import com.advprog.servletecommerce.application.exceptions.NotFoundException;
import com.advprog.servletecommerce.application.exceptions.ProductAlreadyExistsException;
import com.advprog.servletecommerce.application.mappers.ProductMapper;
import com.advprog.servletecommerce.application.service.ProductService;
import com.advprog.servletecommerce.domain.dao.ProductDao;
import com.advprog.servletecommerce.domain.dto.ProductDetailsDto;
import com.advprog.servletecommerce.domain.dto.ProductDto;
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
            throw new ProductAlreadyExistsException(product.getId());
        }
       return productDao.save(product);
    }

    @Override
    public ProductDetailsDto getProduct(Long id) {
        Product p=productDao.findById(id).orElseThrow(()->new NotFoundException("Product not Found"));
        return ProductMapper.toDetailsDto(p);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> p= productDao.findAll();
        return p.stream()
               .map(ProductMapper::toDto)
               .toList();
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
