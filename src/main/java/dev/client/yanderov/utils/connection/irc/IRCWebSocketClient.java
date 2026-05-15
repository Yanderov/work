package dev.client.yanderov.utils.connection.irc;

import antidaunleak.api.UserProfile;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.client.yanderov.Yanderov;
import dev.client.yanderov.commands.defaults.IRCCommand;
import dev.client.yanderov.utils.client.chat.ChatMessage;
import dev.client.yanderov.utils.client.text.TextHelper;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;
import net.minecraft.class_124;
import net.minecraft.class_2561;
import net.minecraft.class_2583;
import net.minecraft.class_310;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

public class IRCWebSocketClient extends WebSocketClient {
   private boolean isMuted = false;
   private final String clientId = UserProfile.getInstance().profile("uid");
   private final Set processedMessages = new HashSet();

   public boolean isMuted() {
      return this.isMuted;
   }

   public IRCWebSocketClient(URI serverUri) {
      super(serverUri);
      this.addHeader("Sec-WebSocket-Key", this.clientId);
   }

   public void onOpen(ServerHandshake handshakedata) {
      this.sendGetPrefix();
      YanderovIntegration.getInstance().getIrcManager().isConnecting.set(false);
   }

   public void onMessage(String message) {
      String decodedMessage = this.cypher(message);
      if (this.processedMessages.add(decodedMessage)) {
         this.handleMessage(decodedMessage);
      }

   }

   private void handleMessage(String jsonMessage) {
      try {
         JsonObject json = JsonParser.parseString(jsonMessage).getAsJsonObject();
         if (!json.has("type")) {
            return;
         }

         String type = json.get("type").getAsString();
         if (type.equals("text")) {
            if (!json.has("message") || !json.has("author") || !json.has("prefix")) {
               return;
            }

            String author = json.get("author").getAsString();
            String content = json.get("message").getAsString();
            String prefix = json.get("prefix").getAsString();
            String formattedMessage = author + " -> " + content;
            if (YanderovIntegration.getInstance().isShowIrcMessages()) {
               this.displayMessage(prefix, formattedMessage, author, content);
            }
         } else if (!type.equals("mute") && !type.equals("mute_attempt")) {
            if (type.equals("unmute")) {
               this.isMuted = false;
               if (YanderovIntegration.getInstance().isShowIrcMessages()) {
                  ChatMessage.ircmessageWithGreen("Ð’Ñ‹ Ñ€Ð°Ð·Ð¼ÑƒÑ‡ÐµÐ½Ñ‹!");
               }
            } else if (type.equals("prefix_info")) {
               String prefix = json.get("prefix").getAsString();
               IRCCommand.setSelectedPrefix(prefix.isEmpty() ? null : prefix);
            } else if (type.equals("prefix_updated")) {
               String prefix = json.get("prefix").getAsString();
               IRCCommand.setSelectedPrefix(prefix.isEmpty() ? null : prefix);
            } else if (type.equals("system")) {
               if (!json.has("message")) {
                  return;
               }

               String systemMessage = json.get("message").getAsString();
               if (YanderovIntegration.getInstance().isShowIrcMessages()) {
                  ChatMessage.ircmessage(systemMessage);
               }
            }
         } else {
            this.isMuted = true;
            if (YanderovIntegration.getInstance().isShowIrcMessages() && json.has("reason") && json.has("duration_minutes")) {
               String reason = json.get("reason").getAsString();
               int duration = json.get("duration_minutes").getAsInt();
               String reasonTranslated = reason.equals("Ð¡Ð¿Ð°Ð¼") ? "ÑÐ¿Ð°Ð¼" : (reason.equals("ÐœÐ°Ñ‚") ? "Ð¼Ð°Ñ‚" : (reason.equals("ÐšÐ°Ð¿Ñ") ? "Ð·Ð»Ð¾ÑƒÐ¿Ð¾Ñ‚Ñ€ÐµÐ±Ð»ÐµÐ½Ð¸Ðµ ÐºÐ°Ð¿ÑÐ¾Ð¼ Ð¸Ð»Ð¸ ÑÐ¸Ð¼Ð²Ð¾Ð»Ð°Ð¼Ð¸" : "Ð¾Ñ‚Ð¿Ñ€Ð°Ð²ÐºÑƒ ÑÑÑ‹Ð»Ð¾Ðº"));
               ChatMessage.ircmessageWithRed("Ð’Ñ‹ Ð·Ð°Ð¼ÑƒÑ‡ÐµÐ½Ñ‹ Ð·Ð° " + reasonTranslated + " Ð½Ð° " + duration + " Ð¼Ð¸Ð½ÑƒÑ‚!");
            }
         }
      } catch (Exception var8) {
      }

   }

   void displayMessage(String prefix, String message, String author, String content) {
      if (class_310.method_1551().field_1724 != null && YanderovIntegration.getInstance().isShowIrcMessages()) {
         class_2561 prefixText;
         if (author.equals("HZeed") && author.equals("Silv4ik") && author.equals("Raze")) {
            prefixText = ChatMessage.ircprefixDeveloper("");
         } else {
            switch (prefix) {
               case "pikmi" -> prefixText = ChatMessage.ircprefixPikmi("");
               case "labuba" -> prefixText = ChatMessage.ircprefixLabuba("");
               case "zapen" -> prefixText = ChatMessage.ircprefixZapen("");
               case "boost" -> prefixText = ChatMessage.ircprefixBoost("");
               case "Yanderov" -> prefixText = ChatMessage.ircprefixYanderov("");
               case "panda" -> prefixText = ChatMessage.ircprefixPanda("");
               case "smiley" -> prefixText = ChatMessage.ircprefixSmiley("");
               case "bibi" -> prefixText = ChatMessage.ircprefixBibi("");
               case "benena" -> prefixText = ChatMessage.ircprefixBenena("");
               case "blyabuba" -> prefixText = ChatMessage.ircprefixBlyabuba("");
               default -> prefixText = class_2561.method_43470("");
            }
         }

         class_2561 ircPrefix = TextHelper.applyPredefinedGradient("[IRC] ", "black_light_purple", true);
         class_2561 username = class_2561.method_43470(author + " ").method_10862(class_2583.field_24360.method_10977(class_124.field_1068));
         class_2561 arrow = class_2561.method_43470("-> ").method_10862(class_2583.field_24360.method_10977(class_124.field_1080));
         class_2561 contentText = class_2561.method_43470(content).method_10862(class_2583.field_24360.method_10977(class_124.field_1068));
         class_2561 formattedText = ircPrefix.method_27661().method_10852(prefixText).method_10852(username).method_10852(arrow).method_10852(contentText);
         class_310.method_1551().field_1724.method_7353(formattedText, false);
      }
   }

   public void onClose(int code, String reason, boolean remote) {
      YanderovIntegration.getInstance().getIrcManager().isConnecting.set(false);
   }

   public void onError(Exception ex) {
      if (YanderovIntegration.getInstance().isShowIrcMessages()) {
         ChatMessage.ircmessageWithRed("ÐžÑˆÐ¸Ð±ÐºÐ° Ð¿Ð¾Ð´ÐºÐ»ÑŽÑ‡ÐµÐ½Ð¸Ñ Ðº IRC");
      }

      YanderovIntegration.getInstance().getIrcManager().isConnecting.set(false);
   }

   public void sendMessage(String message) {
      if (this.isOpen()) {
         if (this.isMuted) {
            if (YanderovIntegration.getInstance().isShowIrcMessages()) {
               ChatMessage.ircmessageWithRed("Ð’Ñ‹ Ð·Ð°Ð¼ÑƒÑ‡ÐµÐ½Ñ‹ Ð¸ Ð½Ðµ Ð¼Ð¾Ð¶ÐµÑ‚Ðµ Ð¾Ñ‚Ð¿Ñ€Ð°Ð²Ð»ÑÑ‚ÑŒ ÑÐ¾Ð¾Ð±Ñ‰ÐµÐ½Ð¸Ñ");
            }

         } else {
            String sender = UserProfile.getInstance().profile("username");
            JsonObject json = new JsonObject();
            json.addProperty("type", "text");
            json.addProperty("message", message);
            json.addProperty("author", sender);
            json.addProperty("clientId", this.clientId);
            this.send(this.cypher(json.toString()));
         }
      }
   }

   public void sendSetPrefix(String newPrefix) {
      JsonObject json = new JsonObject();
      json.addProperty("type", "set_prefix");
      json.addProperty("new_prefix", newPrefix);
      json.addProperty("clientId", this.clientId);
      json.addProperty("author", UserProfile.getInstance().profile("username"));
      this.send(this.cypher(json.toString()));
   }

   private void sendGetPrefix() {
      JsonObject json = new JsonObject();
      json.addProperty("type", "get_prefix");
      json.addProperty("clientId", this.clientId);
      this.send(this.cypher(json.toString()));
   }

   private String cypher(String input) {
      byte[] bytes = input.getBytes(StandardCharsets.UTF_8);

      for(int i = 0; i < bytes.length; ++i) {
         bytes[i] = (byte)(bytes[i] ^ 21);
      }

      return new String(bytes, StandardCharsets.UTF_8);
   }
}

