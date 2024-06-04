package com.codmind.orderapi.controllers;

import com.codmind.orderapi.converters.ProductConverter;
import com.codmind.orderapi.dtos.ProductDTO;
import com.codmind.orderapi.entity.Product;
import com.codmind.orderapi.services.ProductService;
import com.codmind.orderapi.utils.WrapperResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductConverter productConverter;
    //private final ProductConverter productConverter = new ProductConverter();

    @GetMapping(value="products")
    public ResponseEntity<WrapperResponse<List<ProductDTO>>> findAll
            (@RequestParam(value = "pageNumber", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(value = "pageSize", required = false, defaultValue = "5") int pageSize){
        Pageable pageable = PageRequest.of(pageNumber,pageSize);

        List<Product> listProduct = productService.findAll(pageable);

        List<ProductDTO> listProductDTO = productConverter.fromEntity(listProduct);

        return new WrapperResponse<List<ProductDTO>>(true, "success", listProductDTO).createResponse(HttpStatus.OK);
    }

    @PostMapping(value = "products")
    public ResponseEntity<WrapperResponse<ProductDTO>> create(@RequestBody ProductDTO product){

        Product newProduct = productService.save(productConverter.fromDTO(product));

        ProductDTO productDTO = productConverter.fromEntity(newProduct);

        return new WrapperResponse<ProductDTO>(true, "success", productDTO).createResponse(HttpStatus.CREATED);
    }

    @GetMapping(value="products/{id}")
    public ResponseEntity<WrapperResponse<ProductDTO> > findById(@PathVariable Long id){

        Product product = productService.findById(id);

        ProductDTO productDTO = productConverter.fromEntity(product);

        return new WrapperResponse<ProductDTO>(true, "success", productDTO).createResponse(HttpStatus.OK);

    }

    @DeleteMapping(value = "products/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){

        productService.delete(id);
        WrapperResponse<?> response = new WrapperResponse<>(true, "success", null);
        response.createResponse(HttpStatus.OK);

        return new WrapperResponse<>(true, "success", null).createResponse(HttpStatus.OK);

    }

    @PutMapping(value = "products")
    public ResponseEntity<WrapperResponse<ProductDTO>> update(@RequestBody ProductDTO product){

        Product updateProduct = productService.save(productConverter.fromDTO(product));

        ProductDTO productDTO = productConverter.fromEntity(updateProduct);

        return new WrapperResponse<ProductDTO>(true, "success", productDTO).createResponse(HttpStatus.OK);

    }


}
