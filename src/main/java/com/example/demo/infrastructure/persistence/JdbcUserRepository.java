package com.example.demo.infrastructure.persistence;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
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
     * @return メールアドレスに対応するユーザーエンティティ、存在しない場合は null
     */
    @Override
    public User findByEmail(String email) {
        SqlParameterSource param = new MapSqlParameterSource().addValue("email", email);
        String sql = "SELECT * FROM users WHERE email = :email";

        try {
            // SQLクエリを実行し、結果をユーザーエンティティにマッピングして返す
            return template.queryForObject(sql, param, USER_ROW_MAPPER);
        } catch (EmptyResultDataAccessException e) {
            // 結果が見つからない場合、null を返す
            return null;
        } catch (DataAccessException e) {
            // その他のデータアクセス例外処理（ログ出力など）
            throw e;
        }
    }

    /**
     * 指定されたメールアドレスを持つユーザーがデータベースに存在するかどうかを確認します。
     *
     * @param email ユーザーのメールアドレス
     * @return ユーザーが存在する場合は true、存在しない場合は false
     */
    @Override
    public boolean existsByEmail(String email) {
        SqlParameterSource param = new MapSqlParameterSource().addValue("email", email);
        String sql = "SELECT COUNT(*) FROM users WHERE email = :email";

        int count = template.queryForObject(sql, param, Integer.class);
        return count > 0;
    }

    /**
     * 新しいユーザーをデータベースに挿入します。
     *
     * @param user 挿入するユーザー情報
     * @return 挿入が成功した場合は true、失敗した場合は false
     */
    @Override
    public boolean insert(User user) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(user);
        String sql = "INSERT INTO users (name, email, password) VALUES (:name, :email, :password)";

        // ユーザー情報を挿入し、成功したかどうかを返す
        int rowsAffected = template.update(sql, param);
        return rowsAffected > 0;
    }

    /**
     * ユーザー情報をデータベースにアップデートします。
     * メールアドレスが既に存在する場合は、ユーザー情報を更新します。
     *
     * @param user 更新するユーザー情報
     * @return アップデートが成功した場合は true、失敗した場合は false
     */
    @Override
    public boolean update(User user) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(user);
        String sql = "UPDATE users SET name = :name, password = :password "
            + "WHERE email = :email";
            
        // ユーザー情報を挿入または更新し、成功したかどうかを返す
        int rowsAffected = template.update(sql, param);
        return rowsAffected > 0;
    }
}