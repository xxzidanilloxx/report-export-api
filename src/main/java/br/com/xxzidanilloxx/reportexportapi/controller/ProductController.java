package br.com.xxzidanilloxx.reportexportapi.controller;

import br.com.xxzidanilloxx.reportexportapi.dto.ProductRequestDTO;
import br.com.xxzidanilloxx.reportexportapi.dto.ProductResponseDTO;
import br.com.xxzidanilloxx.reportexportapi.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponseDTO> saveProduct(@RequestBody ProductRequestDTO data) {
        ProductResponseDTO result = productService.saveProduct(data);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> findAllProducts() {
        List<ProductResponseDTO> result = productService.findAllProducts();
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable Long id,
                                                            @RequestBody ProductRequestDTO data) {
        ProductResponseDTO result = productService.updateProduct(id, data);
        return ResponseEntity.ok(result);
    }
}
