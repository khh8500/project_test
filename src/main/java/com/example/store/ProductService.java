package com.example.store;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public void deleteById(Integer id){
        productRepository.deleteById(id);
    }

    public void updateById(ProductRequest.UpdateDTO reqDTO){
        productRepository.updateById(reqDTO);
    }

    public void save(ProductRequest.SaveDTO reqDTO){
        productRepository.save(reqDTO);
    }

    public Product findById(Integer id){
        return productRepository.findById(id);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
