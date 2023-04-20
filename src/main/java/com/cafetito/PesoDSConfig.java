package com.cafetito;

import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author Anderson
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "pesoEntityManagerFactory",
        transactionManagerRef = "pesoTransactionManager",
        basePackages = {"com.cafetito.repository.peso"})
public class PesoDSConfig {

    @Autowired
    private Environment env;

    @Bean(name = "pesoDataSource")
    public DataSource pesoDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(env.getProperty("peso.datasource.url"));
        dataSource.setUsername(env.getProperty("peso.datasource.username"));
        dataSource.setPassword(env.getProperty("peso.datasource.password"));
        dataSource.setDriverClassName(env.getProperty("peso.datasource.driver-class-name"));

        return dataSource;
    }
    
    @Bean(name = "pesoEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean pesoEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(pesoDataSource());
        em.setPackagesToScan("com.cafetito.entity.peso");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        Map<String, Object> propierties = new HashMap<>();
        propierties.put("hibernate.hbm2ddl.auto", env.getProperty("peso.jap.hibernate.ddl-auto"));
        propierties.put("hibernate.show-sql", env.getProperty("peso.jap.show-sql"));
        propierties.put("hibernate.dialect", env.getProperty("peso.jpa.database-platform"));

        em.setJpaPropertyMap(propierties);

        return em;
    }
    
    @Bean(name = "pesoTransactionManager")
     public PlatformTransactionManager pesoTransactionManager(){
         JpaTransactionManager transactionManager = new JpaTransactionManager();
         transactionManager.setEntityManagerFactory(pesoEntityManagerFactory().getObject());
         
         return transactionManager;
     }
}
