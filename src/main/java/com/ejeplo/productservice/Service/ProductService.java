package com.ejeplo.productservice.Service;

import com.ejeplo.productservice.Entity.ProductEntity;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    ProductEntity saveProduct(ProductEntity productEntity);
    Optional<ProductEntity> findById(Long id);
    List<ProductEntity> findAll();
    boolean deleteByIdPro(Long id);
    ProductEntity updateProduct(ProductEntity productEntity);



}
