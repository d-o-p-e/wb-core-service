package com.dope.wb.repository;

import com.dope.wb.domain.board.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findBySerial(String serial);
}
