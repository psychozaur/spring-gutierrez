package com.rybickim.spring.jdbc;

import com.rybickim.spring.data.DocumentDAO;
import com.rybickim.spring.model.Document;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class DocumentJdbcTemplateRepository implements DocumentDAO {

    private JdbcTemplate jdbcTemplate;
    private DataSource dataSource;
    private String query;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(this.dataSource);
    }

    public void setQuery(String query) {
        this.query = query;
    }

    @Override
    public List<Document> getAll() {
        return jdbcTemplate.query(query, new DocumentRowMapper());
    }

    @Override
    public Document findById(String id) {
        return null;
    }
}
