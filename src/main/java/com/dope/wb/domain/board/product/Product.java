package com.dope.wb.domain.board.product;

import com.dope.wb.domain.board.Board;
import com.dope.wb.domain.user.User;
import com.dope.wb.specification.ProductCategory;
import javax.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Getter
@Entity
@Table(
        indexes = {
                @Index(name="idx_product_serial",columnList = "serial")
        }
)
public class Product extends Board {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="USER_ID")
    private User writer;

    private String serial;

    private ProductCategory productCategory;

    public Product() {

    }

    @Builder
    public Product(String serial, String content, ProductCategory productCategory) {
        this.serial = serial;
        this.content = content;
        this.productCategory = productCategory;
        this.view = 0L;
    }

}
