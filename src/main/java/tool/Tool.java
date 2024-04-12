package tool;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Tool {

	public static EntityManagerFactory innitServer() {
		return Persistence.createEntityManagerFactory("MySql");
	}

	public static EntityManagerFactory initDriver() {
		return Persistence.createEntityManagerFactory("MySql");
	}
}
