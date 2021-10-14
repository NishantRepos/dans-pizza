package com.danspizza.config;

import java.util.Properties;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.danspizza.dao")
@ComponentScan(basePackages = {"com.danspizza"})
@PropertySource({"classpath:persistence-mysql.properties"})
public class ApplicationConfig implements WebMvcConfigurer {
	
	@Autowired
	private Environment env;
	
	private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String PROPERTY_NAME_HIBERNATE_FORMAT_SQL = "hibernate.format_sql";
    private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
	
	private Logger logger = Logger.getLogger(getClass().getName());
	
	// ViewResolver Bean
	@Bean
	public ViewResolver viewResolver() {
		
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		
		return viewResolver;		
	}
	
	// DataSource Bean
	@Bean
	public DataSource dpDataSource() {

		// create datasource
		DriverManagerDataSource dpDataSource = new DriverManagerDataSource();
		
		// set the jdbc driver
		try {
			dpDataSource.setDriverClassName(env.getProperty("jdbc.driver"));
		} catch (Exception exc) {
			throw new RuntimeException(exc);
		}
		
		// log the url for a check
		logger.info("jdbc.url = " + env.getProperty("jdbc.url"));
		logger.info("jdbc.user = " + env.getProperty("jdbc.user"));
		
		// set database connection properties
		dpDataSource.setUrl(env.getProperty("jdbc.url"));
		dpDataSource.setUsername(env.getProperty("jdbc.user"));
		dpDataSource.setPassword(env.getProperty("jdbc.password"));
		
		return dpDataSource;
	}

	// EntityManagerFactory Bean
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		
		LocalContainerEntityManagerFactoryBean entityManagerFactory = 
				new LocalContainerEntityManagerFactoryBean();		
		
		// set the DataSource, HibernateJpaVendor and Entities package
		entityManagerFactory.setDataSource(dpDataSource());
		entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		entityManagerFactory.setPackagesToScan(env.getProperty("packagesToScan"));
		
		// configure the used Database dialect, allows Hibernate to create SQL
		Properties jpaProperties = new Properties();
		
		jpaProperties.put(PROPERTY_NAME_HIBERNATE_DIALECT,
				env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_DIALECT));
		
		jpaProperties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL,
				env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL));
		
		jpaProperties.put(PROPERTY_NAME_HIBERNATE_FORMAT_SQL,
				env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_FORMAT_SQL));
		
		entityManagerFactory.setJpaProperties(jpaProperties);
		
		return entityManagerFactory;
	}
	
	// JpaTransactionManager Bean
	@Bean
	public PlatformTransactionManager transactionManager() {
		
		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(entityManagerFactory().getObject());
		
		return txManager;
	}
	
	// ResourceBundleMessageSource Bean for showing error messages
	@Bean
	public ResourceBundleMessageSource errorMessageSource() {
		
		ResourceBundleMessageSource errorMessageSource = 
				new ResourceBundleMessageSource();
		
		errorMessageSource.setBasename("classpath:messages");
		errorMessageSource.setUseCodeAsDefaultMessage(true);
		errorMessageSource.setDefaultEncoding("UTF-8");
		
		return errorMessageSource;
		
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		registry
			.addResourceHandler("/resources/**")
			.addResourceLocations("/resources/");
		
	}
	
	
}





























