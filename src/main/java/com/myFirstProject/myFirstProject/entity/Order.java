package com.myFirstProject.myFirstProject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // internal relation only (not serialized)
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private Users user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items;

    private int totalPrice;

    private String status;
    // CREATED, PAID, SHIPPED, DELIVERED, CANCELLED

    private String paymentMethod;
    // COD, UPI, CARD

    private String shippingAddress;

    private LocalDateTime createdAt;
}
