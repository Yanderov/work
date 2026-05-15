package dev.client.yanderov.common.localization;

import com.google.gson.reflect.TypeToken;
import dev.client.yanderov.utils.display.interfaces.QuickImports;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.class_2960;
import net.minecraft.class_3298;

public class Localization implements QuickImports {
   private static final Map cache = new ConcurrentHashMap();

   public static String get(String key) {
      Map<String, String> translations = (Map)cache.computeIfAbsent(Language.ENG, Localization::loadTranslations);
      return (String)translations.getOrDefault(key, key);
   }

   private static Map loadTranslations(Language language) {
      try {
         class_2960 identifier = class_2960.method_60654("translations/" + language.getFile() + ".json");
         InputStream stream = ((class_3298)mc.method_1478().method_14486(identifier).get()).method_14482();
         BufferedReader reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8));
         return (Map)gson.fromJson(reader, (new TypeToken() {
         }).getType());
      } catch (Throwable $ex) {
         throw $ex;
      }
   }
}

