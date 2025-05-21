package it.gssi.edelta.migrationservice.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "migration")
public class MigrationConfigProperties {

	private String modelfolder;

	public String getModelfolder() {
		return modelfolder;
	}

	public void setModelfolder(String modelfolder) {
		this.modelfolder = modelfolder;
	}
	
}
