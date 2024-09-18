package com.example.demo.application.usecases;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.domain.entities.Product;
import com.example.demo.domain.repositories.ProductRepository;

/**
 * 商品に関するユースケースの実装クラスです。
 */
@Service
public class ProductUseCaseImpl implements ProductUseCase{

    private final ProductRepository productRepository;

    /**
     * ProductUseCaseImplのコンストラクタです。
     *
     * @param productRepository 商品データの永続化を管理するリポジトリ
     */
    public ProductUseCaseImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * データベースからすべての商品を取得します。
     *
     * @return 商品のリスト
     */
    @Override
    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }
}
