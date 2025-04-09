package br.com.xxzidanilloxx.reportexportapi.dto;

import br.com.xxzidanilloxx.reportexportapi.enumeration.Category;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductResponseDTO(
        Long id,
        String name,
        String brand,
        String description,
        Integer stockQuantity,
        Category category,
        BigDecimal price,
        Boolean isActive,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {
}
