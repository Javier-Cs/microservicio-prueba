package com.ejeplo.productservice.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@Table(name = "product_tbl")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long idProduct;

    @Column(name = "name_produ")
    @NotBlank(message = "El nombre es obligatorio")
    private String nameProduct;

    @Column(name = "descrip_produ")
    @NotNull(message = "El descripcion no puede ser nulo")
    private String descriptionProduct;

    @Column(name = "price_produ")
    @NotNull(message = "El precio no puede ser nulo")
    private Double priceProduct;


//    create database productservice_db;
//    use productservice_db
//    create table product_tbl(
//            id_producto int primary key identity(1,1) not null,
//    name_produ nvarchar(30) not null,
//    descrip_produ nvarchar(200) not null,
//    price_produ numeric(18,2)
//    );

}
