package edu.asu.diging.sustainability.config;

import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:config.properties")
@EnableJpaRepositories(basePackages = "edu.asu.diging.sustainability.core.data")
public class PersistenceJPAConfig{
 
	@Autowired
	private Environment env;
	
	private String dbDriver;
	
	private String dbUrl;
	
	private String dbUser;
	
	private String dbPassword;
	
	@PostConstruct
	public void init() {
		dbDriver = env.getProperty("db_driver");
		dbUrl = env.getProperty("db_url");
		dbUser = env.getProperty("db_user");
		dbPassword = env.getProperty("db_password");
	}
	
   @Bean
   public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
      LocalContainerEntityManagerFactoryBean em 
        = new LocalContainerEntityManagerFactoryBean();
      em.setDataSource(dataSource());
      em.setPackagesToScan(new String[] { "edu.asu.diging.sustainability.core.model" });
 
      JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
      em.setJpaVendorAdapter(vendorAdapter);
      em.setJpaProperties(additionalProperties());
 
      return em;
   }
 
   @Bean
   public DataSource dataSource(){
      DriverManagerDataSource dataSource = new DriverManagerDataSource();
      dataSource.setDriverClassName(dbDriver);
      dataSource.setUrl(dbUrl);
      dataSource.setUsername(dbUser);
      dataSource.setPassword(dbPassword);
      return dataSource;
   }
 
   @Bean
   public PlatformTransactionManager transactionManager(
     EntityManagerFactory emf){
       JpaTransactionManager transactionManager = new JpaTransactionManager();
       transactionManager.setEntityManagerFactory(emf);
 
       return transactionManager;
   }
 
   @Bean
   public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
       return new PersistenceExceptionTranslationPostProcessor();
   }
 
   Properties additionalProperties() {
       Properties properties = new Properties();
       properties.setProperty("hibernate.hbm2ddl.auto", "update");
       properties.setProperty("hibernate.show_sql", "false");
       properties.setProperty(
         "hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        
       return properties;
   }
}