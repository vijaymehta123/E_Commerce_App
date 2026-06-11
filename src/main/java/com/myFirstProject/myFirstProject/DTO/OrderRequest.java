package com.myFirstProject.myFirstProject.DTO;

import lombok.Data;

@Data
public class OrderRequest {
    private String address;
    private String paymentMethod;
}
