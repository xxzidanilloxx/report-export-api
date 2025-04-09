package br.com.xxzidanilloxx.reportexportapi.entity;

import br.com.xxzidanilloxx.reportexportapi.dto.ProductRequestDTO;
import br.com.xxzidanilloxx.reportexportapi.enumeration.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "brand")
    private String brand;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private Category category;

    @Column(name = "stock_quantity")
    private Integer stockQuantity;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "status")
    private Boolean isActive;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Product(String name, String brand, String description, Category category, Integer stockQuantity, BigDecimal price) {
        this.name = name;
        this.brand = brand;
        this.description = description;
        this.category = category;
        this.stockQuantity = stockQuantity;
        this.price = price;
    }

    public static Product toEntity(ProductRequestDTO data){
        return new Product(
                data.name(),
                data.brand(),
                data.description(),
                data.category(),
                data.stockQuantity(),
                data.price());
    }

    @PrePersist
    protected void onCreate() {
        this.isActive = true;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
