package com.codmind.orderapi.controllers;

import com.codmind.orderapi.converters.OrderConverter;
import com.codmind.orderapi.dtos.OrderDTO;
import com.codmind.orderapi.dtos.ProductDTO;
import com.codmind.orderapi.entity.Order;
import com.codmind.orderapi.services.OrderService;
import com.codmind.orderapi.utils.WrapperResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    OrderConverter orderConverter = new OrderConverter();

    @GetMapping(value = "orders")
    public ResponseEntity<WrapperResponse<List<OrderDTO>>> findAll(@RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber,
                                                  @RequestParam(name = "pageSize", defaultValue = "10") int pageSize){
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<Order> listOrder = orderService.findAll(pageable);

        return new WrapperResponse<List<OrderDTO>>(true, "success", orderConverter.fromEntity(listOrder)).createResponse();
    }

    @GetMapping(value = "orders/{id}")
    public ResponseEntity<WrapperResponse<OrderDTO>> findById(@PathVariable(name = "id") Long id){
        Order order = orderService.findById(id);

        return new WrapperResponse<OrderDTO>(true, "success", orderConverter.fromEntity(order)).createResponse();
    }

    @PostMapping(value = "orders")
    public ResponseEntity<WrapperResponse<OrderDTO>> create(@RequestBody OrderDTO orderDTO){
        Order newOrder = orderService.save(orderConverter.fromDTO(orderDTO));

        return new WrapperResponse<OrderDTO>(true, "success", orderConverter.fromEntity(newOrder)).createResponse(HttpStatus.CREATED);
    }

    @PutMapping(value = "orders")
    public ResponseEntity<WrapperResponse<OrderDTO>> update(@RequestBody OrderDTO orderDTO){
        Order updateOrder = orderService.save(orderConverter.fromDTO(orderDTO));

        return new WrapperResponse<OrderDTO>(true, "success", orderConverter.fromEntity(updateOrder)).createResponse(HttpStatus.CREATED);
    }

    @DeleteMapping(value = "orders/{id}")
    public ResponseEntity<?> delete(@PathVariable(name="id") Long id){
        orderService.delete(id);

        return new WrapperResponse<>(true, "success", null).createResponse();
    }

}
