package br.com.xxzidanilloxx.reportexportapi.repository;

import br.com.xxzidanilloxx.reportexportapi.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
