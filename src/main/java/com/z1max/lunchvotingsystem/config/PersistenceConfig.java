package com.z1max.lunchvotingsystem.config;

import org.hibernate.cfg.AvailableSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.z1max.lunchvotingsystem.repository")
@PropertySource("classpath:db/hsqldb.properties")
public class PersistenceConfig {

    private final Environment env;

    @Autowired
    public PersistenceConfig(Environment env) {
        this.env = env;
    }

    @Bean
    public DataSource dataSource(){
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();

        builder.setType(EmbeddedDatabaseType.HSQL)
                .addScript(env.getProperty("jdbc.initLocation"))
                .addScript(env.getProperty("jdbc.insertLocation"));

        return builder.build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
        HibernateJpaVendorAdapter vendorAdapter =new HibernateJpaVendorAdapter();
        vendorAdapter.setShowSql(Boolean.valueOf(env.getProperty("jpa.showSql")));

        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("com.z1max.lunchvotingsystem.model");
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaPropertyMap(getPropertyMap());

        return em;
    }

    private Map<String, Object> getPropertyMap() {
        Map<String, Object> propertyMap = new HashMap<>();
        propertyMap.put(AvailableSettings.FORMAT_SQL, env.getProperty("hibernate.format_sql"));
        propertyMap.put(AvailableSettings.USE_SQL_COMMENTS, env.getProperty("hibernate.user_sql_comments"));
        propertyMap.put(AvailableSettings.JPA_PROXY_COMPLIANCE, true);
        propertyMap.put(AvailableSettings.DIALECT, env.getProperty("hibernate.dialect"));
        return propertyMap;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);

        return transactionManager;
    }
}
