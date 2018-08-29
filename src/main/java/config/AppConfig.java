package config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mysql.cj.jdbc.Driver;

@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
@ComponentScans(value = {@ComponentScan("dao"),@ComponentScan("service") })
public class AppConfig {
  @Autowired
  private Environment env;

  @Bean
  public LocalSessionFactoryBean getSessionFactory(){
    LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();

    Properties props = new Properties();
    // Setting JDBC properties
    props.put("mysql.driver",env.getProperty("mysql.driver"));
    props.put("mysql.url",env.getProperty("mysql.url"));
    props.put("mysql.user",env.getProperty("mysql.user"));
    props.put("mysql.password",env.getProperty("mysql.password"));

    // Setting Hibernate properties
    props.put("hibernate.show_sql",env.getProperty("hibernate.show_sql"));
    props.put("hibernate.hbm2ddl.auto",env.getProperty("hibernate.hbm2ddl.auto"));

    // Setting C3P0 properties
    props.put("hibernate.c3p0.min_size",env.getProperty("hibernate.c3p0.min_size"));
    props.put("hibernate.c3p0.max_size",env.getProperty("hibernate.c3p0.max_size"));
    props.put("hibernate.c3p0.acquire_increment",env.getProperty("hibernate.c3p0.acquire_increment"));
    props.put("hibernate.c3p0.timeout",env.getProperty("hibernate.c3p0.timeout"));
    props.put("hibernate.c3p0.max_statements",env.getProperty("hibernate.c3p0.max_statements"));

    factoryBean.setHibernateProperties(props);
    factoryBean.setPackagesToScan("model");

    return factoryBean;
  }

  @Bean
  public HibernateTransactionManager getTransactionManager(){
    HibernateTransactionManager transactionManager = new HibernateTransactionManager();
    transactionManager.setSessionFactory(getSessionFactory().getObject());
    return transactionManager;
  }

}
