package com.bridgelabz.sellerorderdetails.repository;

import com.bridgelabz.sellerorderdetails.model.SellerOrders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<SellerOrders, Long> {
    @Query(value = "SELECT * FROM seller_orders WHERE seller_id=:sellerId", nativeQuery = true)
    List<SellerOrders> findAllOrdersBySellerId(Long sellerId);
    @Query(value = "SELECT * FROM seller_orders WHERE seller_id=:sellerId AND customer_email=:email", nativeQuery = true)
    List<SellerOrders> findOrdersByEmail(Long sellerId, String email);
}
