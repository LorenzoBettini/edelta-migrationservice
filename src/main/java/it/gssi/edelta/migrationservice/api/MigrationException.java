package it.gssi.edelta.migrationservice.api;

@SuppressWarnings("serial")
public class MigrationException extends RuntimeException {

	public MigrationException() {
		super();
	}

	public MigrationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public MigrationException(String message, Throwable cause) {
		super(message, cause);
	}

	public MigrationException(String message) {
		super(message);
	}

	public MigrationException(Throwable cause) {
		super(cause);
	}
	

}
