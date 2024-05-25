package com.codmind.orderapi.dtos;

import com.codmind.orderapi.entity.Order;
import com.codmind.orderapi.entity.Product;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineDTO {
    private Long id;
    private ProductDTO product;
    private Double price;
    private Double quantity;
    private Double total;
}
