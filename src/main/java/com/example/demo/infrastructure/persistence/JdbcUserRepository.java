package com.example.demo.infrastructure.persistence;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.entities.User;
import com.example.demo.domain.repositories.UserRepository;

/**
 * JDBCを使用してデータベースからユーザー情報を永続化するリポジトリクラスです。
 */
@Repository
public class JdbcUserRepository implements UserRepository {

    private final NamedParameterJdbcTemplate template;

    /**
     * JdbcUserRepositoryのコンストラクターです。
     *
     * @param template NamedParameterJdbcTemplateのインスタンス
     */
    public JdbcUserRepository(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    /**
     * ユーザーエンティティのRowMapperです。
     * ResultSetからユーザーエンティティにマッピングします。
     */
    private static final RowMapper<User> USER_ROW_MAPPER = (rs, i) -> {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setName(rs.getString("name"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        return user;
    };

    /**
     * 指定されたメールアドレスを持つユーザーをデータベースから検索します。
     *
     * @param email 検索するユーザーのメールアドレス
     * @return メールアドレスに対応するユーザーエンティティ、存在しない場合はnull
     */
    @Override
    public User findByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email=:email";

        SqlParameterSource param = new MapSqlParameterSource().addValue("email", email);

        try {
            // SQLクエリを実行し、結果をユーザーエンティティにマッピングして返す
            User user = template.queryForObject(sql, param, USER_ROW_MAPPER);
            return user;
        } catch (Exception e) {
            // 例外が発生した場合はnullを返す
            return null;
        }
    }
}
