package codes.laivy.viabackwardswarns.listeners;

import com.viaversion.viaversion.api.Via;
import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import static codes.laivy.viabackwardswarns.LvViaBackwardsWarns.plugin;

@SuppressWarnings("unused")
public class BlocksListener implements Listener {

    @EventHandler
    private void place(BlockPlaceEvent e) {
        int playerVersionNumber = Via.getAPI().getPlayerVersion(e.getPlayer().getUniqueId());
        int serverVersionNumber = Via.getAPI().getServerVersion().highestSupportedVersion();
        String playerVersion = ProtocolVersion.getProtocol(playerVersionNumber).getName();
        String serverVersion = ProtocolVersion.getProtocol(serverVersionNumber).getName();

        if (
                Via.getAPI().getPlayerVersion(e.getPlayer().getUniqueId()) <= 754 &&
                e.getBlock().getLocation().getY() >= 256
        ) {
            e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes(
                    '&',
                    plugin().getConfig().getString("mensagem de blocos acima da 256").replace("%player_version", playerVersion).replace("%server_version", serverVersion)
            ));

            e.setCancelled(true);
        }
    }

}
