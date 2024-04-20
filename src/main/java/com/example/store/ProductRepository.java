package com.example.store;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ProductRepository {
    private final EntityManager em;

    @Transactional
    public void deleteById(Integer id){
        Query query = em.createNativeQuery("delete from product_tb where id=?", Product.class);
        query.setParameter(1, id);

        query.executeUpdate();
    }

    @Transactional
    public void updateById(ProductRequest.UpdateDTO reqDTO){
        String name = reqDTO.getName();
        Integer price = reqDTO.getPrice();
        Integer qty = reqDTO.getQty();
        Integer id = reqDTO.getId();

        Query query = em.createNativeQuery("update product_tb set name=?, price=?, qty=? where id=?", Product.class);
        query.setParameter(1, name);
        query.setParameter(2, price);
        query.setParameter(3, qty);
        query.setParameter(4, id);
        query.executeUpdate();
    }

    @Transactional
    public void save(ProductRequest.SaveDTO reqDTO){
        String name = reqDTO.getName();
        Integer price = reqDTO.getPrice();
        Integer qty = reqDTO.getQty();

        Query query = em.createNativeQuery("insert into product_tb (name, price, qty, created_at) values (?,?,?, now())");
        query.setParameter(1, name);
        query.setParameter(2, price);
        query.setParameter(3, qty);
        query.executeUpdate();
    }

    public Product findById(Integer id){
        Query query = em.createNativeQuery("select * from product_tb where id=?", Product.class);
        query.setParameter(1, id);
        return (Product) query.getSingleResult();
    }

    public List<Product> findAll(){
        Query query = em.createNativeQuery("select * from product_tb order by id desc", Product.class);
        return query.getResultList();
    }
}
