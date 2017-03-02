package com.guillermods.common.security.config;

import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.ClassUtils;

import com.guillermods.common.security.domain.User;
import com.guillermods.common.security.repository.UserRepository;

@Profile("test")
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackageClasses = {UserRepository.class},
    entityManagerFactoryRef = "securityEntityManagerFactory")
public class PersistenceConfig {

  private static final String USER_DB = "sa";
  private static final String PASSWORD_DB = "";


  private static final String JDBC_URL =
      "jdbc:h2:mem:migrationtestdb;DB_CLOSE_DELAY=-1;TRACE_LEVEL_SYSTEM_OUT=1";

  @Bean
  public DataSource dataSource() throws SQLException {
    try {
      Class.forName("org.h2.Driver");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setUsername(USER_DB);
    dataSource.setPassword(PASSWORD_DB);
    dataSource.setUrl(JDBC_URL);

    Flyway flyway = new Flyway();
    flyway.setLocations("classpath:db/migration");
    flyway.setClassLoader(this.getClass().getClassLoader());
    flyway.setDataSource(dataSource);
    flyway.migrate();
    return flyway.getDataSource();
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean securityEntityManagerFactory() throws SQLException {
    LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
    em.setDataSource(dataSource());
    em.setPersistenceUnitName("securityPersistence");
    em.setPackagesToScan(ClassUtils.getPackageName(User.class));
    JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    em.setJpaVendorAdapter(vendorAdapter);
    em.setJpaProperties(additionalProperties());
    return em;
  }

  private Properties additionalProperties() {
    Properties properties = new Properties();
    properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
    return properties;
  }

  @Bean
  public JpaTransactionManager transactionManager() throws SQLException {
    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(securityEntityManagerFactory().getObject());
    return transactionManager;
  }

}
