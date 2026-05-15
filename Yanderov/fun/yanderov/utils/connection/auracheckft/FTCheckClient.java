package fun.Yanderov.utils.connection.auracheckft;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import fun.Yanderov.utils.client.chat.ChatMessage;
import java.net.URI;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

public class FTCheckClient extends WebSocketClient {
   private JsonObject config = new JsonObject();
   private long lastWarningTime = 0L;
   private static final long WARNING_INTERVAL = 5000L;
   private boolean connected = false;

   public FTCheckClient(URI serverUri) {
      super(serverUri);
      this.setConnectionLostTimeout(10);
   }

   public void onOpen(ServerHandshake handshake) {
      this.connected = true;

      try {
         this.send("{\"type\":\"getConfig\"}");
      } catch (Exception var3) {
      }

   }

   public void onMessage(String message) {
      try {
         JsonObject data = JsonParser.parseString(message).getAsJsonObject();
         if (data.has("type") && data.get("type").getAsString().equals("config")) {
            this.config = data.getAsJsonObject("data");
         }
      } catch (Exception var3) {
      }

   }

   public void onClose(int code, String reason, boolean remote) {
      this.connected = false;
   }

   public void onError(Exception ex) {
      this.connected = false;
   }

   public boolean isConnected() {
      return this.connected && this.isOpen();
   }

   public boolean isFtFixed() {
      if (!this.isConnected()) {
         return false;
      } else {
         try {
            return this.config.has("ftfixed") && this.config.get("ftfixed").getAsBoolean();
         } catch (Exception var2) {
            return false;
         }
      }
   }

   public void checkAndWarnFunTime() {
      if (this.isConnected()) {
         class_310 mc = class_310.method_1551();
         if (mc.field_1724 != null) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - this.lastWarningTime >= 5000L) {
               try {
                  if (this.isFtFixed()) {
                     class_2561 prefix = ChatMessage.brandmessage();
                     class_2561 message = class_2561.method_43470(" Â» Funtime Ð¸ÑÐ¿Ñ€Ð°Ð²Ð¸Ð» ÐšÐ¸Ð»Ð»Ð°ÑƒÑ€Ñƒ. ÐÐµ Ð¸Ð³Ñ€Ð°Ð¹Ñ‚Ðµ Ñ Ð½ÐµÐ¹, Ð¸Ð½Ð°Ñ‡Ðµ Ð²Ð°Ñ Ð·Ð°Ð±Ð°Ð½ÑÑ‚! ÐœÑ‹ ÑƒÐ¶Ðµ ÑƒÑÐµÑ€Ð´Ð½Ð¾ Ð´ÐµÐ»Ð°ÐµÐ¼ Ð½Ð¾Ð²ÑƒÑŽ â€” Ð¾Ð¶Ð¸Ð´Ð°Ð¹Ñ‚Ðµ.");
                     mc.field_1724.method_7353(prefix.method_27661().method_10852(message), false);
                     this.lastWarningTime = currentTime;
                  }
               } catch (Exception var6) {
               }

            }
         }
      }
   }
}

