package codes.laivy.viabackwardswarns.listeners;

import com.viaversion.viaversion.api.Via;
import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class TestsListener implements Listener {

    @EventHandler
    private void p(PlayerInteractEvent e) {
        int playerVersionNumber = Via.getAPI().getPlayerVersion(e.getPlayer().getUniqueId());

        String playerVersion = ProtocolVersion.getProtocol(playerVersionNumber).getName();
        String serverVersion = ProtocolVersion.getProtocol(Via.getAPI().getServerVersion().highestSupportedVersion()).getName();

        Bukkit.broadcastMessage(playerVersionNumber + "(" + playerVersion + ") - " + Via.getAPI().getServerVersion().highestSupportedVersion() + "(" + serverVersion + ")");
    }

}
