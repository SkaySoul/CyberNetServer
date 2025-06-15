package com.cybernet.cybernetserver.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private String name;
    private Long id;
    private double price;
    private Long manufacturerId;
    private Long categoryId;
    private Long subcategoryId;
    private ProductImageDTO mainImage;

}
