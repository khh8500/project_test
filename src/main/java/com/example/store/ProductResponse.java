package com.example.store;

import lombok.Data;

public class ProductResponse {

    @Data
    public static class DetailDTO {
        private String name;
        private Integer price;
        private Integer qty;
        private Integer id;

        public DetailDTO(Product product){
            this.name = product.getName();
            this.price = product.getPrice();
            this.qty = product.getQty();
            this.id = product.getId();
        }
    }

    @Data
    public static class UpdateDTO {
        private String name;
        private Integer price;
        private Integer qty;
        private Integer id;

        public UpdateDTO(Product product){
            this.name = product.getName();
            this.price = product.getPrice();
            this.qty = product.getQty();
            this.id = product.getId();
        }
    }

    @Data
    public static class MainDTO {
        private String name;
        private Integer price;
        private Integer qty;
        private Integer id;

        public MainDTO(Product product) {
            this.name = product.getName();
            this.price = product.getPrice();
            this.qty = product.getQty();
            this.id = product.getId();
        }
    }

}