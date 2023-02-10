package my.config;


import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "my.dao")
@PropertySource(value = {
        "classpath:/database.properties",
        "classpath:/hibernate.properties"
})
@EnableJpaRepositories(basePackages = "my.dao")
public class ConfigSources {

    @Bean
    public Properties hibernateProperties(
            @Value("${hibernate.dialect}") String dialect,
            @Value("${hibernate.show_sql:false}") String showSql,
            @Value("${hibernate.format_sql:false}") String formatSql,
            @Value("${hibernate.hbm2ddl.auto:validate}") String hbm2ddl,
            @Value("${hibernate.enable_lazy_load_no_trans:false}") String lazyNoTrans) {
        Properties properties = new Properties();
        properties.put(Environment.DIALECT, dialect);
        properties.put(Environment.SHOW_SQL, showSql);
        properties.put(Environment.FORMAT_SQL, formatSql);
        properties.put(Environment.HBM2DDL_AUTO, hbm2ddl);
        properties.put(Environment.ENABLE_LAZY_LOAD_NO_TRANS, lazyNoTrans);
        return properties;
    }

    @Bean
    public DataSource dataSource(
            @Value("${url}") String url,
            @Value("${username}") String username,
            @Value("${password}") String password,
            @Value("${driver}") String driver,
            @Value("${connection.remove_abandoned:true}") boolean removeAbandoned,
            @Value("${connection.initial_size:10}") int initialSize,
            @Value("${connection.max_total:25}") int maxTotal) {

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(url);
        dataSource.setDriverClassName(driver);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setRemoveAbandonedOnBorrow(removeAbandoned);
        dataSource.setInitialSize(initialSize);
        dataSource.setMaxTotal(maxTotal);
        return dataSource;
    }

    @Bean
    public EntityManagerFactory entityManagerFactory(DataSource dataSource, Properties hibernateProperties) {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("my.model");
        factory.setDataSource(dataSource);
        factory.setJpaProperties(hibernateProperties);
        factory.afterPropertiesSet();

        return factory.getObject();
    }


    @Bean
    public PlatformTransactionManager transactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }
}

