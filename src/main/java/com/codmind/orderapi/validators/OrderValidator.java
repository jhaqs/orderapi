package com.codmind.orderapi.validators;

import com.codmind.orderapi.entity.Order;
import com.codmind.orderapi.entity.OrderLine;
import com.codmind.orderapi.exceptions.ValidateServiceException;

public class OrderValidator {

    public static void save(Order order){
        /*if(order.getTotal()<0){
            throw new ValidateServiceException("El total es incorrecto.");
        }*/

        if(order.getLines()==null || order.getLines().isEmpty()){
            throw new ValidateServiceException("Las lineas son requerida.");
        }

        for(OrderLine line: order.getLines()){
            /*if(line.getPrice()<0){
                throw new ValidateServiceException("El precio es incorrecto");
            }*/
            if(line.getProduct()==null || line.getProduct().getId()==null){
                throw new ValidateServiceException("El producto es requerido");
            }
            if(line.getQuantity()==null){
                throw new ValidateServiceException("El cantidad es requerido");
            }
            if(line.getQuantity()<0){
                throw new ValidateServiceException("El cantidad es incorrecto");
            }
            /*if(line.getTotal()<0){
                throw new ValidateServiceException("El total es incorrecto");
            }*/
        }
    }
}
