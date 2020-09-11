package pl.lakasabasz.mc.radiacja.tools;

import java.util.HashMap;
import java.util.Map;

public class Permissions {
	
	private static Map<PermissionsType, String> messagesDB;
	static {
		messagesDB = new HashMap<PermissionsType, String>();
		messagesDB.put(PermissionsType.COMMAND_USAGE, "radiacja.cmd");
	}
	
	public static String getPermission(PermissionsType permission) {
		return messagesDB.get(permission);
	}
}
