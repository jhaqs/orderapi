package com.codmind.orderapi.converters;

import com.codmind.orderapi.dtos.OrderDTO;
import com.codmind.orderapi.dtos.OrderLineDTO;
import com.codmind.orderapi.entity.Order;
import com.codmind.orderapi.entity.OrderLine;
import com.codmind.orderapi.entity.Product;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class OrderConverter extends AbstractConverter<Order, OrderDTO>{
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");
    private ProductConverter productConverter = new ProductConverter();

    @Override
    public OrderDTO fromEntity(Order entity) {
        if(entity==null) return null;

        List<OrderLineDTO> lines = fromOrderLineEntity(entity.getLines());

        return OrderDTO.builder()
                .id(entity.getId())
                .lines(lines)
                .regDate(entity.getRegDate().format(dateTimeFormatter))
                .total(entity.getTotal())
                .build();
    }
    private List<OrderLineDTO> fromOrderLineEntity(List<OrderLine> lines){
        if(lines==null) return null;

        return lines.stream().map(e->OrderLineDTO.builder()
                .id(e.getId())
                .product(productConverter.fromEntity(e.getProduct()))
                .price(e.getPrice())
                .quantity(e.getQuantity())
                .total(e.getTotal())
                .build()).collect(Collectors.toList());

    }

    @Override
    public Order fromDTO(OrderDTO dto) {
        if(dto==null) return null;

        List<OrderLine> lines = fromOrderLineDTO(dto.getLines());

        return Order.builder()
                .id(dto.getId())
                .lines(lines)
                //.regDate(dto.getRegDate().format(dateTimeFormatter))
                .total(dto.getTotal())
                .build();
    }
    private List<OrderLine> fromOrderLineDTO(List<OrderLineDTO> lines){
        if(lines==null) return null;

        return lines.stream().map(e-> OrderLine.builder()
                .id(e.getId())
                .product(productConverter.fromDTO(e.getProduct()))
                .price(e.getPrice())
                .quantity(e.getQuantity())
                .total(e.getTotal())
                .build()
        ).collect(Collectors.toList());
    }
}
