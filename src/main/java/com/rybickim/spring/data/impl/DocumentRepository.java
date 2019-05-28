package com.rybickim.spring.data.impl;

import com.rybickim.spring.data.DocumentDAO;
import com.rybickim.spring.jdbc.DocumentRowMapper;
import com.rybickim.spring.model.Document;
import com.rybickim.spring.model.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.EmptyResultDataAccessException;
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
import java.util.Date;
import java.util.List;
import java.util.Scanner;

@Repository("documentDAO")
public class DocumentRepository implements DocumentDAO {

    @Autowired
    private String query;
    @Autowired
    private String insert;
    @Autowired
    private String find;
    @Autowired
    private String update;

    @Autowired
    private DataSource dataSource;

    @Override
    public List<Document> getAll() {
        return new JdbcTemplate(this.dataSource).query(query, new DocumentRowMapper());
    }

    @Override
    public Document findById(String id) {
        Document updateDocument = null;
        JdbcTemplate template = new JdbcTemplate(dataSource);

        try {
            updateDocument = template.queryForObject(find,
                    new Object[] { id },
                    new DocumentRowMapper());
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }
        return updateDocument;
    }

    public void save(Document document) {
        try {
            JdbcTemplate template = new JdbcTemplate(dataSource);
            if (null == findById(document.getDocumentID()))
                template.update(
                        insert,
                        new Object[] { document.getDocumentID(),
                                document.getName(), document.getLocation(),
                                document.getDescription(),
                                document.getType().getTypeId(),
                                document.getCreated(), document.getModified() });
            else
                template.update(
                        update,
                        new Object[] { document.getName(),
                                document.getLocation(),
                                document.getDescription(),
                                document.getType().getTypeId(), new Date(),
                                document.getDocumentID() });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
