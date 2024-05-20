package com.codmind.orderapi.services.impl;

import com.codmind.orderapi.entity.Product;
import com.codmind.orderapi.exceptions.GeneralServiceException;
import com.codmind.orderapi.exceptions.NoDataFoundException;
import com.codmind.orderapi.exceptions.ValidateServiceException;
import com.codmind.orderapi.repository.ProductRepository;
import com.codmind.orderapi.services.ProductService;
import com.codmind.orderapi.validators.ProductValidator;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findAll(Pageable pageable) {
        try {
            List<Product> listProduct = productRepository.findAll(pageable).toList();
            return listProduct;

        }catch(ValidateServiceException | NoDataFoundException e){
            log.info(e.getMessage());
            throw e;
        }catch(Exception e){
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public Product save(Product product){
        try {
            ProductValidator.save(product);

            if(product.getId()==null){
                return productRepository.save(product);
            }else{
                Product findProduct = productRepository.findById(product.getId())
                        .orElseThrow(()->new NoDataFoundException("No existe el producto"));

                findProduct.setName(product.getName());
                findProduct.setPrice(product.getPrice());
                productRepository.save(findProduct);
                return findProduct;
            }

        }catch(ValidateServiceException | NoDataFoundException e){
            log.info(e.getMessage());
            throw e;
        }catch(Exception e){
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }

    }

    @Override
    public Product findById(Long id){
        log.info("findById =>"+id);

        try {
            return productRepository.findById(id)
                    .orElseThrow(()->new NoDataFoundException("No existe el producto"));

        }catch(ValidateServiceException | NoDataFoundException e){
            log.info(e.getMessage());
            throw e;
        }catch(Exception e){
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public void delete(Long id){
        try {
            Product product = productRepository.findById(id)
                    .orElseThrow(()->new NoDataFoundException("No existe el producto"));
            productRepository.delete(product);

        }catch(ValidateServiceException | NoDataFoundException e){
            log.info(e.getMessage());
            throw e;
        }catch(Exception e){
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }


    }

}
