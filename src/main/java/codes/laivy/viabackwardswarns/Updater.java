package codes.laivy.viabackwardswarns;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import static codes.laivy.viabackwardswarns.LvViaBackwardsWarns.*;

public class Updater implements Runnable {
    @Override
    public void run() {
        if (plugin().getConfig().getBoolean("check-updates")) {
            StringBuilder content = new StringBuilder();
            try {
                URL url = new URL("https://api.github.com/repos/LaivyTLife/ViaBackwardsWarns/releases");
                URLConnection urlConnection = url.openConnection();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    content.append(line).append("\n");
                }

                bufferedReader.close();
            } catch (Exception e) {
                broadcastColoredMessage("&cNão foi possível verificar por novas atualizações, você está conectado à internet?");
                return;
            }

            List<String> releases = new ArrayList<>();
            JsonArray dataJson = new JsonParser().parse(content.toString()).getAsJsonArray();
            for (JsonElement e : dataJson) {
                JsonObject obj = e.getAsJsonObject();
                releases.add(obj.get("tag_name").getAsString());
            }

            if (!releases.get(0).equals(plugin().getDescription().getVersion())) {
                broadcastColoredMessage("&cUma nova versão do plugin está disponível :)");
                broadcastColoredMessage("&cBaixe aqui: &6https://github.com/LaivyTLife/ViaBackwardsWarns/releases/" + releases.get(0) + "/");
            }
        }
    }
}
