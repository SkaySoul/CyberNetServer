package com.cybernet.cybernetserver.controllers;

import com.cybernet.cybernetserver.dto.FullProductDTO;
import com.cybernet.cybernetserver.dto.ProductDTO;
import com.cybernet.cybernetserver.dtoconverter.FullProductDTOConverter;
import com.cybernet.cybernetserver.dtoconverter.ProductDTOConverter;
import com.cybernet.cybernetserver.entities.Product;
import com.cybernet.cybernetserver.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductDTOConverter productDTOConverter;
    private final FullProductDTOConverter fullProductDTOConverter;


    @GetMapping()
    public ResponseEntity<List<ProductDTO>> getAll() {
        List<ProductDTO> dtoList = productService.getAllProducts();
        return ResponseEntity.ok(dtoList);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getById(@PathVariable Long id) {
        ProductDTO dto = productService.getProductById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/full/{id}")
    public ResponseEntity<FullProductDTO> getFullById(@PathVariable Long id) {
        FullProductDTO fullDto = productService.getFullProductById(id);
        return ResponseEntity.ok(fullDto);
    }


    @GetMapping("/category/{catId}")
    public ResponseEntity<List<ProductDTO>> getByCategory(@PathVariable Long catId) {
        List<ProductDTO> dtos = productService.getByCategoryId(catId);
        return ResponseEntity.ok(dtos);
    }


    @PostMapping
    public ResponseEntity<ProductDTO> create(@RequestBody ProductDTO dto) {
        Product created = productService.createProduct(dto);
        ProductDTO result = productDTOConverter.mapProductToProductDTO(created);
        return ResponseEntity.ok(result);
    }

    @PutMapping
    public ResponseEntity<ProductDTO> update(@RequestBody ProductDTO dto) {
        Product updated = productService.updateProduct(dto);
        ProductDTO result = productDTOConverter.mapProductToProductDTO(updated);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/subcategory/{subId}")
    public ResponseEntity<List<ProductDTO>> getBySubcategory(@PathVariable Long subId) {
        List<Product> list = productService.getBySubcategoryId(subId);
        List<ProductDTO> dtos = list.stream()
                .map(productDTOConverter::mapProductToProductDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
}