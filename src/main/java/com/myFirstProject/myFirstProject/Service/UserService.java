package com.myFirstProject.myFirstProject.Service;

import com.myFirstProject.myFirstProject.Repository.CartRepository;
import com.myFirstProject.myFirstProject.Repository.UserRepository;
import com.myFirstProject.myFirstProject.entity.Cart;
import com.myFirstProject.myFirstProject.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;


    public Users add(Users user){
        Users savedUser = userRepository.findById(user.getId()).orElseThrow();

        Cart cart = new Cart();
        cart.setUser(savedUser);
        cart.setTotalPrice(0);
        cartRepository.save(cart);

        return savedUser;
    }

}
