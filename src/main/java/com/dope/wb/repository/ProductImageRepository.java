package com.dope.wb.repository;

import com.dope.wb.domain.board.product.Product;
import com.dope.wb.domain.board.attachment.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
    List<ProductImage> findAllByProduct(Product product);
}
