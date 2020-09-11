package pl.lakasabasz.mc.radiacja;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.annotation.dependency.Dependency;
import org.bukkit.plugin.java.annotation.permission.Permission;
import org.bukkit.plugin.java.annotation.plugin.ApiVersion;
import org.bukkit.plugin.java.annotation.plugin.Description;
import org.bukkit.plugin.java.annotation.plugin.Plugin;
import org.bukkit.plugin.java.annotation.plugin.author.Author;
import org.bukkit.scheduler.BukkitTask;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

import pl.lakasabasz.mc.radiacja.cmds.AddRegionCommand;
import pl.lakasabasz.mc.radiacja.cmds.CatchableCommand;
import pl.lakasabasz.mc.radiacja.cmds.GiveHelmetCommand;
import pl.lakasabasz.mc.radiacja.cmds.HelpCommand;
import pl.lakasabasz.mc.radiacja.cmds.ListRegionCommand;
import pl.lakasabasz.mc.radiacja.cmds.ReloadConfigCommand;
import pl.lakasabasz.mc.radiacja.cmds.RemoveRegionCommand;
import pl.lakasabasz.mc.radiacja.cmds.SaveConfigCommand;
import pl.lakasabasz.mc.radiacja.schedulers.RadiationAreasEffects;
import pl.lakasabasz.mc.radiacja.tools.BossBarType;
import pl.lakasabasz.mc.radiacja.tools.BossBarsManager;
import pl.lakasabasz.mc.radiacja.tools.Commands;
import pl.lakasabasz.mc.radiacja.tools.Logger;
import pl.lakasabasz.mc.radiacja.tools.Messages;
import pl.lakasabasz.mc.radiacja.tools.MessagesType;
import pl.lakasabasz.mc.radiacja.tools.Permissions;
import pl.lakasabasz.mc.radiacja.tools.PermissionsType;


@Plugin(name = "Radiacja", version = "0.0.4.2")
@Description(value = "Plugin tworzący strefy radiacji")
@Author(value = "Łukasz Łakasabasz Mastalerz")
@Dependency(value = "WorldGuard")
@org.bukkit.plugin.java.annotation.command.Commands(@org.bukkit.plugin.java.annotation.command.Command(name = "radiacja", desc = "Komenda konfiguracyjna", permission = "radiacja.cmd", usage = "/radiacja"))
@Permission(name = "radiacja.cmd")
@Permission(name = "radiacja.*")
@ApiVersion(ApiVersion.Target.v1_15)
public class Main extends JavaPlugin {
	
	private static Main instance;
	private List<ProtectedRegion> areas;
	private boolean everyAreaIsSafe;
	private BukkitTask radiationAreasEffectScheduler;
	
	@Override
	public void onEnable() {
		Logger.sendInfo("Starting");
		
		instance = this;
		areas = new ArrayList<ProtectedRegion>();
		everyAreaIsSafe = true;
		
		this.saveDefaultConfig();
		boolean eais = this.getConfig().getBoolean("everyAreaIsSafe");
		if(!Objects.isNull(eais)) {
			everyAreaIsSafe = eais;
		} else {
			Logger.sendError(Messages.getMessage(MessagesType.ERROR_LOAD_EAIS));
		}
		List<String> savedRegions = this.getConfig().getStringList("exceptAreas");
		if(Objects.isNull(savedRegions)) {
			Logger.sendError(Messages.getMessage(MessagesType.ERROR_LOAD_REGIONS));
			savedRegions = new ArrayList<String>();
		}
		RegionManager wgrm = WorldGuard.getInstance().getPlatform().getRegionContainer().get(BukkitAdapter.adapt(Bukkit.getWorld("world")));
		for(String area : savedRegions) {
			ProtectedRegion rgn = wgrm.getRegion(area);
			if(Objects.isNull(rgn)) {
				Logger.sendWarning(Messages.getMessage(MessagesType.WARRNING_CHECK_REGIONS));
			} else {
				areas.add(rgn);
			}
		}
		if(areas.isEmpty()) {
			Logger.sendWarning(Messages.getMessage(MessagesType.WARRNING_EMPTY_REGIONS));
		}
		
		BossBarsManager.setBossBar(BossBarType.RADIATION_AREA, Bukkit.createBossBar("Strefa skażenia radioaktywnego", BarColor.RED,  BarStyle.SOLID, BarFlag.CREATE_FOG, BarFlag.DARKEN_SKY));
		
		radiationAreasEffectScheduler = Bukkit.getScheduler().runTaskTimer(this, new RadiationAreasEffects(), 2*20, 20);
		
		Commands.registerCommand(new HelpCommand());
		Commands.registerCommand(new AddRegionCommand());
		Commands.registerCommand(new SaveConfigCommand());
		Commands.registerCommand(new ReloadConfigCommand());
		Commands.registerCommand(new ListRegionCommand());
		Commands.registerCommand(new RemoveRegionCommand());
		Commands.registerCommand(new GiveHelmetCommand());
		Messages.postInit();
		Logger.sendInfo("Started");
	}
	
	@Override
	public void onDisable() {
		Logger.sendInfo("Stopping");
		
		radiationAreasEffectScheduler.cancel();
		
		List<String> areasID = new ArrayList<String>();
		for(ProtectedRegion pr : areas) {
			areasID.add(pr.getId());
			Logger.sendDebug(pr.getId());
		}
		this.getConfig().set("exceptAreas", areasID);
		this.getConfig().set("everyAreaIsSafe", everyAreaIsSafe);
		Logger.sendDebug(this.getConfig().getStringList("exceptAreas").size() + "");
		this.saveConfig();
		Logger.sendInfo("Stoped");
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!sender.hasPermission(Permissions.getPermission(PermissionsType.COMMAND_USAGE))) {
			Logger.sendError(sender, Messages.getMessage(MessagesType.ERROR_NO_PERMISSIONS));
			return true;
		}
		
		if(args.length == 0) {
			Logger.sendInfo(sender, Messages.getMessage(MessagesType.HELP));
			return true;
		}
		
		for(CatchableCommand cc : Commands.getRegistredCommands()) {
			if(args[0].equalsIgnoreCase(cc.getKeyWord())) {
				cc.execute(sender, args);
				return true;
			}
		}
		
		Logger.sendError(sender, Messages.getMessage(MessagesType.ERROR_NO_COMMAND));
		return true;
	}

	public static Main getInstance() {
		return instance;
	}

	public boolean addRegion(ProtectedRegion pr) {
		if(areas.contains(pr)) return false;
		areas.add(pr);
		return true;
	}

	public List<ProtectedRegion> getAreas() {
		return areas;
	}

	public boolean getEveryAreaIsSafe() {
		return everyAreaIsSafe;
	}

	public void setEveryAreaIsSafe(boolean eais) {
		this.everyAreaIsSafe = eais;
	}

	public void setRegions(ArrayList<ProtectedRegion> arrayList) {
		this.areas = arrayList;
	}

	public boolean removeArea(String regionid) {
		for(ProtectedRegion pr : areas) {
			if(pr.getId().equalsIgnoreCase(regionid)) {
				areas.remove(pr);
				return true;
			}
		}
		return false;
	}
}
