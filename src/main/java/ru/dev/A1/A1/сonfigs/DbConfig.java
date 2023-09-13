package ru.dev.A1.A1.сonfigs;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DbConfig {
    @Bean
    public DataSource getDataSource(){
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.postgresql.Driver");
        dataSourceBuilder.url("jdbc:postgresql://localhost:5432/KebabCloud?useSSL=false&serverTimezone=UTC");
        dataSourceBuilder.username("postgres");
        dataSourceBuilder.password("Dune1488");
        return dataSourceBuilder.build();
    }

}
