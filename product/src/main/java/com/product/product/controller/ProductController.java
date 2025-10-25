package com.product.product.controller;


import com.product.product.dao.ProductDao;
import com.product.product.dto.ProductDTO;
import com.product.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/product")
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping
    public List<ProductDTO> getProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{productId}")
    public ProductDTO getProductById(@PathVariable Integer productId) {
        return productService.getProductById(productId);
    }

    @PostMapping
    public ProductDTO saveProduct(@RequestBody ProductDTO productDTO) {
        return productService.addProduct(productDTO);
    }

    @PutMapping
    public ProductDTO updateProduct(@RequestBody ProductDTO productDTO) {
        return productService.updateProduct(productDTO);
    }

    @DeleteMapping("/{productId}")
    public String deleteProduct(@PathVariable Integer productId) {
        return productService.deleteProduct(productId);
    }
}
