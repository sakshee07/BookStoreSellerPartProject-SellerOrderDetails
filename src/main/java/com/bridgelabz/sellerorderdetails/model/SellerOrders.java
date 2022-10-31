package com.bridgelabz.sellerorderdetails.model;

import com.bridgelabz.sellerorderdetails.dto.BookDTO;
import com.bridgelabz.sellerorderdetails.dto.OrderDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class SellerOrders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "orderId", nullable = false)
     Long orderId;
     Long sellerId;
     Long bookId;
     int orderQuantity;
     double orderPrice;
     String customerAddress;
     String customerEmail;
     boolean dispatch;
     boolean cancel;
     LocalDateTime orderPlacedOn = LocalDateTime.now();

    public SellerOrders(OrderDTO orderDTO){
        this.sellerId = orderDTO.getSellerId();
        this.bookId = orderDTO.getBookId();
        this.orderQuantity = orderDTO.getOrderQuantity();
        this.customerAddress=orderDTO.getCustomerAddress();
        this.customerEmail= orderDTO.getCustomerEmail();
    }
    
    public SellerOrders() {
		// TODO Auto-generated constructor stub
	}
	public Long getOrderId() {
		return orderId;
	}

	public Long getSellerId() {
		return sellerId;
	}

	public Long getBookId() {
		return bookId;
	}

	public int getOrderQuantity() {
		return orderQuantity;
	}

	public double getOrderPrice() {
		return orderPrice;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public boolean isDispatch() {
		return dispatch;
	}

	public boolean isCancel() {
		return cancel;
	}

	public LocalDateTime getOrderPlacedOn() {
		return orderPlacedOn;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	public void setOrderPrice(double orderPrice) {
		this.orderPrice = orderPrice;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public void setDispatch(boolean dispatch) {
		this.dispatch = dispatch;
	}

	public void setCancel(boolean cancel) {
		this.cancel = cancel;
	}

	public void setOrderPlacedOn(LocalDateTime orderPlacedOn) {
		this.orderPlacedOn = orderPlacedOn;
	}
    
    
}
