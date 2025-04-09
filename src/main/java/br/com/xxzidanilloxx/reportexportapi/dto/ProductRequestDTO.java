package br.com.xxzidanilloxx.reportexportapi.dto;

import br.com.xxzidanilloxx.reportexportapi.enumeration.Category;

import java.math.BigDecimal;

public record ProductRequestDTO(
        String name,
        String brand,
        String description,
        Category category,
        Integer stockQuantity,
        BigDecimal price) {
}
