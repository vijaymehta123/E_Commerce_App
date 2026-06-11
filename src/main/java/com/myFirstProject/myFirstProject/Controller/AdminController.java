package com.myFirstProject.myFirstProject.Controller;
import com.myFirstProject.myFirstProject.DTO.ProductRequestDto;
import com.myFirstProject.myFirstProject.Service.OrderService;
import com.myFirstProject.myFirstProject.Service.ProductService;
import com.myFirstProject.myFirstProject.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/admin")

public class AdminController {

    @Autowired
ProductService productService;
    @Autowired
    private OrderService orderService;

    @PostMapping("/add")
    public String addProduct(@RequestBody ProductRequestDto pr){
        productService.insertProduct(pr);
        return "Product Inserted SuccessFully";
    }

    @GetMapping("/orders")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @DeleteMapping("/product/{id}")
    public String deleteProduct(@PathVariable int id){

        productService.deleteProduct(id);
        return "Product Deleted SuccessFully";
    }

}
