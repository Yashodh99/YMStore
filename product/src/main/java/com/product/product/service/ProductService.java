package com.product.product.service;
import com.product.product.dao.ProductDao;
import com.product.product.dto.ProductDTO;
import com.product.product.model.Product;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private ModelMapper modelMapper;


    public List<ProductDTO> getAllProducts(){
        List<Product>productList = productDao.findAll();
        return modelMapper.map(productList, new TypeToken<List<ProductDTO>>(){}.getType());
    }

    public ProductDTO addProduct(ProductDTO productDTO){
        productDao.save(modelMapper.map(productDTO,Product.class));
        return productDTO;
    }

    public ProductDTO updateProduct(ProductDTO productDTO){
        productDao.save(modelMapper.map(productDTO,Product.class));
        return productDTO;
    }

    public String deleteProduct(Integer productId){
        productDao.deleteById(productId);
        return "Product deleted successfully";
    }

    public ProductDTO getProductById(Integer productId){
        Product product = productDao.getProductById(productId);
        return modelMapper.map(product,ProductDTO.class);
    }

}
