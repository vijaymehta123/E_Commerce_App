package com.myFirstProject.myFirstProject.Service;

import com.myFirstProject.myFirstProject.Repository.CartRepository;
import com.myFirstProject.myFirstProject.Repository.ProductRepository;
import com.myFirstProject.myFirstProject.Repository.UserRepository;
import com.myFirstProject.myFirstProject.entity.Cart;
import com.myFirstProject.myFirstProject.entity.CartItem;
import com.myFirstProject.myFirstProject.entity.Products;
import com.myFirstProject.myFirstProject.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    public void addItemTOCart(Long userId, int prodcutId,int quantity){
              Users user = userRepository.findById(userId).orElseThrow(()-> new RuntimeException("User Not Found"));

              Cart cart = user.getCart();
              if(cart == null){
                  throw new RuntimeException("Cart not found for user " + userId);
              }

        Products product = productRepository.findById(prodcutId).orElseThrow(()-> new RuntimeException("product not found"));

            CartItem cartItem = null;

            for(CartItem item : cart.getItems()){
                if(item.getProduct().getProductId().equals(prodcutId)){
                    cartItem=item;
                    break;
                }
            }
            if(cartItem != null){
                cartItem.setQuantity(cartItem.getQuantity()+quantity);
            }else {
                CartItem item = new CartItem();
                item.setProduct(product);
                item.setCart(cart);
                item.setQuantity(quantity);
                cart.getItems().add(item);
            }
            int total =0;
            for(CartItem item : cart.getItems()){
                total+= item.getProduct().getProduct_price() *item.getQuantity();
            }
            cart.setTotalPrice(total);
            cartRepository.save(cart);
    }

    public Cart getCart(Long userId){

        Cart cart = cartRepository.findByUser_Id(userId);

        if(cart != null){
            return cart;
        }

        Users user = userRepository.findById(userId).orElseThrow(()->new RuntimeException("NO User Found"));

        Cart newCart = new Cart();
        newCart.setUser(user);
        newCart.setTotalPrice(0);
        cartRepository.save(newCart);
        return newCart;

    }
    public Cart getOrCreateCart(Long userId) {

        Cart cart = cartRepository.findByUser_Id(userId);
        if (cart != null) return cart;


        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Cart newCart = new Cart();
        newCart.setUser(user);
        newCart.setTotalPrice(0);


        return cartRepository.save(newCart);
    }
    public Cart decreaseQuantity(Long userId, Long cartItemId) {

        Cart cart = cartRepository.findByUser_Id(userId);
        if (cart == null) throw new RuntimeException("Cart not found");

        cart.getItems().removeIf(item -> {
            if (item.getId().equals(cartItemId)) {
                item.setQuantity(item.getQuantity() - 1);
                return item.getQuantity() <= 0; // remove if zero
            }
            return false;
        });

        recalculateTotal(cart);
        return cartRepository.save(cart);
    }
    public Cart increaseQuantity(Long userId, Long cartItemId) {

        Cart cart = cartRepository.findByUser_Id(userId);
        if (cart == null) throw new RuntimeException("Cart not found");

        for (CartItem item : cart.getItems()) {
            if (item.getId().equals(cartItemId)) {
                item.setQuantity(item.getQuantity() + 1);
                break;
            }
        }

        recalculateTotal(cart);
        return cartRepository.save(cart);
    }

    private void recalculateTotal(Cart cart) {
        int total = 0;
        for (CartItem item : cart.getItems()) {
            total += item.getProduct().getProduct_price() * item.getQuantity();
        }
        cart.setTotalPrice(total);
    }
    public Cart removeItem(Long userId, Long cartItemId) {

        Cart cart = cartRepository.findByUser_Id(userId);
        if (cart == null) {
            throw new RuntimeException("Cart not found");
        }

        // remove item
        cart.getItems().removeIf(item -> item.getId().equals(cartItemId));

        // recalculate total
        int total = 0;
        for (CartItem item : cart.getItems()) {
            total += item.getProduct().getProduct_price() * item.getQuantity();
        }
        cart.setTotalPrice(total);

        return cartRepository.save(cart);
    }



}
