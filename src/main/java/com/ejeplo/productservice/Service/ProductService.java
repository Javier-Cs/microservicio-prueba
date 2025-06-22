package com.ejeplo.productservice.Service;

import com.ejeplo.productservice.DTOs.ProductDTO;
import com.ejeplo.productservice.DTOs.ProductDTO_pu;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<ProductDTO> findAll();
    Optional<ProductDTO> findById(Long id);
    ProductDTO_pu saveProduct(ProductDTO_pu productPU_dto);
    boolean deleteById(Long id);
    ProductDTO_pu updateProduct(Long id, ProductDTO_pu productPU_dto);

//    ProductEntity saveProduct(ProductEntity productEntity);
//    Optional<ProductEntity> findById(Long id);
//    List<ProductEntity> findAll();
//    boolean deleteByIdPro(Long id);
//    ProductEntity updateProduct(ProductEntity productEntity);



}
