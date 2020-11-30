package com.echevarne.sap.cloud.interview.config;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Class for the {@link ContextProvider}.
 * 
 * <p>. . .</p>
 * <p>This is the configuration beans </p>
 *
 * 
 */
@SpringBootConfiguration
@ComponentScan(basePackages = {"com.echevarne.sap.cloud.facturacion"})
@EnableTransactionManagement
@EnableConfigurationProperties
public class DatabaseConfig {

    private static Logger logger = LoggerFactory.getLogger(DatabaseConfig.class);

    /**
     * 
     * @return
     */
    @Profile("test")
    @Bean("dataSourceTest")
    public DataSource dataSourceTest() {
        try {
            EmbeddedDatabaseBuilder databaseBuilder = new EmbeddedDatabaseBuilder();
            return databaseBuilder.setType(EmbeddedDatabaseType.H2).build();
        } catch (Exception e) {
            logger.error("Embedded Database error ", e);
            return null;
        }
    }

    /**
     * 
     * @return
     */
    @Bean
    @Profile("mysql")
    public DataSource dataSourceDev() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://192.999.999.99:3306/dbInterview");
        dataSource.setUsername("interview");
        dataSource.setPassword("genericpass");
        
        return dataSource;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propConfig(){
        PropertySourcesPlaceholderConfigurer placeholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        placeholderConfigurer.setLocations( new ClassPathResource( "application.properties" ) );
        placeholderConfigurer.setIgnoreUnresolvablePlaceholders( true );
        return placeholderConfigurer;
    }

}
