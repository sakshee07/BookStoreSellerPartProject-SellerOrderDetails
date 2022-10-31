package com.bridgelabz.sellerorderdetails.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookDTO {
    private Long sellerId;
    private String bookName;
    private String authorName;
    private String bookDescription;
    private String bookImage;
    double price;
    int quantity;
	public Long getSellerId() {
		return sellerId;
	}
	public String getBookName() {
		return bookName;
	}
	public String getAuthorName() {
		return authorName;
	}
	public String getBookDescription() {
		return bookDescription;
	}
	public String getBookImage() {
		return bookImage;
	}
	public double getPrice() {
		return price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public void setBookDescription(String bookDescription) {
		this.bookDescription = bookDescription;
	}
	public void setBookImage(String bookImage) {
		this.bookImage = bookImage;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
    
    
}
