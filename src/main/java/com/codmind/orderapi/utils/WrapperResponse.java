package com.codmind.orderapi.utils;

import com.codmind.orderapi.dtos.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WrapperResponse<T> {
    private boolean ok;
    private String message;
    private T body;

    public ResponseEntity<WrapperResponse<T>> createResponse(HttpStatus status){
        return new ResponseEntity(this, status);
    }
}
