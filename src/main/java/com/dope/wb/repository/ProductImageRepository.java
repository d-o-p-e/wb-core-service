package com.dope.wb.repository;

import com.dope.wb.domain.product.attachment.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepository extends JpaRepository<ProductImage, Integer> {
}
