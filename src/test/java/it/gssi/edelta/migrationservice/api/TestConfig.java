package it.gssi.edelta.migrationservice.api;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import it.gssi.edelta.migrationservice.configuration.MigrationConfigProperties;

@TestConfiguration
public class TestConfig {

	/**
	 * Create a test-specific MigrationConfigProperties bean This allows us to
	 * override the model folder path in tests
	 */
	@Bean
	@Primary
	public MigrationConfigProperties testMigrationConfigProperties() {
		return new MigrationConfigProperties();
	}
}
