package conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "repository")
public class DataConfig {

  private static final String PACKAGE_ENTITY = "repository.entity";

  private static final String DRIVER_NAME = "org.postgresql.Driver";

  @Value("${datasource.url}")
  private String dataSourceUrl;

  @Value("${datasource.username}")
  private String datasourceUsername;

  @Value("${datasource.password}")
  private String datasourcePassword;

  @Value("${datasource.schema}")
  private String schema;

  @Value("${spring.jpa.properties.hibernate.dialect}")
  private String hibernateDialect;

  @Value("${spring.jpa.hibernate.ddl-auto}")
  private String ddl;


  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    LocalContainerEntityManagerFactoryBean em
        = new LocalContainerEntityManagerFactoryBean();
    em.setPackagesToScan(PACKAGE_ENTITY);
    em.setDataSource(dataSource());
    JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    em.setJpaVendorAdapter(vendorAdapter);
    em.setJpaProperties(additionalProperties());
    return em;
  }

  @Bean
  public DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName(DRIVER_NAME);
    dataSource.setUrl(dataSourceUrl);
    dataSource.setUsername(datasourceUsername);
    dataSource.setPassword(datasourcePassword);
    dataSource.setSchema(schema);
    return dataSource;
  }

  @Bean
  public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(emf);
    return transactionManager;
  }

  @Bean
  public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
    return new PersistenceExceptionTranslationPostProcessor();
  }

  Properties additionalProperties() {
    Properties properties = new Properties();
    properties.setProperty("hibernate.hbm2ddl.auto", ddl);
    properties.setProperty("hibernate.dialect", hibernateDialect);
    properties.setProperty("hibernate.jdbc.lob.non_contextual_creation","true");
    properties.setProperty("hibernate.default_schema", schema);
    return properties;
  }
}
