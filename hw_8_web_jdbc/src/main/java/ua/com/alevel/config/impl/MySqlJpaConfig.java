package ua.com.alevel.config.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.config.DataSourceProperties;
import ua.com.alevel.config.JpaConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Service
public class MySqlJpaConfig implements JpaConfig {

    private final DataSourceProperties dataSourceProperties;
    private Statement statement;
    private Connection connection;

    public MySqlJpaConfig(DataSourceProperties dataSourceProperties) {
        this.dataSourceProperties = dataSourceProperties;
    }

    @Override
    public void connect() {
        try {
            Class.forName(dataSourceProperties.getDriverClassName());
            connection = DriverManager.getConnection(
                    dataSourceProperties.getUrl(),
                    dataSourceProperties.getUsername(),
                    dataSourceProperties.getPassword()
            );
            statement = connection.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public Statement getStatement() {
        return statement;
    }
}
