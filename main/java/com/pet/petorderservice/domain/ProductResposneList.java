package com.pet.petorderservice.domain;

public class ProductResposneList {

	private String statusCode;
	private Product[] body;

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public Product[] getBody() {
		return body;
	}

	public void setBody(Product[] body) {
		this.body = body;
	}

}
