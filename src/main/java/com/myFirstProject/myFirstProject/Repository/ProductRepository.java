package com.myFirstProject.myFirstProject.Repository;

import com.myFirstProject.myFirstProject.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Products,Integer> {
    @Query("""
        SELECT p 
        FROM Products p 
        WHERE LOWER(p.product_name) LIKE LOWER(CONCAT('%', :q, '%'))
    """)
    List<Products> searchByProductName(@Param("q") String q);
}
