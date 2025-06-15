// src/main/java/com/cybernet/cybernetserver/controllers/ImageController.java
package com.cybernet.cybernetserver.controllers;

import com.cybernet.cybernetserver.entities.ProductImage;
import com.cybernet.cybernetserver.repositories.ProductImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/images")
@RequiredArgsConstructor
public class ImageController {
    private final ProductImageRepository imageRepository;

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        ProductImage img = imageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Image not found: " + id));

        Optional<MediaType> optType = MediaTypeFactory.getMediaType(img.getContentType());
        MediaType mime = optType.orElse(MediaType.APPLICATION_OCTET_STREAM);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "inline; filename=\"" + img.getContentType() + "\"")
                .contentType(mime)
                .body(img.getData());
    }
}