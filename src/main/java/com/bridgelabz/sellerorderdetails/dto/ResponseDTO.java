package com.bridgelabz.sellerorderdetails.dto;

import com.bridgelabz.sellerorderdetails.model.SellerOrders;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@Data
@NoArgsConstructor
public class ResponseDTO {
    String message;
    Object response;
    public ResponseDTO(String message, String response) {
        this.message = message;
        this.response = response;
    }
    public ResponseDTO(String message, ResponseDTO response) {
        this.message = message;
        this.response = response;
    }

    public ResponseDTO(String message, SellerOrders response) {
        this.message = message;
        this.response = response;
    }

    public ResponseDTO(String message, List<SellerOrders> response) {
        this.message = message;
        this.response = response;
    }
    public ResponseDTO(String message, Optional<SellerOrders> response) {
        this.message = message;
        this.response = response;
    }
	public String getMessage() {
		return message;
	}
	public Object getResponse() {
		return response;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setResponse(Object response) {
		this.response = response;
	}
    
    
}
