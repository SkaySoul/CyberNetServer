package com.cybernet.cybernetserver.services.impl;

import com.cybernet.cybernetserver.dto.FullProductDTO;
import com.cybernet.cybernetserver.dto.ProductDTO;
import com.cybernet.cybernetserver.dto.ProductImageDTO;
import com.cybernet.cybernetserver.dtoconverter.*;
import com.cybernet.cybernetserver.entities.Product;
import com.cybernet.cybernetserver.entities.ProductImage;
import com.cybernet.cybernetserver.repositories.ProductAttributeRepository;
import com.cybernet.cybernetserver.repositories.ProductImageRepository;
import com.cybernet.cybernetserver.repositories.ProductRepository;
import com.cybernet.cybernetserver.services.CategoryService;
import com.cybernet.cybernetserver.services.ManufacturerService;
import com.cybernet.cybernetserver.services.ProductService;
import com.cybernet.cybernetserver.services.SubcategoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductAttributeRepository productAttributeRepository;
    private final CategoryService categoryService;
    private final SubcategoryService subcategoryService;
    private final ManufacturerService manufacturerService;
    private ProductDTOConverter productDTOConverter;
    private FullProductDTOConverter fullProductDTOConverter;
    private ProductImageRepository imageRepository;
    private ProductImageDTOConverter imageDTOConverter;
    private ProductAttributesDTOConverter productAttributesDTOConverter;
    private final ManufacturerDTOConverter manufacturerDTOConverter;
    public Product createProduct(FullProductDTO dto){
        return productRepository.save(Product.builder()
                .productCode(dto.getProductCode())
                .amount(dto.getAmount())
                .price(dto.getPrice())
                .manufacturer(manufacturerDTOConverter.mapManufacturerDTOtoManufacturer(manufacturerService.getManufacturerById(dto.getManufacturerId())))
                .category(categoryService.getCategoryById(dto.getCategoryId()))
                .subcategory(subcategoryService.getSubcategoryById(dto.getSubcategoryId()))
                .build());
    }

    @Override
    public Product createProduct(ProductDTO dto) {
        return null;
    }

    public List<ProductDTO> getAllProducts() {
        List<Product> productList = productRepository.findAll();
        List<ProductDTO> productDTOList = new ArrayList<>(productList.size());

        for (Product product : productList) {
            ProductDTO dto = productDTOConverter.mapProductToProductDTO(product);
            ProductImage imageEntity = imageRepository
                    .findByProductIdAndPreviewImageIsTrue(product.getId());

            if (imageEntity != null) {
                ProductImageDTO imgDto = imageDTOConverter
                        .mapProductImageToProductImageDTO(imageEntity);
                imgDto.setUrl("/images/" + imgDto.getId());
                dto.setMainImage(imgDto);
            } else {
                dto.setMainImage(null);
            }

            productDTOList.add(dto);
        }

        return productDTOList;
    }

    public List<ProductDTO> getByCategoryId(Long id){
        List<Product> productList = productRepository.findByCategoryId(id);
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (Product product: productList){
            productDTOList.add(productDTOConverter.mapProductToProductDTO(product));
        }
        return productDTOList;
    }

    public Product updateProduct(ProductDTO product) {
        return productRepository.save(productDTOConverter.mapProductDTOtoProduct(product));
    }

    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }

    public ProductDTO getProductById(Long id){
        Product product = productRepository.findById(id).orElseThrow(()->
                new RuntimeException("Product not found - " + id));
        ProductDTO productDTO = productDTOConverter.mapProductToProductDTO(product);
        ProductImageDTO productImageDTO = imageDTOConverter.mapProductImageToProductImageDTO(imageRepository.findByProductIdAndPreviewImageIsTrue(id));
        productImageDTO.setUrl("/images/" + productImageDTO.getId());
        productDTO.setMainImage(productImageDTO);
        return productDTO;
    }



    @Override
    public FullProductDTO getFullProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found: " + id));
        FullProductDTO fullProductDTO = fullProductDTOConverter.mapProductToProductDTO(product);
        List<ProductImageDTO> productImageDTOList = new ArrayList<>();
        for (ProductImage productImage: imageRepository.findByProductId(id)){
            ProductImageDTO productImageDTO = imageDTOConverter.mapProductImageToProductImageDTO(productImage);
            productImageDTO.setUrl("/images/" + productImageDTO.getId());
            productImageDTOList.add(productImageDTO);
        }
        fullProductDTO.setImages(productImageDTOList);
        if(productAttributeRepository.findByProductId(id) != null){
            fullProductDTO.setProductAttributes(productAttributesDTOConverter.mapProductAttributesToProductAttributesDTO(productAttributeRepository.findByProductId(id)));
        }
        return fullProductDTO;
    }

    public List<Product> getBySubcategoryId(Long subcategoryId) {
        return productRepository.findBySubcategoryId(subcategoryId);
    }

}
