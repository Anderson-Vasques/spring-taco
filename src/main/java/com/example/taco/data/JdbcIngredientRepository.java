package com.example.taco.data;

import com.example.taco.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JdbcIngredientRepository implements IngredientRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcIngredientRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private Ingredient mapRowToIngredient(ResultSet rs, int rowNum) throws SQLException {
        return new Ingredient (
                rs.getString("id"),
                rs.getString("name"),
                Ingredient.Type.valueOf(rs.getString("type"))
        );
    }

    @Override
    public Ingredient findById(String id) {
        return jdbcTemplate.queryForObject(
                "SELECT id, name, type from Ingredient WHERE id=?",
                this::mapRowToIngredient, id);
    }

    @Override
    public Iterable<Ingredient> findAll() {
        return jdbcTemplate.query("SELECT id, name, type FROM Ingredient",
                this::mapRowToIngredient);
    }

    public Ingredient save(Ingredient ingredient) {
        jdbcTemplate.update("INSERT into Ingredient (id, name, type) values (? ? ?)",
                ingredient.getId(),
                ingredient.getName(),
                ingredient.getType());
        return ingredient;
    }
}
