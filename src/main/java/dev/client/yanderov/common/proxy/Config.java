package dev.client.yanderov.common.proxy;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import net.minecraft.class_310;
import org.apache.commons.io.FileUtils;

public class Config {
   private static final File CONFIG_DIR;
   private static final File CONFIG_FILE;
   public static HashMap accounts;
   public static String lastPlayerName;

   public static void loadConfig() {
      try {
         if (!CONFIG_DIR.exists()) {
            CONFIG_DIR.mkdirs();
         }

         if (!CONFIG_FILE.exists()) {
            if (!CONFIG_FILE.createNewFile()) {
               System.out.println("Error creating Proxyconfig.json file");
            }

            saveConfig();
            return;
         }

         String configString = FileUtils.readFileToString(CONFIG_FILE, StandardCharsets.UTF_8);
         if (!configString.isEmpty()) {
            JsonObject configJson = JsonParser.parseString(configString).getAsJsonObject();
            if (configJson.has("proxy-enabled")) {
               ProxyServer.proxyEnabled = configJson.get("proxy-enabled").getAsBoolean();
            }

            Type type = (new TypeToken() {
            }).getType();
            if (configJson.has("accounts")) {
               accounts = (HashMap)(new Gson()).fromJson(configJson.get("accounts"), type);
            }

            if (accounts == null) {
               accounts = new HashMap();
            }

            if (accounts.containsKey("")) {
               ProxyServer.proxy = (Proxy)accounts.get("");
            } else {
               ProxyServer.proxy = new Proxy();
            }
         }
      } catch (Exception e) {
         System.out.println("Error reading Proxyconfig.json file");
         e.printStackTrace();
      }

   }

   public static void setDefaultProxy(Proxy proxy) {
      accounts.put("", proxy);
   }

   public static void saveConfig() {
      try {
         if (!CONFIG_DIR.exists()) {
            CONFIG_DIR.mkdirs();
         }

         JsonElement accountsJsonObject = (new Gson()).toJsonTree(accounts);
         JsonObject configJson = new JsonObject();
         configJson.addProperty("proxy-enabled", ProxyServer.proxyEnabled);
         configJson.add("accounts", accountsJsonObject);
         Gson gsonPretty = (new GsonBuilder()).setPrettyPrinting().create();
         FileUtils.write(CONFIG_FILE, gsonPretty.toJson(configJson), StandardCharsets.UTF_8);
      } catch (IOException e) {
         System.out.println("Error writing Proxyconfig.json file");
         e.printStackTrace();
      }

   }

   static {
      CONFIG_DIR = new File(class_310.method_1551().field_1697, "Yanderov/Proxy");
      CONFIG_FILE = new File(CONFIG_DIR, "Proxyconfig.json");
      accounts = new HashMap();
      lastPlayerName = "";
   }
}

