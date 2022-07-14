package lee.code.namecolors.listeners;

import lee.code.namecolors.NameColors;
import net.luckperms.api.LuckPermsProvider;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import static lee.code.namecolors.PluginUtility.getLuckPermRankColor;

public class ChatListener implements Listener {

    @EventHandler
    public static void onChat(AsyncPlayerChatEvent event) {
        NameColors plugin = NameColors.getPlugin();
        Player player = event.getPlayer();
        ChatColor color = ChatColor.WHITE;

        if (plugin.getData().getPermissionSystemSupport()) {
            if (player.isOp()) {
                color = plugin.getData().getOPColor();
            } else {
                for (ChatColor permColor : ChatColor.values()) {
                    if (player.hasPermission("color." + permColor.name())) {
                        color = permColor;
                        break;
                    }
                }
            }
        } else if (plugin.getData().getLuckPermsSupport()) {
            color = getLuckPermRankColor(player.getUniqueId());
        }

        event.setFormat(String.format("<%s%%s%s> %%s", color, ChatColor.RESET));
    }
}
