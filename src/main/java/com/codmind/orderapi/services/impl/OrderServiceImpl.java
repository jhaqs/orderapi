package com.codmind.orderapi.services.impl;

import com.codmind.orderapi.entity.Order;
import com.codmind.orderapi.entity.OrderLine;
import com.codmind.orderapi.entity.Product;
import com.codmind.orderapi.exceptions.GeneralServiceException;
import com.codmind.orderapi.exceptions.NoDataFoundException;
import com.codmind.orderapi.exceptions.ValidateServiceException;
import com.codmind.orderapi.repository.OrderLineRepository;
import com.codmind.orderapi.repository.OrderRepository;
import com.codmind.orderapi.services.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderLineRepository orderLineRepository;

    @Override
    public List<Order> findAll(Pageable pageable) {
        try {
            List<Order> listOrder = orderRepository.findAll(pageable).toList();
            return listOrder;

        }catch(ValidateServiceException | NoDataFoundException e){
            log.info(e.getMessage());
            throw e;
        }catch(Exception e){
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Order findById(Long id) {
        try {
            Order order = orderRepository.findById(id)
                    .orElseThrow(()->new NoDataFoundException("La orden no existe"));
            return order;

        }catch(ValidateServiceException | NoDataFoundException e){
            log.info(e.getMessage());
            throw e;
        }catch(Exception e){
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void delete(Long id) {
        try {
            Order order = orderRepository.findById(id)
                    .orElseThrow(()->new NoDataFoundException("La orden no existe"));
            orderRepository.delete(order);
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
    public Order save(Order order) {
        try {
            order.getLines().forEach(line->line.setOrder(order));
            if(order.getId()==null){
                //create
                order.setRegDate(LocalDateTime.now());
                Order newOrder = orderRepository.save(order);
                return newOrder;
            }
            //update
            Order savedOrder = orderRepository.findById(order.getId())
                    .orElseThrow(()->new NoDataFoundException("La orden no existe"));
            order.setRegDate(savedOrder.getRegDate());

            List<OrderLine> deletedLines = savedOrder.getLines();
            deletedLines.removeAll(order.getLines());
            orderLineRepository.deleteAll(deletedLines);

            return orderRepository.save(order);

        }catch(ValidateServiceException | NoDataFoundException e){
            log.info(e.getMessage());
            throw e;
        }catch(Exception e){
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }
}
