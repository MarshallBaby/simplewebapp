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
@EnableTransactionManagement
public class AppConfiguration {
    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("org.postgresql.Driver");
        ds.setUrl("jdbc:postgresql://127.0.0.1:5432/employeedb");
        ds.setUsername("postgres");
        ds.setPassword("hochu_v_godel_bezumno");

        return ds;
    }
}
