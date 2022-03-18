package by.marshallbaby.simplewebapp.config;

import by.marshallbaby.simplewebapp.dao.EmployeeDao;
import by.marshallbaby.simplewebapp.dto.Employee;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
public class AppConfiguration {
    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("org.postgresql.Driver");
        ds.setUrl("jdbc:postgresql://192.168.1.120:5432/employeedb");
        ds.setUsername("postgres");
        ds.setPassword("32049");

        return ds;
    }
}
