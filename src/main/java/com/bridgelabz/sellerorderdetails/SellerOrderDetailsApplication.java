package com.bridgelabz.sellerorderdetails;

import lombok.extern.slf4j.Slf4j;



import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@Slf4j
@EnableEurekaClient
public class SellerOrderDetailsApplication {
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    public static void main(String[] args) {
    	final Logger log = LoggerFactory.getLogger(SellerOrderDetailsApplication.class);
        SpringApplication.run(SellerOrderDetailsApplication.class, args);
        System.out.println("--------------------------------");
        log.info("\nHello! Welcome to Book Store Seller Project! ** seller orders");
    }

}
