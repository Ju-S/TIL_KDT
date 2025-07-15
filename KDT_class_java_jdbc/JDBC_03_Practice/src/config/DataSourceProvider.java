package config;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;

public final class DataSourceProvider {
    // db연결
    private static BasicDataSource bds;

    private static DataSourceProvider instance;

    public synchronized static DataSourceProvider getInstance() {
        if(instance == null) {
            instance = new DataSourceProvider();
        }
        return instance;
    }

    private DataSourceProvider() {
        bds = new BasicDataSource();
        bds.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
        bds.setUsername("kedu");
        bds.setPassword("kedu");
        bds.setInitialSize(10);
    }

    public Connection getConnection() throws Exception {
        return bds.getConnection();
    }
}
