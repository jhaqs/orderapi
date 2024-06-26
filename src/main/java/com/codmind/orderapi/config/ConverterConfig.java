package com.codmind.orderapi.config;

import com.codmind.orderapi.converters.OrderConverter;
import com.codmind.orderapi.converters.ProductConverter;
import com.codmind.orderapi.converters.UserConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.format.DateTimeFormatter;


@Configuration
public class ConverterConfig {

    @Value("${config.datetimeFormat}")
    private String datetimeFormat;

    @Bean
    public ProductConverter getProductConverter(){
        return new ProductConverter();
    }

    @Bean
    public OrderConverter getOrderConverter(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(datetimeFormat);
        return new OrderConverter(dateTimeFormatter, getProductConverter());
    }

    @Bean
    public UserConverter getUserConverter(){
        return new UserConverter();
    }
}
