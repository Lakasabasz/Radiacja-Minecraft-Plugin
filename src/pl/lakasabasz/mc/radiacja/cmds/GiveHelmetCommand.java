package pl.lakasabasz.mc.radiacja.cmds;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import pl.lakasabasz.mc.radiacja.tools.ArmorTool;
import pl.lakasabasz.mc.radiacja.tools.Logger;
import pl.lakasabasz.mc.radiacja.tools.Messages;
import pl.lakasabasz.mc.radiacja.tools.MessagesType;

public class GiveHelmetCommand implements CatchableCommand {

	@Override
	public void execute(CommandSender sender, String[] args) {
		if(!(sender instanceof Player)) {
			Logger.sendError(sender, Messages.getMessage(MessagesType.ERROR_ONLY_PLAYER));
			return;
		}
		
		if(args.length == 1) {
			Logger.sendError(sender, Messages.getMessage(MessagesType.ERROR_TOOLOW_ARGS));
			return;
		} else if(args.length > 2) {
			Logger.sendError(sender, Messages.getMessage(MessagesType.ERROR_TOOMANY_ARGS));
			return;
		}
		
		ItemStack is;
		if(args[1].equalsIgnoreCase("żółty")) {
			is = ArmorTool.getArmorHelmet(false);
		} else if(args[1].equalsIgnoreCase("biały")) {
			is = ArmorTool.getArmorHelmet(true);
		} else {
			Logger.sendError(sender, Messages.getMessage(MessagesType.ERROR_WRONG_ARG));
			return;
		}
		
		((HumanEntity) sender).getInventory().addItem(is);
	}

	@Override
	public String getKeyWord() {
		// TODO Auto-generated method stub
		return "givehelmet";
	}

	@Override
	public String getUsing() {
		// TODO Auto-generated method stub
		return "givehelmet <biały/żółty>";
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "Komanda dająca hełm ołowiowy";
	}

}
