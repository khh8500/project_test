package com.example.product;

import lombok.Data;

public class ProductRequest {

    @Data
    public static class UpdateDTO{
        private String name;
        private Integer price;
        private Integer qty;
        private Integer id;

    }

    @Data
    public static class SaveDTO{
        private String name;
        private Integer price;
        private Integer qty;

    }
}
