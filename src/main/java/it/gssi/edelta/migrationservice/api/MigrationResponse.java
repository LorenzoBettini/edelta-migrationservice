package it.gssi.edelta.migrationservice.api;

import java.util.List;

public class MigrationResponse {
	
	private List<Byte[]> migratedModels;

	public MigrationResponse(List<Byte[]> migratedModels) {
		super();
		this.migratedModels = migratedModels;
	}

	public List<Byte[]> getMigratedModels() {
		return migratedModels;
	}
	
	
}
