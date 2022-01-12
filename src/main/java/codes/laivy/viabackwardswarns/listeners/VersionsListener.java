package codes.laivy.viabackwardswarns.listeners;

import com.viaversion.viaversion.api.Via;
import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static codes.laivy.viabackwardswarns.LvViaBackwardsWarns.plugin;

@SuppressWarnings({"ConstantConditions", "unused"})
public class VersionsListener implements Listener {

    @EventHandler
    private void join(PlayerJoinEvent e) {
        int playerVersionNumber = Via.getAPI().getPlayerVersion(e.getPlayer().getUniqueId());
        int serverVersionNumber = Via.getAPI().getServerVersion().highestSupportedVersion();
        String playerVersion = ProtocolVersion.getProtocol(playerVersionNumber).getName();
        String serverVersion = ProtocolVersion.getProtocol(serverVersionNumber).getName();

        if (playerVersion.equals(serverVersion)) {
            if (plugin().getConfig().getBoolean("aviso de versão correta")) {
                e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes(
                        '&',
                        plugin().getConfig().getString("mensagem de versão correta").replace("%player_version", playerVersion).replace("%server_version", serverVersion)
                ));
            }
        } else {
            if (plugin().getConfig().getBoolean("aviso de versão recomendada")) {
                e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes(
                        '&',
                        plugin().getConfig().getString("mensagem de versão recomendada").replace("%player_version", playerVersion).replace("%server_version", serverVersion)
                ));
            }
        }
    }

}
