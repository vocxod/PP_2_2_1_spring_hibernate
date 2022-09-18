package hiber.config;

import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
@ComponentScan(value = "hiber")
public class AppConfig {

  @Autowired
  private Environment env;

  @Bean
  public DataSource getDataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();

    dataSource.setDriverClassName(env.getProperty("com.mysql.cj.jdbc.Driver"));
    dataSource.setUrl(env.getProperty(
        "jdbc:mysql://localhost:3306/pp221?verifyServerCertificate=false&useSSL=false&"
            + "requireSSL=false&useLegacyDatetimeCode=false&amp&serverTimezone=UTC"));
    dataSource.setUsername(env.getProperty("pp221"));
    dataSource.setPassword(env.getProperty("Pp221Apple1976@@@###Fuck"));
    /*
     * dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
     * dataSource.setUrl("jdbc:mysql://localhost:3306/pp221");
     * dataSource.setUsername("pp221");
     * dataSource.setPassword("Pp221Apple1976@@@###Fuck");
     */
    return dataSource;

  }

  @Bean
  public LocalSessionFactoryBean getSessionFactory() {
    LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
    factoryBean.setDataSource(getDataSource());

    Properties props = new Properties();
    props.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
    props.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));

    factoryBean.setHibernateProperties(props);
    factoryBean.setAnnotatedClasses(User.class);
    return factoryBean;
  }

  @Bean
  public HibernateTransactionManager getTransactionManager() {
    HibernateTransactionManager transactionManager = new HibernateTransactionManager();
    transactionManager.setSessionFactory(getSessionFactory().getObject());
    return transactionManager;
  }
}
