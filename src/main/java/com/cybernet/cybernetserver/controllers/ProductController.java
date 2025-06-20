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
        return ResponseEntity.ok(productService.getAllProducts());
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @GetMapping("/full/{id}")
    public ResponseEntity<FullProductDTO> getFullById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getFullProductById(id));
    }


    @GetMapping("/category/{catId}")
    public ResponseEntity<List<ProductDTO>> getByCategory(@PathVariable Long catId) {
        return ResponseEntity.ok(productService.getByCategoryId(catId));
    }


    @PostMapping
    public ResponseEntity<ProductDTO> create(@RequestBody ProductDTO dto) {
        return ResponseEntity.ok(productDTOConverter.mapProductToProductDTO(productService.createProduct(dto)));
    }

    @PutMapping
    public ResponseEntity<ProductDTO> update(@RequestBody ProductDTO dto) {
        return ResponseEntity.ok(productDTOConverter.mapProductToProductDTO(productService.updateProduct(dto)));
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