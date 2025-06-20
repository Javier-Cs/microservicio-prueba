package com.ejeplo.productservice.Service;

import com.ejeplo.productservice.Entity.ProductEntity;
import com.ejeplo.productservice.Exception.ResourceNotFoundException;
import com.ejeplo.productservice.Repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    //Inyección de dependencias a través del constructor (preferido en Spring)
    final  ProductRepository productRepository;

    // inyeccion por constructor recomendado
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    // asegura que el guardado sea de forma atomica
    @Transactional
    public ProductEntity saveProduct(ProductEntity productEntity) {
        return productRepository.save(productEntity);
    }

    @Override
    // Optimización para métodos de solo lectura
    @Transactional(readOnly = true)
    public Optional<ProductEntity> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductEntity> findAll() {
        return productRepository.findAll();
    }

    @Override
    @Transactional
    public boolean deleteByIdPro(Long id) {
        // si no existe, enviamos el estado correspondiente
        if(!productRepository.existsById(id)){
            throw new ResourceNotFoundException("No se encontro el id del producto"+id);
        }
        productRepository.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public ProductEntity updateProduct(ProductEntity productUpEntity) {
        return productRepository.findById(productUpEntity.getIdProduct())
                        .map(ProductExist -> {
                            if(productUpEntity.getNameProduct() != null ){
                                ProductExist.setNameProduct(productUpEntity.getNameProduct());
                            }
                            if(productUpEntity.getDescriptionProduct() != null ){
                                ProductExist.setDescriptionProduct(productUpEntity.getDescriptionProduct());
                            }
                            if (productUpEntity.getPriceProduct() != null){
                                ProductExist.setPriceProduct(productUpEntity.getPriceProduct());
                            }
                            return productRepository.save(ProductExist);
                        }
                ).orElseThrow( () -> new ResourceNotFoundException("No se encontro product con ID: "+productUpEntity.getIdProduct()));
    }
}
