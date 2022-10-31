package com.bridgelabz.sellerorderdetails.controller;

import com.bridgelabz.sellerorderdetails.dto.OrderDTO;
import com.bridgelabz.sellerorderdetails.dto.ResponseDTO;
import com.bridgelabz.sellerorderdetails.model.SellerOrders;
import com.bridgelabz.sellerorderdetails.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    IOrderService orderService;

    //Add Order Details
    @PostMapping("/addOrder")
    public ResponseEntity<String> addOderDetail(@RequestBody OrderDTO orderDTO) {
        ResponseDTO response = orderService.addOrder(orderDTO);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }
    //Adding order details using Model Mapper
    @PostMapping("/addOrderDetails")
    public ResponseEntity<String> addOderDetails(@RequestBody OrderDTO orderDTO) {
        ResponseDTO response = orderService.addOrderDetails(orderDTO);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }
    //Dispatching the order
    @PostMapping("/dispatchingOrder/{sellerId}/{orderId}")
    public ResponseEntity<String> dispatchOrderDetails(@PathVariable Long sellerId, @PathVariable Long orderId) {
        ResponseDTO response = orderService.dispatchOrder(sellerId, orderId);
        return new ResponseEntity(response, HttpStatus.OK);
    }
    //Cancelling the order
    @PostMapping("/cancelOrder/{sellerId}/{orderId}")
    public ResponseEntity<String> cancellingOrderDetails(@PathVariable Long sellerId, @PathVariable Long orderId) {
        ResponseDTO response = orderService.cancelOrder(sellerId, orderId);
        return new ResponseEntity(response, HttpStatus.OK);
    }
    //Get all order list of a seller
    @GetMapping("/allOrdersOfSeller/{sellerId}")
    public ResponseEntity<String> getOrderDetailsOfSeller(@PathVariable Long sellerId) {
        ResponseDTO response = orderService.getOrdersOfSeller(sellerId);
        return new ResponseEntity(response, HttpStatus.OK);
    }
    //Get Order details by order ID
    @GetMapping("/getOrder/{orderId}")
    public ResponseEntity<String> getOrderByOrderId(@PathVariable Long orderId) {
        ResponseDTO response = orderService.getOrderByOrderId(orderId);
        return new ResponseEntity(response, HttpStatus.OK);
    }
    //Get all order history of a particular Customer from a particular seller
    @GetMapping("/getOrdersOfCustomer/{sellerId}/{email}")
    public ResponseEntity<ResponseDTO> getOrderByCustomerEmail(@PathVariable Long sellerId,@PathVariable String email) {
        List<SellerOrders> response = orderService.getOrderListByCustomerEmail(sellerId, email);
        ResponseDTO responseDTO = new ResponseDTO("Order list of email: "+email, response);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    //update order by order ID
    @PutMapping("/editOrderDetails/{sellerId}/{orderId}")
    public ResponseEntity<ResponseDTO> updateOrderDetails(@PathVariable Long sellerId, @PathVariable Long orderId, @RequestBody OrderDTO orderDTO) {
        SellerOrders sellerOrders = orderService.editOrderDetailsByOrderId(sellerId, orderId, orderDTO);
        ResponseDTO respDTO = new ResponseDTO("Data Update info", sellerOrders);
        return new ResponseEntity<>(respDTO, HttpStatus.OK);
    }
    //Delete the order details by order ID
    //update order by order ID
    @DeleteMapping("/deleteOrderDetails/{sellerId}/{orderId}")
    public ResponseEntity<ResponseDTO> deleteOrderDetails(@PathVariable Long sellerId, @PathVariable Long orderId, @RequestBody OrderDTO orderDTO) {
        SellerOrders sellerOrders = orderService.deleteOrderDetailsByOrderId(sellerId, orderId, orderDTO);
        ResponseDTO respDTO = new ResponseDTO("Deleted Order ID: "+orderId, sellerOrders);
        return new ResponseEntity<>(respDTO, HttpStatus.OK);
    }
}
