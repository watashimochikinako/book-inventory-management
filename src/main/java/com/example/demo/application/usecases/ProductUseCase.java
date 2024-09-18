package com.example.demo.application.usecases;

import java.util.List;

import com.example.demo.domain.entities.Product;

/**
 * 商品に関するユースケースを定義するインターフェースです。
 */
public interface ProductUseCase {

    /**
     * すべての商品を取得します。
     *
     * @return 商品のリスト
     */
    List<Product> getAllProducts();
}
