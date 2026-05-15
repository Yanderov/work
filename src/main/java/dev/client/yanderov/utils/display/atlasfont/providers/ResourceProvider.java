package dev.client.yanderov.utils.display.atlasfont.providers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_3300;

public final class ResourceProvider {
   private static final class_3300 RESOURCE_MANAGER = class_310.method_1551().method_1478();
   private static final Gson GSON = new Gson();

   public static class_2960 getShaderIdentifier(String name) {
      return class_2960.method_60655("mre", "core/" + name);
   }

   public static JsonObject toJson(class_2960 identifier) {
      return JsonParser.parseString(toString(identifier)).getAsJsonObject();
   }

   public static Object fromJsonToInstance(class_2960 identifier, Class clazz) {
      return GSON.fromJson(toString(identifier), clazz);
   }

   public static String toString(class_2960 identifier) {
      return toString(identifier, "\n");
   }

   public static String toString(class_2960 identifier, String delimiter) {
      try {
         InputStream inputStream = RESOURCE_MANAGER.open(identifier);

         String var4;
         try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            try {
               var4 = (String)reader.lines().collect(Collectors.joining(delimiter));
            } catch (Throwable var8) {
               try {
                  reader.close();
               } catch (Throwable var7) {
                  var8.addSuppressed(var7);
               }

               throw var8;
            }

            reader.close();
         } catch (Throwable var9) {
            if (inputStream != null) {
               try {
                  inputStream.close();
               } catch (Throwable var6) {
                  var9.addSuppressed(var6);
               }
            }

            throw var9;
         }

         if (inputStream != null) {
            inputStream.close();
         }

         return var4;
      } catch (IOException ex) {
         throw new RuntimeException(ex);
      }
   }
}

