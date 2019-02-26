package com.pet.petorderservice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.pet.petorderservice.dao.OrderRepository;
import com.pet.petorderservice.domain.Order;
import com.pet.petorderservice.domain.OrderDetails;
import com.pet.petorderservice.domain.Product;
import com.pet.petorderservice.service.OrderService;
import com.pet.petorderservice.service.OrderServiceImpl;
import com.pet.petorderservice.service.ProductService;
import com.pet.petorderservice.service.ProductServiceImpl;

@RunWith(SpringRunner.class)
public class PetOrderServiceApplicationTests {

	@Mock
	OrderRepository orderRepository;

	@Mock
	ProductService productService;

	@InjectMocks
	ProductServiceImpl mockProductServiceImpl = new ProductServiceImpl();

	@Mock
	OrderService orderService;

	@InjectMocks
	OrderServiceImpl mockOrderServiceImpl = new OrderServiceImpl();

	// Mock product
	public Product returnProduct = new Product(1l, "Container", 25.50);

	// Mock order
	public Date orderDate = new Date();
	private static List<OrderDetails> productsOrdered;
	static {
		productsOrdered = new ArrayList<OrderDetails>();
		productsOrdered.add(new OrderDetails(1L, 1L, 100L, 7L));
		productsOrdered.add(new OrderDetails(2L, 1L, 101L, 5L));
	};

	public Order mockOrder = new Order(1L, 2l, productsOrdered);

	// Test OrderService implementation
	@Test
	public void test_OrderService_findById() {
		Assert.assertNotEquals(orderService.findById(1L), mockOrder);
	}
	
	@Test
	public void test_OrderService_CreateOrder() {
		Assert.assertNotEquals(orderService.createOrder(mockOrder), mockOrder);
	}

}
