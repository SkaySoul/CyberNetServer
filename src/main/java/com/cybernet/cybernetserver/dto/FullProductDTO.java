package com.cybernet.cybernetserver.dto;
import lombok.Data;

import java.util.List;
@Data
public class FullProductDTO {
    private String name;
    private Long id;
    private int amount;
    private double price;
    private String productCode;
    private Long manufacturerId;
    private Long categoryId;
    private Long subcategoryId;
    private List<ProductImageDTO> images;
    ProductAttributesDTO productAttributes;
}
