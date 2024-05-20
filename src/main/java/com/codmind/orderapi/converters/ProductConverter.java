package com.codmind.orderapi.converters;

import com.codmind.orderapi.dtos.ProductDTO;
import com.codmind.orderapi.entity.Product;

import java.util.List;
import java.util.stream.Collectors;

public class ProductConverter extends AbstractConverter<Product, ProductDTO>{

    @Override
    public ProductDTO fromEntity(Product entity) {
        return ProductDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .price(entity.getPrice()).build();
    }

    @Override
    public Product fromDTO(ProductDTO dto) {
        return Product.builder()
                .id(dto.getId())
                .name(dto.getName())
                .price(dto.getPrice()).build();
    }

    @Override
    public List<ProductDTO> fromEntity(List<Product> entitys) {
        return entitys.stream()
                .map(e->fromEntity(e))
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> fromDTO(List<ProductDTO> dtos) {
        return dtos.stream()
                .map(d->fromDTO(d))
                .collect(Collectors.toList());
    }
}
