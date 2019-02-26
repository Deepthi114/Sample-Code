package com.pet.petorderservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pet.petorderservice.domain.Order;
import com.pet.petorderservice.domain.Product;
import com.pet.petorderservice.service.OrderService;
import com.pet.petorderservice.service.ProductService;

@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController {

	public static final Logger logger = LoggerFactory
			.getLogger(OrderController.class);

	@Autowired
	OrderService orderService;

	@Autowired
	ProductService productService;

	@RequestMapping(value = "/createOrder", method = RequestMethod.POST)
	public Order createOrder(@RequestBody Order order) {
		logger.info("Creating order : {}", order);
		return orderService.createOrder(order);
	}

	@RequestMapping(value = "/getAllOrders", method = RequestMethod.GET)
	public List<Order> getAllOrders() {
		List<Order> orderList = orderService.findAllOrders();
		return orderList;
	}

	@RequestMapping(value = "/getOrderByID/{orderID}", method = RequestMethod.GET)
	public Order getOrderByID(@PathVariable(name = "orderID") Long orderID) {
		return orderService.findById(orderID);
	}

	@RequestMapping(value = "/deleteOrder/{orderID}", method = RequestMethod.GET)
	public void deleteOrder(@PathVariable(name = "orderID") Long orderID) {
		orderService.deleteOrderById(orderID);
	}

	@RequestMapping(value = "/getAllProduct", method = RequestMethod.GET)
	public List<Product> getAllProduct() {
		return productService.findAllProducts();
	}

	@RequestMapping(value = "/getProductByID/{productID}", method = RequestMethod.GET)
	public Product getProductByID(
			@PathVariable(name = "productID") Long productID) {
		return productService.findById(productID);
	}

}
