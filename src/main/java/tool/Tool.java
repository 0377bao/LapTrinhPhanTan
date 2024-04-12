package tool;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Tool {

	public static EntityManagerFactory initServer() {
		return Persistence.createEntityManagerFactory("MySql");
	}

}
