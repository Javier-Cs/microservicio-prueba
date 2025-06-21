package com.ejeplo.productservice.DTOs;

import com.ejeplo.productservice.Entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    // Mapea la entidad Product a ProductDTO
    ProductDTO productDTOaProducto(ProductEntity productEntity);

    // mapear de DTO a Entidad
    ProductEntity productEntityaProducto(ProductDTO productDTO);


}
