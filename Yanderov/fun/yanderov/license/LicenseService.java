package fun.Yanderov.license;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import net.minecraft.class_156;
import net.minecraft.class_310;
import net.minecraft.class_442;

public class LicenseService {
   private static final String LICENSE_CACHE_PATH = "config/YanderovClient/license.json";
   private static final String PASTEBIN_URL = "https://pastebin.com/raw/HtbeMPB8";
   private static final Gson GSON = new Gson();

   public static boolean isLicenseValid() {
      String cachedKey = getCachedLicenseKey();
      return cachedKey != null ? checkKeyOnline(cachedKey) : false;
   }

   public static boolean validateKey(String key) {
      boolean valid = checkKeyOnline(key);
      if (valid) {
         cacheLicenseKey(key);
      }

      return valid;
   }

   private static String getCachedLicenseKey() {
      File file = new File("config/YanderovClient/license.json");
      if (!file.exists()) {
         return null;
      } else {
         try {
            FileReader reader = new FileReader(file);

            String var3;
            try {
               JsonObject json = (JsonObject)GSON.fromJson(reader, JsonObject.class);
               var3 = json.has("key") ? json.get("key").getAsString() : null;
            } catch (Throwable var5) {
               try {
                  reader.close();
               } catch (Throwable var4) {
                  var5.addSuppressed(var4);
               }

               throw var5;
            }

            reader.close();
            return var3;
         } catch (IOException var6) {
            return null;
         }
      }
   }

   private static void cacheLicenseKey(String key) {
      File file = new File("config/YanderovClient/license.json");
      file.getParentFile().mkdirs();
      JsonObject json = new JsonObject();
      json.addProperty("key", key);

      try {
         FileWriter writer = new FileWriter(file);

         try {
            GSON.toJson(json, writer);
         } catch (Throwable var7) {
            try {
               writer.close();
            } catch (Throwable var6) {
               var7.addSuppressed(var6);
            }

            throw var7;
         }

         writer.close();
      } catch (IOException var8) {
      }

   }

   private static boolean checkKeyOnline(String key) {
      try {
         HttpURLConnection connection = (HttpURLConnection)(new URL("https://pastebin.com/raw/HtbeMPB8")).openConnection();
         connection.setRequestMethod("GET");
         connection.setConnectTimeout(5000);
         connection.setReadTimeout(5000);
         connection.connect();
         if (connection.getResponseCode() == 200) {
            Scanner scanner = new Scanner(connection.getInputStream(), StandardCharsets.UTF_8.name());

            boolean var3;
            label52: {
               try {
                  while(scanner.hasNextLine()) {
                     if (scanner.nextLine().trim().equals(key)) {
                        var3 = true;
                        break label52;
                     }
                  }
               } catch (Throwable var6) {
                  try {
                     scanner.close();
                  } catch (Throwable var5) {
                     var6.addSuppressed(var5);
                  }

                  throw var6;
               }

               scanner.close();
               return false;
            }

            scanner.close();
            return var3;
         }
      } catch (IOException var7) {
      }

      return false;
   }

   public static void showLicenseScreenIfNeeded() {
      class_310 client = class_310.method_1551();
      class_156.method_18349().execute(() -> {
         if (!isLicenseValid()) {
            client.method_1507(new LicenseScreen(new class_442()));
         }

      });
   }
}

