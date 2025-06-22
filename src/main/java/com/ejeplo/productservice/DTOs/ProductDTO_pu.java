package com.ejeplo.productservice.DTOs;

import lombok.Data;

@Data
public class ProductDTO_pu {

    private String nameProduct;
    private String descriptionProduct;
    private Double priceProduct;
    private String distribuidor;
    private String codeProducto;
    private Double priceDistribuidor;
}
