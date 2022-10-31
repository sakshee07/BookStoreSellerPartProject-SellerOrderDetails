package com.bridgelabz.sellerorderdetails.service;

import com.bridgelabz.sellerorderdetails.dto.OrderDTO;
import com.bridgelabz.sellerorderdetails.dto.ResponseDTO;
import com.bridgelabz.sellerorderdetails.model.SellerOrders;

import java.util.List;

public interface IOrderService {
    ResponseDTO addOrder(OrderDTO orderDTO);

    ResponseDTO dispatchOrder(Long sellerId, Long orderId);

    ResponseDTO cancelOrder(Long sellerId, Long orderId);

    ResponseDTO getOrdersOfSeller(Long sellerId);
    //Using Model Mapper
    ResponseDTO addOrderDetails(OrderDTO orderDTO);

    ResponseDTO getOrderByOrderId(Long orderId);

    List<SellerOrders> getOrderListByCustomerEmail(Long sellerId, String email);

    SellerOrders editOrderDetailsByOrderId(Long sellerId, Long orderId, OrderDTO orderDTO);

    SellerOrders deleteOrderDetailsByOrderId(Long sellerId, Long orderId, OrderDTO orderDTO);
}
