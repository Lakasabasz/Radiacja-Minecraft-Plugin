package pl.lakasabasz.mc.radiacja.tools;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

import net.md_5.bungee.api.ChatColor;
import pl.lakasabasz.mc.radiacja.cmds.CatchableCommand;

public class Messages {

	private static Map<MessagesType, String> messagesDB;
	static {
		messagesDB = new HashMap<MessagesType, String>();
		messagesDB.put(MessagesType.INTRO, ChatColor.RESET + "[" + ChatColor.DARK_RED + "Radiacja" + ChatColor.RESET + "]");
		messagesDB.put(MessagesType.ERROR_LOAD_EAIS, "Błąd podczas ładowania parametru everyAreaIsSave z config.yml, ustawiono wartość domyślną");
		messagesDB.put(MessagesType.ERROR_LOAD_REGIONS, "Błąd podczas ładowania listy regionów z config.yml, regiony nie działają");
		messagesDB.put(MessagesType.ERROR_NO_COMMAND, "Komenda nie istnieje, wpisz /radiacja aby otrzymać pomoc");
		messagesDB.put(MessagesType.ERROR_NO_PERMISSIONS, "Nie masz uprawnień do korzystania z tej komendy");
		messagesDB.put(MessagesType.ERROR_NOTEXIST_REGION, "Wybrany region nie istnieje");
		messagesDB.put(MessagesType.ERROR_TOOLOW_ARGS, "Komenda wymaga więcej argumentów. Aby uzyskać pomoc wpisz /radiacja");
		messagesDB.put(MessagesType.ERROR_TOOMANY_ARGS, "Zostało podane za dużo argumentów. Aby uzyskać pomoc wpisz /radiacja");
		messagesDB.put(MessagesType.ERROR_NOTFOUND_REGION, "Podany region nie został znaleziony w bazie regionów. Wpisz /radiacja list aby zobaczyć listę regionów");
		messagesDB.put(MessagesType.ERROR_ONLY_PLAYER, "Komenda nie jest możliwa do uruchomienia z poziomu konsoli");
		messagesDB.put(MessagesType.ERROR_WRONG_ARG, "Podane argumenty są niepoprawne. Wpisz /radiacja aby uzyskać pomoc");
		messagesDB.put(MessagesType.WARRNING_CHECK_REGIONS, "Ten region nie istnieje. Prawdopodobnie nie został utworzony lub został skasowany");
		messagesDB.put(MessagesType.WARRNING_EMPTY_REGIONS, "Lista regionów jest pusta");
		messagesDB.put(MessagesType.WARRNING_ADDEXISTING_REGION, "Próbujesz dodać istniejącą strefę");
		messagesDB.put(MessagesType.SUCCESS_ADDED_REGION, "Pomyślnie dodano nową strefę");
		messagesDB.put(MessagesType.SUCCESS_SAVE_CONFIG, "Pomyślnie zapisano konfigurację");
		messagesDB.put(MessagesType.SUCCESS_RELOAD_CONFIG, "Pomyślnie przeładowano konfigurację");
		messagesDB.put(MessagesType.SUCCESS_REMOVE_REGION, "Pomyślnie usunięto strefę");
	}
	
	public static void postInit() {
		String helpMsg = messagesDB.get(MessagesType.INTRO) + ChatColor.LIGHT_PURPLE + " Dostępne komendy: \n";
		for(CatchableCommand cc : Commands.getRegistredCommands()) {
			helpMsg += messagesDB.get(MessagesType.INTRO) + ChatColor.LIGHT_PURPLE + "\t/radiacja " + cc.getUsing() + "\t" + cc.getDescription() + "\n";
		}
		messagesDB.put(MessagesType.HELP, helpMsg);
	}
	
	@Nullable
	public static String getMessage(MessagesType intro) {
		if(messagesDB.containsKey(intro)) {
			return messagesDB.get(intro);
		}
		return null;
	}

}
