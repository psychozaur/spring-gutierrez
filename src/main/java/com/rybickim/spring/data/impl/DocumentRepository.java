package com.rybickim.spring.data.impl;

import com.rybickim.spring.data.DocumentDAO;
import com.rybickim.spring.jdbc.DocumentRowMapper;
import com.rybickim.spring.model.Document;
import com.rybickim.spring.model.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Repository("documentDAO")
public class DocumentRepository implements DocumentDAO {

    @Autowired
    private String query;

    @Autowired
    private DataSource dataSource;

    @Override
    public List<Document> getAll() {
        return new JdbcTemplate(this.dataSource).query(query, new DocumentRowMapper());
    }
}
