package com.kakaopay;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "com.kakaopay")
@EnableJpaRepositories(basePackages = "com.kakaopay")
public class ServiceConfig {

//    @Bean
//    public DataSource dataSource(){
//        ComboPooledDataSource dataSource = new ComboPooledDataSource();
//        try{
//            dataSource.setDriverClass("org.mariadb.jdbc.Driver");
//        }catch (PropertyVetoException e) {
//            throw new RuntimeException(e);
//        }
//
//        dataSource.setJdbcUrl("jdbc:mariadb://localhost:3306/jpastart?characterEncoding=utf8");
//        dataSource.setUser("jpauser");
//        dataSource.setPassword("jpapass");
//        return dataSource;
//    }
//
//    @Bean
//   public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//       LocalContainerEntityManagerFactoryBean emfBean = new LocalContainerEntityManagerFactoryBean();
//       emfBean.setDataSource(dataSource());
//       emfBean.setPersistenceUnitName("kakaopay");
//
//       HibernateJpaVendorAdapter jva = new HibernateJpaVendorAdapter();
//       jva.setDatabase(Database.MYSQL);
//       jva.setShowSql(true);
//
//       emfBean.setJpaVendorAdapter(jva);
//       return emfBean;
//   }
//
//   @Bean
//   public JpaTransactionManager transactionManager(EntityManagerFactory emf){
//       JpaTransactionManager txMgr = new JpaTransactionManager();
//       txMgr.setEntityManagerFactory(emf);
//       return txMgr;
//   }
}
