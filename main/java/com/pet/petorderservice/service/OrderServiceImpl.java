package com.pet.petorderservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pet.petorderservice.Exception.NoOrderFoundException;
import com.pet.petorderservice.Exception.ValidateOrderException;
import com.pet.petorderservice.dao.OrderRepository;
import com.pet.petorderservice.domain.Order;
import com.pet.petorderservice.domain.OrderDetails;
import com.pet.petorderservice.domain.Product;

@Service("orderService")
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	ProductService productService;

	public Order findById(Long orderId) {

		Order order = orderRepository.findById(orderId).get();

		if (order == null) {
			throw new NoOrderFoundException("Order not found for order id:"
					+ orderId);
		}

		return order;
	}

	public Order createOrder(Order order) {
		order = orderRepository.save(populateProductDetails(order));
		orderRepository.flush();

		return order;
	}

	public void saveOrder(Order order) {
		orderRepository.save(populateProductDetails(order));
	}

	public void updateOrder(Order order) {
		orderRepository.save(populateProductDetails(order));
	}

	public void deleteOrderById(Long orderId) {
		orderRepository.deleteById(orderId);
	}

	public void deleteAllItems() {
		orderRepository.deleteAll();
	}

	public boolean orderExists(Order order) {
		return findById(order.getOrderId()) != null;
	}

	public List<Order> findAllOrders() {

		List<Order> dataList = (List<Order>) orderRepository.findAll();

		if (dataList == null) {
			throw new NoOrderFoundException("Orders are not found.");
		}

		return dataList;
	}

	private Order populateProductDetails(Order order) {

		List<OrderDetails> orderDetailsList = null;

		double totalCost = 0;

		if (order.getCustomerId() == null) {
			throw new ValidateOrderException("CustomerId  missing.");
		}

		if (order.getItems() != null) {

			orderDetailsList = new ArrayList<OrderDetails>();

			for (OrderDetails item : order.getItems()) {

				OrderDetails orderDetails = new OrderDetails();
				orderDetails.setId(item.getId());
				orderDetails.setOrderId(item.getOrderId());
				orderDetails.setProductId(item.getProductId());

				if (item.getProductId() == null) {
					throw new ValidateOrderException("ProductId missing.");
				}

				if (item.getQuantity() == null) {
					throw new ValidateOrderException("Quantity missing.");
				}

				if (item.getQuantity() != null
						&& item.getQuantity().longValue() <= 0) {
					throw new ValidateOrderException(
							"Quantity should be greater than zero.");
				}

				Product product = productService.findById(item.getProductId());

				if (product == null) {
					throw new ValidateOrderException(
							"Invalid product details with productId:"
									+ item.getProductId());
				}

				orderDetails.setProductName(product.getName());
				orderDetails.setProductPrice(product.getPrice());
				orderDetails.setQuantity(item.getQuantity());
				double total = item.getQuantity() * product.getPrice();
				orderDetails.setTotal(total);

				totalCost = totalCost + total;

				orderDetailsList.add(orderDetails);
			}
		} else {
			throw new ValidateOrderException("Product details missing.");
		}

		order.setItems(orderDetailsList);
		order.setTotalCost(totalCost);

		return order;
	}
}
