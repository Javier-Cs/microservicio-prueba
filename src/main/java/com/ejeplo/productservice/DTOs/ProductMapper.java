package com.ejeplo.productservice.DTOs;

import com.ejeplo.productservice.Entity.ProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
//    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    // Mapea la entidad Product a ProductDTO
    ProductDTO toPDTO(ProductEntity productEntity);

    // mapear de DTO a Entidad
    ProductEntity toPEnt(ProductDTO productDTO);


}
