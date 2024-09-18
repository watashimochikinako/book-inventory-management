package com.example.demo.domain.repositories;

import java.util.List;

import com.example.demo.domain.entities.Product;

/**
 * 商品データの永続化を管理するためのリポジトリインターフェースです。
 */
public interface ProductRepository {

    /**
     * データベースからすべての商品を取得します。
     * 
     * @return 商品のリスト
     */
    List<Product> getAllProducts();

}