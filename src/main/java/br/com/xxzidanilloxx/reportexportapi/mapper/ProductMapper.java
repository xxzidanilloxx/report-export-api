package br.com.xxzidanilloxx.reportexportapi.mapper;

import br.com.xxzidanilloxx.reportexportapi.dto.ProductResponseDTO;
import br.com.xxzidanilloxx.reportexportapi.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductResponseDTO toDto(Product product);
}
