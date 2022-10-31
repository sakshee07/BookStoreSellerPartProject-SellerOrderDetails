package com.bridgelabz.sellerorderdetails.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
public class SellerDTO {
    private Long SellerId;
    private String businessName;
    private String sellerName;
    private String gstn;
    private String sellerWebsite;
    private String emailAddress;
    private String userName;
    private String password;
    private String contactNumber;
    private boolean verify;
    private LocalDateTime createdTimeStamp;
    private LocalDateTime updatedTimeStamp;
	public Long getSellerId() {
		return SellerId;
	}
	public String getBusinessName() {
		return businessName;
	}
	public String getSellerName() {
		return sellerName;
	}
	public String getGstn() {
		return gstn;
	}
	public String getSellerWebsite() {
		return sellerWebsite;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public String getUserName() {
		return userName;
	}
	public String getPassword() {
		return password;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public boolean isVerify() {
		return verify;
	}
	public LocalDateTime getCreatedTimeStamp() {
		return createdTimeStamp;
	}
	public LocalDateTime getUpdatedTimeStamp() {
		return updatedTimeStamp;
	}
	public void setSellerId(Long sellerId) {
		SellerId = sellerId;
	}
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
	public void setGstn(String gstn) {
		this.gstn = gstn;
	}
	public void setSellerWebsite(String sellerWebsite) {
		this.sellerWebsite = sellerWebsite;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public void setVerify(boolean verify) {
		this.verify = verify;
	}
	public void setCreatedTimeStamp(LocalDateTime createdTimeStamp) {
		this.createdTimeStamp = createdTimeStamp;
	}
	public void setUpdatedTimeStamp(LocalDateTime updatedTimeStamp) {
		this.updatedTimeStamp = updatedTimeStamp;
	}
    
    
}
