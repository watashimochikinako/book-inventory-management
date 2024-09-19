package com.example.demo.application.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.application.usecases.ProductUseCase;
import com.example.demo.domain.entities.Product;

/**
 * 商品に関するサービスクラスです。
 * このクラスはコントローラ層から呼び出され、ユースケースを利用してビジネスロジックを実行します。
 */
@Service
public class ProductService {

    private final ProductUseCase productUseCase;

    /**
     * コンストラクタ
     *
     * @param productUseCase 商品ユースケース
     */
    public ProductService(ProductUseCase productUseCase) {
        this.productUseCase = productUseCase;
    }

    /**
     * すべての商品を取得します。
     *
     * @return 商品のリスト
     */
    public List<Product> getAllProducts() {
        return productUseCase.getAllProducts();
    }
}
