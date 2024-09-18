package com.example.demo.infrastructure.persistence;

import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.entities.Product;
import com.example.demo.domain.repositories.ProductRepository;

/**
 * JDBCを使用してデータベースから商品情報を永続化するリポジトリクラスです。
 */
@Repository
public class JdbcProductRepository implements ProductRepository{

    private final NamedParameterJdbcTemplate template;

    private static final RowMapper<Product> PRODUCT_ROW_MAPPER = (rs, i) -> {
        Product product = new Product();
		product.setId(rs.getInt("id"));
		product.setName(rs.getString("name"));
		product.setPriceId(rs.getString("priceId"));
        product.setPrice(rs.getLong("price"));
		return product;
    };

    /**
     * JdbcProductRepositoryのコンストラクタ。
     * 
     * @param template NamedParameterJdbcTemplate オブジェクト
     */
    public JdbcProductRepository(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    /**
     * データベースの "products" テーブルからすべての商品を取得し、リストとして返します。
     * 
     * @return 商品のリスト
     */
    @Override
    public List<Product> getAllProducts() {
        String sql = "SELECT * FROM products";
        List<Product> productList = template.query(sql, PRODUCT_ROW_MAPPER);
        return productList; 
    }
}
