package com.echevarne.sap.cloud.interview.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.eclipse.persistence.config.PersistenceUnitProperties;
import org.eclipse.persistence.logging.SessionLog;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.JpaBaseConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.jta.JtaTransactionManager;

/**
 * <p>
 * Configuration Class of {@link EclipseLinkJpaConfiguration}. for Eclipse Link
 * </p>
 * 
 */
@SpringBootConfiguration
@EnableAutoConfiguration
public class EclipseLinkJpaConfiguration extends JpaBaseConfiguration {

	/**
	 * @param dataSource            .
	 * @param properties            .
	 * @param jtaTransactionManager .
	 */
	protected EclipseLinkJpaConfiguration(DataSource dataSource, JpaProperties properties,
			ObjectProvider<JtaTransactionManager> jtaTransactionManager) {
		super(dataSource, properties, jtaTransactionManager);
	}

	/**
	 * @return .
	 */
	@Override
	protected AbstractJpaVendorAdapter createJpaVendorAdapter() {
		return new EclipseLinkJpaVendorAdapter();
	}

	/**
	 * @return .
	 */
	@Override
	protected Map<String, Object> getVendorProperties() {
		HashMap<String, Object> map = new HashMap<>();
		map.put(PersistenceUnitProperties.WEAVING, detectWeavingMode());
		map.put(PersistenceUnitProperties.DDL_GENERATION, PersistenceUnitProperties.CREATE_OR_EXTEND);
		map.put(PersistenceUnitProperties.LOGGING_LEVEL, SessionLog.SEVERE_LABEL);
		return map;
	}

	private String detectWeavingMode() {
		return InstrumentationLoadTimeWeaver.isInstrumentationAvailable() ? "true" : "static";
	}

}
