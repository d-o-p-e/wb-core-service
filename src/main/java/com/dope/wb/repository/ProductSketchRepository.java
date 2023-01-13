package com.dope.wb.repository;

import com.dope.wb.domain.board.product.Product;
import com.dope.wb.domain.board.attachment.ProductSketch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductSketchRepository extends JpaRepository<ProductSketch, Long> {
    ProductSketch findByProduct(Product product);
}
