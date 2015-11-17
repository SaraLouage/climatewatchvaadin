package be.faros.dao;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.vaadin.spring.annotation.EnableVaadin;

import be.faros.entities.ClimateWatchEvent;
import be.faros.entities.Location;

@Configuration
@ComponentScan
@EnableTransactionManagement
@EnableVaadin
public class CreateDAOBeans {

	@Bean
	LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setDataSource(dataSource());
		factory.setPackagesToScan(ClimateWatchEvent.class.getPackage().getName());
		factory.setPackagesToScan(Location.class.getPackage().getName());
	factory.setPersistenceProviderClass(org.hibernate.jpa.HibernatePersistenceProvider.class);
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setShowSql(true);
		factory.setJpaVendorAdapter(adapter);
		factory.getJpaPropertyMap().put("hibernate.format_sql", true);
		factory.getJpaPropertyMap().put("hibernate.use_sql_comments", true);
		return factory;
	}
	@Bean
	public DataSource dataSource() {
	    DriverManagerDataSource dataSource = new DriverManagerDataSource();
	    dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
	    dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
	    dataSource.setUsername("root");
	    dataSource.setPassword("root");
	    return dataSource;
	}
	@Bean
	JpaTransactionManager transactionManager() {
		return new JpaTransactionManager(entityManagerFactory().getObject());
	}
}
