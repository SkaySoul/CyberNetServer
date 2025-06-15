package com.cybernet.cybernetserver.repositories;

import com.cybernet.cybernetserver.entities.ProductAttributes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductAttributeRepository extends JpaRepository<ProductAttributes, Long> {
    ProductAttributes findByProductId(Long productId);
}
