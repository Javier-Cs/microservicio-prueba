package com.ejeplo.productservice.Service;

import com.ejeplo.productservice.DTOs.ProductDTO;
import com.ejeplo.productservice.DTOs.ProductMapper;
import com.ejeplo.productservice.Entity.ProductEntity;
import com.ejeplo.productservice.Exception.ResourceNotFoundException;
import com.ejeplo.productservice.Repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{

    //Inyección de dependencias a través del constructor (preferido en Spring)
    final ProductMapper productMapper;
    final ProductRepository productRepository;

    public ProductServiceImpl(ProductMapper productMapper, ProductRepository productRepository) {
        this.productMapper = productMapper;
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDTO> findAll() {
        List<ProductEntity> productEntities = productRepository.findAll();

        return productEntities.stream()
                .map(productMapper::toPDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProductDTO> findById(Long id) {
        Optional<ProductEntity> productOpt = productRepository.findById(id);
        if(productOpt.isPresent()) {
            ProductEntity productEntity = productOpt.get();
            return Optional.of(productMapper.toPDTO(productEntity));
        }
        return   Optional.empty();
        //return productRepository.findById(id).map(productMapper::toPDTO);
    }

    @Override
    public ProductDTO saveProduct(ProductDTO productDTO) {
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public ProductDTO updateProduct(ProductDTO productDTO) {
        return null;
    }


    //    @Override
//    @Transactional(readOnly = true)
//    public Optional<ProductEntity> findById(Long id) {
//        return productRepository.findById(id);
//    }


//    @Override
//    @Transactional
//    public ProductEntity saveProduct(ProductEntity productEntity) {
//        return productRepository.save(productEntity);
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public Optional<ProductEntity> findById(Long id) {
//        return productRepository.findById(id);
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public List<ProductEntity> findAll() {
//        return productRepository.findAll();
//    }
//
//    @Override
//    @Transactional
//    public boolean deleteByIdPro(Long id) {
//        // si no existe, enviamos el estado correspondiente
//        if(!productRepository.existsById(id)){
//            throw new ResourceNotFoundException("No se encontro el id del producto"+id);
//        }
//        productRepository.deleteById(id);
//        return true;
//    }
//
//    @Override
//    @Transactional
//    public ProductEntity updateProduct(ProductEntity productUpEntity) {
//        return productRepository.findById(productUpEntity.getIdProduct())
//                        .map(ProductExist -> {
//                            if(productUpEntity.getNameProduct() != null ){
//                                ProductExist.setNameProduct(productUpEntity.getNameProduct());
//                            }
//                            if(productUpEntity.getDescriptionProduct() != null ){
//                                ProductExist.setDescriptionProduct(productUpEntity.getDescriptionProduct());
//                            }
//                            if (productUpEntity.getPriceProduct() != null){
//                                ProductExist.setPriceProduct(productUpEntity.getPriceProduct());
//                            }
//                            return productRepository.save(ProductExist);
//                        }
//                ).orElseThrow( () -> new ResourceNotFoundException("No se encontro product con ID: "+productUpEntity.getIdProduct()));
//    }
}
