package com.arduino.config;

import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
@EnableJpaRepositories(basePackages = "com.arduino.dao", entityManagerFactoryRef = "entityManagerFactory", repositoryImplementationPostfix = "Impl")
public class JpaConfig {

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
		entityManager.setDataSource(dataSource);
		// 设置packagesToScan就不需要persistence.xml文件了
		entityManager.setPackagesToScan("com.arduino.dmo");
		entityManager.setPersistenceProviderClass(HibernatePersistenceProvider.class);
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setGenerateDdl(false);
		adapter.setShowSql(false);
		adapter.setDatabase(Database.MYSQL);
		adapter.setDatabasePlatform("org.hibernate.dialect.MySQL5InnoDBDialect");
		entityManager.setJpaVendorAdapter(adapter);
		entityManager.setJpaDialect(new HibernateJpaDialect());
		return entityManager;
	}

	/**
	 * 
	 * 使用@PersistenceContext注解，启动实体管理器之前，
	 * 必须注册一个PersistenceAnnotationBeanPostProcessor实例
	 */
	@Bean
	public PersistenceAnnotationBeanPostProcessor getPersistenceContexts() {
		return new PersistenceAnnotationBeanPostProcessor();
	}

	@Bean
	public JpaTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory.getObject());
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		return jdbcTemplate;
	}
}
