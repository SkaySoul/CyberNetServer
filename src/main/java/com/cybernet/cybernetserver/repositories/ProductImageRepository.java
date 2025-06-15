package com.cybernet.cybernetserver.repositories;

import com.cybernet.cybernetserver.entities.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage,Long> {
    ProductImage findByProductIdAndPreviewImageIsTrue(Long productId);
    List<ProductImage> findByProductId(Long productId);
}
