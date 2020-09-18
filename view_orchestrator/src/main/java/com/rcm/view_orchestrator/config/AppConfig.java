package com.rcm.view_orchestrator.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.rcm.view_orchestrator.DAO.FormDAO;
import com.rcm.view_orchestrator.DAO.FormDAOimpl;



@Configuration
@ComponentScan(basePackages="com.rcm.view_orchestrator")
public class AppConfig {
	@Bean()
    public DataSource getDataSource1() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/rcm?serverTimezone=UTC&useLegacyDatetimeCode=false");
        dataSource.setUsername("pmauser");
        dataSource.setPassword("alvin147");
         
        return dataSource;
    }
	@Bean()
    public FormDAO getFormDAO() {
    	return new FormDAOimpl(getDataSource1());
    }
}
