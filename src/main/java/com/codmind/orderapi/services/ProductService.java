package com.codmind.orderapi.services;

import com.codmind.orderapi.entity.Product;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    public List<Product> findAll(Pageable pageable);

    public Product save(Product product);

    public Product findById(Long id);

    public void delete(Long id);

}
