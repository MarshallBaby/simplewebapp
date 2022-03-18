package by.marshallbaby.simplewebapp.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class AppConfiguration {
    @Bean
    public DataSource dataSource(){

        // DataSource Bean fix

        return DataSourceBuilder.create()
                .username("postgres")
                .password("32049")
                .driverClassName("org.postgresql.Driver")
                .url("jdbc:postgresql://127.0.0.1:5432/employeedb")
                .build();
    }
}
