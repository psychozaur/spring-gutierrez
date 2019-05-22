package com.rybickim.spring.data.impl;

import com.rybickim.spring.data.DocumentDAO;
import com.rybickim.spring.model.Document;
import com.rybickim.spring.model.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;

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

public class DocumentRepository implements DocumentDAO {

    private static final Logger logger = LoggerFactory.getLogger(DocumentRepository.class);

    private String queryAll;
    private DataSource dataSource;
    private Resource schema;
    private Resource data;

    public void setQueryAll(String queryAll) {
        this.queryAll = queryAll;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void setSchema(Resource schema) {
        this.schema = schema;
    }

    public void setData(Resource data) {
        this.data = data;
    }

    public void initialize(){
        try {
            InputStream stream = schema.getInputStream();
            Scanner scanner = new Scanner(stream);
            StringBuilder sql = new StringBuilder();
            while(scanner.hasNext()) {
                sql.append(scanner.nextLine());
                sql.append("\n");
            }
            scanner.close();
            stream.close();
            Connection connection = null;
            Statement statement = null;
            try {
                connection = dataSource.getConnection();
                statement = connection.createStatement();
                statement.execute(sql.toString());
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            } finally {
                if (null != connection) {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }

            stream = data.getInputStream();
            scanner = new Scanner(stream);
            sql = new StringBuilder();
            while (scanner.hasNext()){
                sql.append(scanner.nextLine());
                sql.append("\n");
            }
            scanner.close();
            stream.close();
            connection = null;
            statement = null;
            try {
                connection = dataSource.getConnection();
                statement = connection.createStatement();
                statement.executeUpdate(sql.toString());
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            } finally {
                if (null != connection) {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Document> getAll() {
        if(logger.isDebugEnabled()) logger.debug("starting getAll() method from DocumentRepository");

        List<Document> result = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Document document = null;
        Type type = null;

        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryAll);
            while(resultSet.next()){
                document = new Document();
                document.setDocumentID(resultSet.getString("documentId"));
                document.setName(resultSet.getString("name"));
                document.setLocation(resultSet.getString("location"));
                document.setCreated(resultSet.getDate("created"));
                document.setModified(resultSet.getDate("modified"));
                document.setDescription("doc_desc");

                type = new Type();
                type.setTypeId(resultSet.getString("typeId"));
                type.setName(resultSet.getString("type_name"));
                type.setDesc(resultSet.getString("type_desc"));
                type.setExtension(resultSet.getString("extension"));

                result.add(document);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (null != connection) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        if(logger.isDebugEnabled()) logger.debug("ending getAll() method from DocumentRepository: " + result);

        return result;

    }
}
