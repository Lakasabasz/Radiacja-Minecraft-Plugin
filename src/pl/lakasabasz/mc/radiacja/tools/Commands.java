package pl.lakasabasz.mc.radiacja.tools;

import java.util.ArrayList;
import java.util.List;

import pl.lakasabasz.mc.radiacja.cmds.CatchableCommand;

public class Commands {
	
	private static List<CatchableCommand> cmdsDB;
	static {
		cmdsDB = new ArrayList<CatchableCommand>();
	}

	public static List<CatchableCommand> getRegistredCommands() {
		return cmdsDB;
	}

	public static void registerCommand(CatchableCommand cmd) {
		cmdsDB.add(cmd);
	}

}
