package com.ejeplo.productservice.DTOs;

import lombok.Data;

@Data
public class ProductDTO {
    private String nameProduct;
    private String descriptionProduct;
    private Double priceProduct;
}
