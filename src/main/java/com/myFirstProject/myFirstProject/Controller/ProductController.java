package com.myFirstProject.myFirstProject.Controller;

import com.myFirstProject.myFirstProject.Service.ProductService;
import com.myFirstProject.myFirstProject.entity.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductService productService;


    

    @PostMapping("/add")
    public  String addProduct(@RequestBody Products p){

        System.out.println(p.toString());
        return "Product inserted Succesfully";
    }

    @GetMapping("/product/{id}")
    public Optional<Products> getProductById(@PathVariable int id){
        return productService.getPrductById(id);
    }

    @GetMapping("/products")
        public List<Products> getAll(){
        return productService.getAllProducts();
        }

        @GetMapping("/search")
        public List<Products> search(@RequestParam("q") String query) {

            return productService.getSearchProduct(query);
        }

          @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable int id){
         productService.deleteProduct(id);
              System.out.println(" #33333333333333 Delete product  #####################");
        return "Product delete successfully";
          }
}
