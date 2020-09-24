package pl.lakasabasz.mc.radiacja.cmds;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;

import pl.lakasabasz.mc.radiacja.tools.IodyneTool;
import pl.lakasabasz.mc.radiacja.tools.Logger;
import pl.lakasabasz.mc.radiacja.tools.Messages;
import pl.lakasabasz.mc.radiacja.tools.MessagesType;

public class GiveIodyneCommand implements CatchableCommand {

	@Override
	public void execute(CommandSender sender, String[] args) {
		if(!(sender instanceof Player)) {
			Logger.sendError(sender, Messages.getMessage(MessagesType.ERROR_ONLY_PLAYER));
			return;
		}
		((HumanEntity) sender).getInventory().addItem(IodyneTool.getIodynePotion());
	}

	@Override
	public String getKeyWord() {
		return "giveiodyne";
	}

	@Override
	public String getUsing() {
		return "giveiodyne";
	}

	@Override
	public String getDescription() {
		return "Komenda dająca jodynę";
	}

}
