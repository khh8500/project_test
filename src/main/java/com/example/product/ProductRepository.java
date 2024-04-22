package com.example.product;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ProductRepository {

    private final EntityManager em;

    //상품명 중복체크
    public boolean isProductNameExists(String name) {
        Query query = em.createNativeQuery("SELECT 1 FROM product_tb WHERE name = ?");
        query.setParameter(1, name);
        try {
            query.getSingleResult();
            return true; // 결과가 존재하면 true 반환
        } catch (NoResultException ex) {
            return false; // 결과가 없으면 false 반환
        }
    }

    public void deleteById(Integer id){
        Query query = em.createNativeQuery("delete from product_tb where id=?", Product.class);
        query.setParameter(1, id);

        query.executeUpdate();
    }

    public void updateById(Integer id, ProductRequest.UpdateDTO reqDTO){
        Query query = em.createNativeQuery("update product_tb set name=?, price=?, qty=? where id=?", Product.class);
        query.setParameter(1, reqDTO.getName());
        query.setParameter(2, reqDTO.getPrice());
        query.setParameter(3, reqDTO.getQty());
        query.setParameter(4, id);
        query.executeUpdate();
    }

    public void save(ProductRequest.SaveDTO reqDTO){
        Query query = em.createNativeQuery("insert into product_tb (name, price, qty, created_at) values (?,?,?, now())");
        query.setParameter(1, reqDTO.getName());
        query.setParameter(2, reqDTO.getPrice());
        query.setParameter(3, reqDTO.getQty());
        query.executeUpdate();
    }

    public ProductResponse.DetailDTO findById(Integer id){
        Query query = em.createNativeQuery("select * from product_tb where id=?", Product.class);
        query.setParameter(1, id);
        Product product = (Product) query.getSingleResult();
        return new ProductResponse.DetailDTO(product);
    }

    public List<ProductResponse.MainDTO> findAll(){
        Query query = em.createNativeQuery("select * from product_tb order by id desc", Product.class);

        return query.getResultList();
    }
}
