package com.myFirstProject.myFirstProject.Controller;

import com.myFirstProject.myFirstProject.DTO.CartRequest;
import com.myFirstProject.myFirstProject.DTO.MeResponse;
import com.myFirstProject.myFirstProject.DTO.OrderRequest;
import com.myFirstProject.myFirstProject.Service.CartService;
import com.myFirstProject.myFirstProject.Service.OrderService;
import com.myFirstProject.myFirstProject.Service.UserService;
import com.myFirstProject.myFirstProject.entity.Cart;
import com.myFirstProject.myFirstProject.entity.Order;
import com.myFirstProject.myFirstProject.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;


    @Autowired
    private CartService cartService;

    @Autowired
 private OrderService orderService;

    @PostMapping("/cart")
public String addCartItem(@RequestBody CartRequest cartRequest,@AuthenticationPrincipal Users user){
        cartService.addItemTOCart(user.getId(),cartRequest.getProductId(),cartRequest.getQuantity());
        return "added successfully";
}
    @PutMapping("/increase/{cartItemId}")
    public Cart increase(
            @PathVariable Long cartItemId,
            @AuthenticationPrincipal Users user
    ) {
        return cartService.increaseQuantity(user.getId(), cartItemId);
    }
    @PutMapping("/decrease/{cartItemId}")
    public Cart decrease(
            @PathVariable Long cartItemId,
            @AuthenticationPrincipal Users user
    ) {
        return cartService.decreaseQuantity(user.getId(), cartItemId);
    }
    @DeleteMapping("/remove/{cartItemId}")
    public Cart removeItem(
            @PathVariable Long cartItemId,
            @AuthenticationPrincipal Users user
    ) {
        return cartService.removeItem(user.getId(), cartItemId);
    }


    @GetMapping("/me")
public MeResponse getUser(@AuthenticationPrincipal Users user){
    MeResponse mr = new MeResponse();
           mr.setEmail(user.getEmail());
           mr.setName(user.getName());
           mr.setId(user.getId());
        return mr;
}


    @GetMapping("/{userId}")
    public Cart getCart(@PathVariable Long userId) {

     return cartService.getOrCreateCart(userId);

    }
    @PostMapping("/place")
    public Order placeOrder(
            @AuthenticationPrincipal Users user,
            @RequestBody OrderRequest request
    ) {

        return orderService.placeOrder(
                user.getId(),
                request.getAddress(),
                request.getPaymentMethod()
        );
    }
    @GetMapping("/myorder")
    public List<Order> getMyOrders(
            @AuthenticationPrincipal Users user
    ) {
        return orderService.getOrdersByUser(user.getId());
    }


}
