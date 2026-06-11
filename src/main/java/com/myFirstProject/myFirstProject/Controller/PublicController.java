package com.myFirstProject.myFirstProject.Controller;

import com.myFirstProject.myFirstProject.Service.ProductService;
import com.myFirstProject.myFirstProject.entity.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    ProductService productService;



    @GetMapping("/getAll")
    public List<Products> getAllProducts(){

       return productService.getAllProducts();

    }






}
