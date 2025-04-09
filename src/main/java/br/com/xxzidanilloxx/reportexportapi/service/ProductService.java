package br.com.xxzidanilloxx.reportexportapi.service;

import br.com.xxzidanilloxx.reportexportapi.dto.ProductRequestDTO;
import br.com.xxzidanilloxx.reportexportapi.dto.ProductResponseDTO;
import br.com.xxzidanilloxx.reportexportapi.entity.Product;
import br.com.xxzidanilloxx.reportexportapi.mapper.ProductMapper;
import br.com.xxzidanilloxx.reportexportapi.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Transactional
    public ProductResponseDTO saveProduct(ProductRequestDTO data) {
        Product product = Product.toEntity(data);
        Product result = productRepository.save(product);
        return productMapper.toDto(result);
    }

    @Transactional(readOnly = true)
    public List<ProductResponseDTO> findAllProducts() {
        List<Product> result = productRepository.findAll();
        return result.stream().map(productMapper::toDto).toList();
    }

    @Transactional
    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO data) {
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Product not found"));
        product.setName(data.name());
        product.setBrand(data.brand());
        product.setDescription(data.description());
        product.setCategory(data.category());
        product.setStockQuantity(data.stockQuantity());
        product.setPrice(data.price());
        Product result = productRepository.save(product);
        return productMapper.toDto(result);
    }
}
