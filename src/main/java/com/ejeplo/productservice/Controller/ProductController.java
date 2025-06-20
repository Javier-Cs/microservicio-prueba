package com.ejeplo.productservice.Controller;

import com.ejeplo.productservice.Entity.ProductEntity;
import com.ejeplo.productservice.Exception.ResourceNotFoundException;
import com.ejeplo.productservice.Service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ApiProdu/v1/")
public class ProductController {

    final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ProductEntity>> getAll(){
        List<ProductEntity> products = productService.findAll();
        if(products == null || products.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return  new ResponseEntity<>(products, HttpStatus.OK);
    }


    @GetMapping("/getById/{id}")
    public ResponseEntity<Optional<ProductEntity>> getById(@PathVariable("id") Long id){
        Optional<ProductEntity> product = productService.findById(id);
        if(product.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return  ResponseEntity.status(HttpStatus.OK).body(product);
        //new ResponseEntity<>(product, HttpStatus.OK);
    }


    @PostMapping("/save")
    public ResponseEntity<ProductEntity> save(@RequestBody ProductEntity product){
        try{
            ProductEntity producto = productService.saveProduct(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(producto);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }


    @DeleteMapping("/Delet/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        try{
            boolean result = productService.deleteByIdPro(id);
            if(result){
               return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



    @PutMapping("put/{id}")
    public ResponseEntity<ProductEntity> put (@PathVariable("id") Long id, @RequestBody ProductEntity productDetails){
        if (productDetails.getIdProduct() == null || !productDetails.getIdProduct().equals(id)){
            productDetails.setIdProduct(id);
        }
        try{
            ProductEntity updatedProduct = productService.updateProduct(productDetails);
            return ResponseEntity.status(HttpStatus.OK).body(updatedProduct);
        }catch (ResourceNotFoundException ex){
            return ResponseEntity.notFound().build();
        }
    }


}
