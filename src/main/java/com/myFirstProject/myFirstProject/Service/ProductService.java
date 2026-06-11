package com.myFirstProject.myFirstProject.Service;

import com.myFirstProject.myFirstProject.DTO.ProductRequestDto;
import com.myFirstProject.myFirstProject.Repository.ProductRepository;
import com.myFirstProject.myFirstProject.entity.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public  void insertProduct(ProductRequestDto pr){
        Products p = new Products();
        p.setProduct_name(pr.getName());
        p.setDiscount(pr.getDiscount());
        p.setCategory(pr.getCategory());
        p.setProduct_price(pr.getPrice().intValue());
        p.setSlug(pr.getSlug());
        p.setImgLink(pr.getImg());
        p.setProduct_desc(pr.getDesc());

        productRepository.save(p);

    }

    public Optional<Products> getPrductById(int id){
     Optional<Products> product = productRepository.findById(id);

     if(product.isPresent()){
         return product;
     }else {
         return null;
     }
    }

    public List<Products> getAllProducts(){

     return   productRepository.findAll();
    }

    public Products getProductById(int Id){
        return productRepository.findById(Id).orElseThrow(()-> new RuntimeException(" Product Not Found"));
    }

public void deleteProduct(int Id){
        Products product = productRepository.findById(Id).orElseThrow(()-> new RuntimeException("Product Not Found"));
        productRepository.delete(product);
}

    public List<Products> getSearchProduct(String q) {

        return productRepository.searchByProductName(q);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Products> updateProduct(
            @RequestBody Products reqProduct,
            @PathVariable int id) {

        Products product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product Not Found"));

        product.setProduct_name(reqProduct.getProduct_name());
        product.setProduct_desc(reqProduct.getProduct_desc());
        product.setProduct_price(reqProduct.getProduct_price());
        product.setImgLink(reqProduct.getImgLink());
        product.setCategory(reqProduct.getCategory());
        product.setDiscount(reqProduct.getDiscount());

        Products updatedProduct = productRepository.save(product);

        return ResponseEntity.ok(updatedProduct);
    }


}
