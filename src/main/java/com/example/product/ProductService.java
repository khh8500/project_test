package com.example.product;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    //상품명 실시간 중복체크(업데이트)
    public Product findByNameUpdate(String name, Integer id) {
        Product product = productRepository.findByNameUpdate(name, id);
        return product;
    }

    //상품명 실시간 중복체크
    public Product findByName(String name) {
        Product product = productRepository.findByName(name);
        return product;
    }

    @Transactional
    public void deleteById(Integer id){
        productRepository.deleteById(id);
    }

    @Transactional
    public void updateById(Integer id, ProductRequest.UpdateDTO reqDTO){
       productRepository.updateById(id, reqDTO);
    }

    @Transactional
    public void save(ProductRequest.SaveDTO reqDTO){
        productRepository.save(reqDTO);
    }

    public ProductResponse.DetailDTO findById(Integer id){
        return productRepository.findById(id);
    }

    public List<ProductResponse.MainDTO> findAll(){
        return productRepository.findAll();
    }
}
