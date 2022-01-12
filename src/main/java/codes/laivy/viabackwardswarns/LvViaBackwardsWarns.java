package codes.laivy.viabackwardswarns;

import codes.laivy.viabackwardswarns.listeners.BlocksListener;
import codes.laivy.viabackwardswarns.listeners.VersionsListener;
import com.viaversion.viaversion.api.Via;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class LvViaBackwardsWarns extends JavaPlugin {

    public static LvViaBackwardsWarns plugin() {
        return getPlugin(LvViaBackwardsWarns.class);
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();

        Plugin plugin = Bukkit.getPluginManager().getPlugin("ViaBackwards");
        if (plugin == null || !plugin.isEnabled()) {
            setEnabled(false);
            broadcastColoredMessage("§cNão foi possível encontrar o plug-in §4ViaBackwards§c.");
            return;
        }

        if (
                !getConfig().contains("check-updates") ||
                !getConfig().contains("aviso de versão recomendada") ||
                !getConfig().contains("mensagem de versão recomendada") ||
                !getConfig().contains("aviso de versão correta") ||
                !getConfig().contains("mensagem de versão correta") ||
                !getConfig().contains("aviso de blocos acima da 256") ||
                !getConfig().contains("mensagem de blocos acima da 256")
        ) {
            broadcastColoredMessage("§cO seu arquivo de configurações foi resetado pois estava faltando pedaços.");
            reloadConfig();
            setEnabled(false);
            return;
        }

        if (
                getConfig().contains("aviso de versão recomendada") ||
                getConfig().contains("aviso de versão correta")
        ) {
            Bukkit.getPluginManager().registerEvents(new VersionsListener(), this);
        }

        if (
                Via.getAPI().getServerVersion().highestSupportedVersion() >= 755 ||
                getConfig().getBoolean("aviso de blocos acima da 256")
        ) {
            Bukkit.getPluginManager().registerEvents(new BlocksListener(), this);
        }

        new Thread(new Updater()).start();
    }

    public static void broadcastColoredMessage(String message) {
        plugin().getServer().getConsoleSender().sendMessage("§8[§6" + plugin().getDescription().getName() + "§8]§7" + " " + ChatColor.translateAlternateColorCodes('&', message));
    }

}
