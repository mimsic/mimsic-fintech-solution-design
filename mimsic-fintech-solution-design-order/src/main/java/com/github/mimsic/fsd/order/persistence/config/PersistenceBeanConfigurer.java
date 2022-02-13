package com.github.mimsic.fsd.order.persistence.config;

import com.github.mimsic.base.persistence.config.PersistenceConfig;
import com.github.mimsic.base.persistence.config.SchemaRoutingBeanFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Set;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = {
                "com.github.mimsic.fsd.order.persistence.repository"
        },
        entityManagerFactoryRef = "schemaRoutingEntityManagerFactory",
        transactionManagerRef = "schemaRoutingTransactionManager")
public class PersistenceBeanConfigurer implements InitializingBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersistenceBeanConfigurer.class);

    @Autowired
    private PersistenceConfig persistenceConfig;

    @Override
    public void afterPropertiesSet() throws Exception {
        LOGGER.info(persistenceConfig.toString());
    }

    private String[] entityPackages() {

        return new String[]{
                "com.github.mimsic.fsd.order.persistence.entity"
        };
    }

    @Bean
    public Set<String> schemas() {
        return persistenceConfig.schemas();
    }

    @Bean(name = "schemaRoutingDataSource")
    public DataSource schemaRoutingDataSource() {

        return new SchemaRoutingBeanFactory(persistenceConfig, entityPackages())
                .schemaRoutingDataSource();
    }

    @Bean(name = "schemaRoutingEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean schemaRoutingEntityManagerFactory(
            @Qualifier("schemaRoutingDataSource") DataSource dataSource) {

        return new SchemaRoutingBeanFactory(persistenceConfig, entityPackages())
                .entityManagerFactoryBean(dataSource, "schemaRoutingPersistenceUnit");
    }

    @Bean(name = "schemaRoutingTransactionManager")
    public PlatformTransactionManager schemaRoutingPlatformTransactionManager(
            @Qualifier("schemaRoutingEntityManagerFactory") EntityManagerFactory entityManagerFactory) {

        return new JpaTransactionManager(entityManagerFactory);
    }
}
