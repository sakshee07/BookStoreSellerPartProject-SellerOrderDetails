package com.bridgelabz.sellerorderdetails.service;

import com.bridgelabz.sellerorderdetails.dto.BookDTO;
import com.bridgelabz.sellerorderdetails.dto.OrderDTO;
import com.bridgelabz.sellerorderdetails.dto.ResponseDTO;
import com.bridgelabz.sellerorderdetails.dto.SellerDTO;
import com.bridgelabz.sellerorderdetails.exception.OrderException;
import com.bridgelabz.sellerorderdetails.model.SellerOrders;
import com.bridgelabz.sellerorderdetails.repository.OrderRepo;
import com.bridgelabz.sellerorderdetails.utility.EmailSenderService;
import com.bridgelabz.sellerorderdetails.utility.TokenUtility;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService{
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private TokenUtility tokenUtility;
    @Autowired
    private EmailSenderService emailSender;
    @Autowired
    private RestTemplate restTemplate;
    //Model Mapper. The main role of ModelMapper is to map objects by determining how one object model is mapped to another called a Data Transformation Object (DTO).
    @Autowired
    private ModelMapper modelMapper;
    private String SELLER_ID_URI = "http://localhost:7070/seller/sellerId/";
    private String BOOK_ID_URI = "http://localhost:7071/book/getByBookId/";
    @Override
    public ResponseDTO addOrder(OrderDTO orderDTO) {
        //using rest template to get the seller and book details
        SellerDTO sellerDetails = restTemplate.getForObject(SELLER_ID_URI + orderDTO.getSellerId(), SellerDTO.class);
        BookDTO bookDetails = restTemplate.getForObject(BOOK_ID_URI + orderDTO.getBookId(), BookDTO.class);
        if(sellerDetails!=null && bookDetails!=null && sellerDetails.isVerify()){
            //Calculate Order Price
            double orderPrice = orderDTO.getOrderQuantity()*bookDetails.getPrice();
            SellerOrders sellerOrders = new SellerOrders(orderDTO);
            sellerOrders.setOrderPrice(orderPrice);
            orderRepo.save(sellerOrders);
            emailSender.sendEmail(sellerDetails.getEmailAddress(), "Order Status", "Order Details Added");
            String token = tokenUtility.createToken(sellerOrders.getOrderId());
            ResponseDTO respDTO = new ResponseDTO(token, sellerOrders);
            return respDTO;
        }else
            throw new OrderException("Invalid Seller ID | Seller is not Verified | Book ID");
    }
    //Adding Order Details Using Model Mapper
    @Override
    public ResponseDTO addOrderDetails(OrderDTO orderDTO) {
        //using rest template to get the seller and book details
        SellerDTO sellerDetails = restTemplate.getForObject(SELLER_ID_URI + orderDTO.getSellerId(), SellerDTO.class);
        BookDTO bookDetails = restTemplate.getForObject(BOOK_ID_URI + orderDTO.getBookId(), BookDTO.class);
        if(sellerDetails!=null && bookDetails!=null && sellerDetails.isVerify()){
            //Calculate Order Price
            double orderPrice = calculateOrderPrice(orderDTO.getBookId(), orderDTO.getOrderQuantity());
            //Tell to the ModelMapper to ignore the mapping when it finds multiple source property hierarchies:
            modelMapper.getConfiguration().setAmbiguityIgnored(true);
            SellerOrders sellerOrders = modelMapper.map(orderDTO, SellerOrders.class);
            sellerOrders.setOrderPrice(orderPrice);
            orderRepo.save(sellerOrders);
            emailSender.sendEmail(sellerDetails.getEmailAddress(), "Order Status", "Order Details Added");
            String token = tokenUtility.createToken(sellerOrders.getOrderId());
            ResponseDTO respDTO = new ResponseDTO(token, sellerOrders);
            return respDTO;
        }else
            throw new OrderException("Invalid Seller ID | Seller is not Verified | Book ID");
    }
    //Calculation of order price
    public double calculateOrderPrice(Long bookId, int quantity){
        BookDTO bookDetails = restTemplate.getForObject(BOOK_ID_URI + bookId, BookDTO.class);
        double orderPrice = quantity*bookDetails.getPrice();
        return orderPrice;
    }
    //Dispatching order
    @Override
    public ResponseDTO dispatchOrder(Long sellerId, Long orderId) {
        SellerDTO sellerDetails = restTemplate.getForObject(SELLER_ID_URI + sellerId, SellerDTO.class);
        Optional<SellerOrders> sellerOrders = orderRepo.findById(orderId);
        if(sellerDetails!=null && sellerDetails.isVerify() && sellerOrders.isPresent()){
            sellerOrders.get().setDispatch(true);
            orderRepo.save(sellerOrders.get());
            String token = tokenUtility.createToken(orderId);
            ResponseDTO respDTO = new ResponseDTO(token, "Order Dispatched");
            return respDTO;
        }else
            throw new OrderException("Invalid Seller ID | Not verified | Invalid Order Id");
    }
    @Override
    public ResponseDTO cancelOrder(Long sellerId, Long orderId) {
        SellerDTO sellerDetails = restTemplate.getForObject(SELLER_ID_URI + sellerId, SellerDTO.class);
        Optional<SellerOrders> sellerOrders = orderRepo.findById(orderId);
        if(sellerDetails!=null && sellerDetails.isVerify() && sellerOrders.isPresent()){
            sellerOrders.get().setCancel(true);
            orderRepo.save(sellerOrders.get());
            String token = tokenUtility.createToken(orderId);
            ResponseDTO respDTO = new ResponseDTO(token, "Order Cancelled");
            return respDTO;
        }else
            throw new OrderException("Invalid Seller ID | Not verified | Invalid Order Id");
    }
    //Get all the orders of a particular seller
    @Override
    public ResponseDTO getOrdersOfSeller(Long sellerId) {
        SellerDTO sellerDetails = restTemplate.getForObject(SELLER_ID_URI + sellerId, SellerDTO.class);
        if(sellerDetails!=null){
            List<SellerOrders> sellerOrdersList = orderRepo.findAllOrdersBySellerId(sellerId);
            ResponseDTO respDTO = new ResponseDTO("All Order List of Seller Id: "+sellerId, sellerOrdersList);
            return respDTO;
        }else
            throw new OrderException("Invalid Seller ID");
    }
    //Get Order details by order Id
    @Override
    public ResponseDTO getOrderByOrderId(Long orderId) {
        Optional<SellerOrders> sellerOrders = orderRepo.findById(orderId);
        if(sellerOrders.isPresent()){
            ResponseDTO respDTO = new ResponseDTO("Order Details with the order ID: "+orderId, sellerOrders);
            return respDTO;
        }else
            throw new OrderException("Invalid order ID | Order does not exist");
    }
    //Get all order history of a particular Customer from a particular seller
    @Override
    public List<SellerOrders> getOrderListByCustomerEmail(Long sellerId, String email) {
        SellerDTO sellerDetails = restTemplate.getForObject(SELLER_ID_URI + sellerId, SellerDTO.class);
        List<SellerOrders> sellerOrdersList = orderRepo.findOrdersByEmail(sellerId, email);
        if(sellerDetails==null && sellerOrdersList.isEmpty()){
            throw new OrderException("Invalid Seller ID | email Address not found");
        }else {
            return sellerOrdersList;
        }
    }
    //Edit order Details by orderID
    @Override
    public SellerOrders editOrderDetailsByOrderId(Long sellerId, Long orderId, OrderDTO orderDTO) {
        SellerDTO sellerDetails = restTemplate.getForObject(SELLER_ID_URI + sellerId, SellerDTO.class);
        BookDTO bookDetails = restTemplate.getForObject(BOOK_ID_URI + orderDTO.getBookId(), BookDTO.class);
        Optional<SellerOrders> sellerOrders = orderRepo.findById(orderId);
        if(sellerDetails!=null && bookDetails!=null && sellerOrders.isPresent() && sellerOrders.get().getSellerId().equals(sellerId)){
            //Calculate Order Price
            double orderPrice = calculateOrderPrice(orderDTO.getBookId(), orderDTO.getOrderQuantity());
            sellerOrders.get().setBookId(orderDTO.getBookId());
            sellerOrders.get().setOrderQuantity(orderDTO.getOrderQuantity());
            sellerOrders.get().setCustomerAddress(orderDTO.getCustomerAddress());
            sellerOrders.get().setCustomerEmail(orderDTO.getCustomerEmail());
            sellerOrders.get().setOrderPrice(orderPrice);
            orderRepo.save(sellerOrders.get());
            emailSender.sendEmail(sellerDetails.getEmailAddress(), "Order Status", "Order Details Updated, click on the below link for the updated order details:\nhttp://localhost:7072/order/getOrder/"+orderId);
            return sellerOrders.get();
        }else
        throw new OrderException("Invalid Seller ID | Order ID");
    }
    @Override
    public SellerOrders deleteOrderDetailsByOrderId(Long sellerId, Long orderId, OrderDTO orderDTO) {
        SellerDTO sellerDetails = restTemplate.getForObject(SELLER_ID_URI + sellerId, SellerDTO.class);
        Optional<SellerOrders> sellerOrders = orderRepo.findById(orderId);
        if(sellerDetails!=null && sellerOrders.isPresent() && sellerOrders.get().getSellerId().equals(sellerId)){
            orderRepo.deleteById(orderId);
            emailSender.sendEmail(sellerDetails.getEmailAddress(), "Order Status", "Your Order with the order ID: "+orderId+", is deleted successfully!");
            return sellerOrders.get();
        }else
            throw new OrderException("Invalid Order ID | Seller ID");
    }
}
