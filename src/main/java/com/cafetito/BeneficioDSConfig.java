
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
@EnableJpaRepositories(entityManagerFactoryRef = "beneficioEntityManagerFactory", 
        transactionManagerRef = "beneficioTransactionManager", 
        basePackages = { "com.cafetito.repository" })
public class BeneficioDSConfig {
    
    @Autowired
    private Environment env;
    
    @Bean(name = "beneficioDataSource")
    public DataSource beneficioDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(env.getProperty("beneficio.datasource.url"));
        dataSource.setUsername(env.getProperty("beneficio.datasource.username"));
        dataSource.setPassword(env.getProperty("beneficio.datasource.password"));
        dataSource.setDriverClassName(env.getProperty("beneficio.datasource.driver-class-name"));
        
        return dataSource;
    }
    
    @Bean(name = "beneficioEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(beneficioDataSource());
        em.setPackagesToScan("com.cafetito.entity");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        Map<String, Object> propierties = new HashMap<>();
        propierties.put("hibernate.hbm2ddl.auto", env.getProperty("postgre.jap.hibernate.ddl-auto"));
        propierties.put("hibernate.show-sql", env.getProperty("postgre.jap.show-sql"));
        propierties.put("hibernate.dialect", env.getProperty("postgre.jpa.database-platform"));

        em.setJpaPropertyMap(propierties);

        return em;
    }
    
     @Bean(name = "beneficioTransactionManager")
     public PlatformTransactionManager transactionManager(){
         JpaTransactionManager transactionManager = new JpaTransactionManager();
         transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
         
         return transactionManager;
     }
}
