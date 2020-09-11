package pl.lakasabasz.mc.radiacja.cmds;

import org.bukkit.command.CommandSender;

import pl.lakasabasz.mc.radiacja.tools.Logger;
import pl.lakasabasz.mc.radiacja.tools.Messages;
import pl.lakasabasz.mc.radiacja.tools.MessagesType;

public class HelpCommand implements CatchableCommand {

	private String keyWord = "help";
	
	@Override
	public void execute(CommandSender sender, String[] args) {
		Logger.sendInfo(sender, Messages.getMessage(MessagesType.HELP));
	}

	@Override
	public String getKeyWord() {
		return keyWord;
	}

	@Override
	public String getUsing() {
		return keyWord;
	}

	@Override
	public String getDescription() {
		return "Wyświetla pomoc dotyczącą komend pluginu";
	}

}
