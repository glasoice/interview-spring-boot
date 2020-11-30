package com.echevarne.sap.cloud.interview.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Class for the {@link ServiceConfig}.
 * 
 * <p>. . .</p>
 * <p>This is the service configuration for the beans </p>
 *
 */
@SpringBootConfiguration
@EnableJpaRepositories(basePackages = {"com.echevarne.sap.cloud.interview.repositories"})
@EntityScan( basePackages = {"com.echevarne.sap.cloud.interview.model"} )
@ComponentScan(basePackages = {"com.echevarne.sap.cloud.interview"})
@ServletComponentScan(basePackages = {"com.echevarne.sap.cloud.interview.odata.services"})
public class ServiceConfig {

}
