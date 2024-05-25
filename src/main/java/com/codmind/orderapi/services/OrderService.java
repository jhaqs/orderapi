package com.codmind.orderapi.services;

import com.codmind.orderapi.entity.Order;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface OrderService {
    public List<Order> findAll(Pageable pageable);

    public Order findById(Long id);

    public void delete(Long id);

    public Order save(Order order);
}
