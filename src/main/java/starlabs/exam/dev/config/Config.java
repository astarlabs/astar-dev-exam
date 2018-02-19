package starlabs.exam.dev.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.cfg.AvailableSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource(value = { "file:/cfip/conf/env.properties" })
@ComponentScan(value = "com.boxs.cfip.core.dao*")
public class Config {
	private static final String PERSISTENCE_UNIT = "PU_CFIP";
	@Autowired
	private Environment env;

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(createDataSource());
		emf.setJpaVendorAdapter(jpaVendorApapter());
		emf.setJpaProperties(getProperties());
		emf.setPersistenceUnitName(PERSISTENCE_UNIT);
		emf.setPackagesToScan("com.boxs.cfip.core.model");
		return emf;
	}
	@Bean
	public DataSource createDataSource() {
		DriverManagerDataSource dataSource = null;
		dataSource = new DriverManagerDataSource();
		
		dataSource.setDriverClassName("org.hsqldb.jdbcDriver");
		//dataSource.setUrl("jdbc:hsqldb:file:/astar/devexamdb");
		dataSource.setUrl("jdbc:hsqldb:file:/astar/devexamdb");
		dataSource.setUsername("sa");
		dataSource.setPassword("");
		return dataSource;
	}
	private String getValue(String properties) {
		String value = env.getProperty(properties);
		return value;
	}
	private Properties getProperties() {
		Properties properties = new Properties();
		properties.put(AvailableSettings.HBM2DDL_AUTO, "update");
		properties.put(AvailableSettings.DIALECT, "org.hibernate.dialect.HSQLDialect");
		properties.put(AvailableSettings.SHOW_SQL, "true");
		return properties;
	}

	@Bean
	public JpaVendorAdapter jpaVendorApapter() {
		return new HibernateJpaVendorAdapter();
	}

	@Bean
	@Autowired
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManager) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManager);
		return transactionManager;
	}
}