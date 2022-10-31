package com.bridgelabz.sellerorderdetails.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class OrderDTO {
    private Long sellerId;
    private Long bookId;
    private int orderQuantity;
    private String customerAddress;
    private String customerEmail;
	public Long getSellerId() {
		return sellerId;
	}
	public Long getBookId() {
		return bookId;
	}
	public int getOrderQuantity() {
		return orderQuantity;
	}
	public String getCustomerAddress() {
		return customerAddress;
	}
	public String getCustomerEmail() {
		return customerEmail;
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
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
    
    
}
