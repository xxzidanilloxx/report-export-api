package br.com.xxzidanilloxx.reportexportapi.dto;

import br.com.xxzidanilloxx.reportexportapi.enumeration.Category;
import com.fasterxml.jackson.annotation.JsonFormat;

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
        @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
        LocalDateTime createdAt,
        @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
        LocalDateTime updatedAt) {
}
