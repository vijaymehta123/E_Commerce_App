package com.myFirstProject.myFirstProject.Service;

import com.myFirstProject.myFirstProject.Repository.CartRepository;
import com.myFirstProject.myFirstProject.Repository.OrderRepository;
import com.myFirstProject.myFirstProject.Repository.UserRepository;
import com.myFirstProject.myFirstProject.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderRepository orderRepository;

    public Order placeOrder(Long userId, String address, String paymentMethod) {

        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Cart cart = cartRepository.findByUser_Id(userId);
        if (cart == null || cart.getItems().isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        Order order = new Order();
        order.setUser(user);
        order.setTotalPrice(cart.getTotalPrice());
        order.setStatus("CREATED");
        order.setPaymentMethod(paymentMethod);
        order.setShippingAddress(address);
        order.setCreatedAt(LocalDateTime.now());

        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem cartItem : cart.getItems()) {

            OrderItem orderItem = new OrderItem();

            // INTERNAL relation only
            orderItem.setOrder(order);

            // ✅ SNAPSHOT DATA
            orderItem.setProductId(cartItem.getProduct().getProductId());
            orderItem.setProductName(cartItem.getProduct().getProduct_name());
            orderItem.setProductImage(cartItem.getProduct().getImgLink());
            orderItem.setProductPrice(cartItem.getProduct().getProduct_price());

            orderItem.setQuantity(cartItem.getQuantity());

            orderItems.add(orderItem);
        }

        order.setItems(orderItems);

        // save order
        Order savedOrder = orderRepository.save(order);

        // clear cart
        cart.getItems().clear();
        cart.setTotalPrice(0);
        cartRepository.save(cart);

        return savedOrder;
    }

    // USER: get own orders
    public List<Order> getOrdersByUser(Long userId) {
        return orderRepository.findByUser_Id(userId);
    }

    // ADMIN: get all orders
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}

