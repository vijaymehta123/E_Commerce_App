package com.myFirstProject.myFirstProject.Repository;

import com.myFirstProject.myFirstProject.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {

    Cart findByUser_Id(Long userId);
}
