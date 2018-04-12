package taowang6000.holi.utils;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Settings {
	//public static boolean emf_excuted;
	public static EntityManagerFactory emf;
	public static String[] tableName;			//table name to be shown on the home page
	public static void setUp() throws Exception {
		emf = Persistence.createEntityManagerFactory("holi_manager");
		tableName = new String[] {
				"checkout_order",
				"item",
				"role",
				"user_account"
		};
	}
}
