package com.dope.wb.repository;

import com.dope.wb.domain.product.Product;
import com.dope.wb.domain.product.attachment.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductImageRepository extends JpaRepository<ProductImage, Integer> {
    List<ProductImage> findAllByProduct(Product product);
}
