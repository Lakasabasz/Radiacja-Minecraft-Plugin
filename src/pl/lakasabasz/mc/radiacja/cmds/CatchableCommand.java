package pl.lakasabasz.mc.radiacja.cmds;

import org.bukkit.command.CommandSender;

public interface CatchableCommand {

	void execute(CommandSender sender, String[] args);

	String getKeyWord();
	
	String getUsing();
	
	String getDescription();

}
