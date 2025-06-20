package com.ejeplo.productservice.Controller;

import com.ejeplo.productservice.Entity.ProductEntity;
import com.ejeplo.productservice.Exception.ResourceNotFoundException;
import com.ejeplo.productservice.Service.ProductService;
import com.ejeplo.productservice.Service.ProductServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class ProductController {

    final ProductServiceImpl productServiceImpl;

    public ProductController(ProductServiceImpl productServiceImpl) {
        this.productServiceImpl = productServiceImpl;
    }


    @GetMapping("/getAll")
    public ResponseEntity<List<ProductEntity>> getAll(){
        List<ProductEntity> products = productServiceImpl.findAll();
        return ResponseEntity.ok(products);

//        List<ProductEntity> products = productServiceImpl.findAll();
//        if(products == null || products.isEmpty()){
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return  new ResponseEntity<>(products, HttpStatus.OK);
    }


    @GetMapping("/getById/{id}")
    public ResponseEntity<ProductEntity> getById(@PathVariable("id") Long id){
        return productServiceImpl.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet( () -> ResponseEntity.notFound().build());

//        Optional<ProductEntity> product = productServiceImpl.findById(id);
//        if(product.isEmpty()){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return  ResponseEntity.status(HttpStatus.OK).body(product);
//        //new ResponseEntity<>(product, HttpStatus.OK);
    }


    @PostMapping("/save")
    public ResponseEntity<ProductEntity> save(@RequestBody ProductEntity product){
        ProductEntity producto = productServiceImpl.saveProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(producto);

//        try{
//            ProductEntity producto = productServiceImpl.saveProduct(product);
//            return ResponseEntity.status(HttpStatus.CREATED).body(producto);
//        }catch (Exception e){
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }

    }


    @DeleteMapping("/deletedById/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        try{
            //boolean result = productServiceImpl.deleteByIdPro(id);
//            if(result){
//               return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//            }else {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//            }
            productServiceImpl.deleteByIdPro(id);
            return ResponseEntity.noContent().build();
        }catch (ResourceNotFoundException e){
            return ResponseEntity.notFound().build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



    @PutMapping("/putProdu/{id}")
    public ResponseEntity<ProductEntity> putproduct (@PathVariable("id") Long id, @RequestBody ProductEntity productDetails){
//        if (productDetails.getIdProduct() == null || !productDetails.getIdProduct().equals(id)){
//            productDetails.setIdProduct(id);
//        }
        productDetails.setIdProduct(id);
        try{
            ProductEntity updatedProduct = productServiceImpl.updateProduct(productDetails);
            return ResponseEntity.status(HttpStatus.OK).body(updatedProduct);
        }catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}
