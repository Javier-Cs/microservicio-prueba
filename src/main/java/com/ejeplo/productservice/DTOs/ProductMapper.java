package com.ejeplo.productservice.DTOs;

import com.ejeplo.productservice.Entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {
//    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    // Mapea la entidad Product a ProductDTO
    ProductDTO toPDTO(ProductEntity productEntity);
    // mapear de DTO a Entidad
    ProductEntity toPEnt(ProductDTO productDTO);


    ProductDTO_pu toDTO_P_U(ProductEntity productEntity);
    ProductEntity toEnt_P_U(ProductDTO_pu ProductDTO_pu);

    @Mapping(target = "idProduct", ignore = true)
    void updateEntidadDeDTO_pu(ProductDTO_pu productDTO_pu, @MappingTarget ProductEntity productEntity);


}
