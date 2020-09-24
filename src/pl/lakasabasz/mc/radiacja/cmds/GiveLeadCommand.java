package pl.lakasabasz.mc.radiacja.cmds;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;

import pl.lakasabasz.mc.radiacja.tools.LeadTool;
import pl.lakasabasz.mc.radiacja.tools.Logger;
import pl.lakasabasz.mc.radiacja.tools.Messages;
import pl.lakasabasz.mc.radiacja.tools.MessagesType;

public class GiveLeadCommand implements CatchableCommand {

	@Override
	public void execute(CommandSender sender, String[] args) {
		if(!(sender instanceof Player)) {
			Logger.sendError(Messages.getMessage(MessagesType.ERROR_ONLY_PLAYER));
			return;
		}
		
		((HumanEntity) sender).getInventory().addItem(LeadTool.getLeadNugget());
	}

	@Override
	public String getKeyWord() {
		return "givelead";
	}

	@Override
	public String getUsing() {
		return "givelead";
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "Komenda daje 1 sztukę ołowiu";
	}

}
