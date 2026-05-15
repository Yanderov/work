package fun.Yanderov.features.impl.misc;

import fun.Yanderov.events.player.TickEvent;
import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.features.module.setting.Setting;
import fun.Yanderov.features.module.setting.implement.MultiSelectSetting;
import fun.Yanderov.features.module.setting.implement.SelectSetting;
import fun.Yanderov.utils.client.chat.ChatMessage;
import fun.Yanderov.utils.client.managers.event.EventHandler;
import fun.Yanderov.utils.math.time.TimerUtil;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;
import net.minecraft.class_2561;
import net.minecraft.class_2797;
import net.minecraft.class_640;
import net.minecraft.class_7469;
import net.minecraft.class_7635;

public class TabParser extends Module {
   SelectSetting versionSelect = (new SelectSetting("Ð’ÐµÑ€ÑÐ¸Ñ", "1.16.5")).value("1.16.5", "1.21.4");
   MultiSelectSetting donateSelect = (new MultiSelectSetting("Ð”Ð¾Ð½Ð°Ñ‚ Ð¿Ñ€ÐµÑ„Ð¸ÐºÑÑ‹", "")).value("Ð“ÐµÑ€Ñ†Ð¾Ð³", "ÐšÐ½ÑÐ·ÑŒ", "ÐŸÑ€Ð¸Ð½Ñ†", "Ð¢Ð¸Ñ‚Ð°Ð½", "Ð­Ð»Ð¸Ñ‚Ð°", "Ð“Ð»Ð°Ð²Ð°");
   List anarchyServers165 = new ArrayList();
   List anarchyServers214 = new ArrayList();
   Map parsedPlayers = new ConcurrentHashMap();
   Map initialPlayers = new ConcurrentHashMap();
   TimerUtil switchTimer = TimerUtil.create();
   TimerUtil scanTimer = TimerUtil.create();
   int currentServerIndex = 0;
   boolean parsing = false;
   boolean waitingForServerLoad = false;
   static final Pattern NAME_PATTERN = Pattern.compile("^\\w{3,16}$");

   public TabParser() {
      super("Tab Parser", "Tab Parser", ModuleCategory.MISC);
      this.setup(new Setting[]{this.versionSelect, this.donateSelect});
      this.anarchyServers165.addAll(List.of("/an102", "/an103", "/an104", "/an105", "/an106", "/an107"));

      for(int i = 203; i <= 221; ++i) {
         this.anarchyServers165.add("/an" + i);
      }

      for(int i = 302; i <= 313; ++i) {
         this.anarchyServers165.add("/an" + i);
      }

      this.anarchyServers165.addAll(List.of("/an502", "/an503", "/an504", "/an505", "/an506", "/an507", "/an602"));
      this.anarchyServers214.addAll(List.of("/an11", "/an12", "/an21", "/an23", "/an31", "/an32", "/an51", "/an52"));
   }

   public void activate() {
      super.activate();
      this.loadExistingData();
      this.saveInitialState();
      this.currentServerIndex = 0;
      this.parsing = true;
      this.waitingForServerLoad = false;
      this.switchTimer.resetCounter();
      this.scanTimer.resetCounter();

      for(String donate : this.donateSelect.getSelected()) {
         if (!this.parsedPlayers.containsKey(donate)) {
            this.parsedPlayers.put(donate, ConcurrentHashMap.newKeySet());
         }
      }

      this.switchToNextServer();
   }

   public void deactivate() {
      super.deactivate();
      this.parsing = false;
      ChatMessage.brandmessage("ÐŸÐ°Ñ€ÑÐ¸Ð½Ð³ Ð¾ÑÑ‚Ð°Ð½Ð¾Ð²Ð»ÐµÐ½.");
   }

   @EventHandler
   public void onTick(TickEvent e) {
      if (mc.field_1724 != null && mc.field_1687 != null && this.parsing) {
         if (this.waitingForServerLoad) {
            if (this.scanTimer.hasTimeElapsed(3000L)) {
               this.waitingForServerLoad = false;
               this.scanTimer.resetCounter();
            }

         } else {
            if (this.scanTimer.hasTimeElapsed(2000L)) {
               this.scanCurrentServer();
               this.scanTimer.resetCounter();
            }

            if (this.switchTimer.hasTimeElapsed(5000L)) {
               this.switchToNextServer();
               this.switchTimer.resetCounter();
            }

         }
      }
   }

   private void saveInitialState() {
      this.initialPlayers.clear();

      for(Map.Entry entry : this.parsedPlayers.entrySet()) {
         this.initialPlayers.put((String)entry.getKey(), new HashSet((Collection)entry.getValue()));
      }

   }

   private int calculateNewPlayers() {
      int newCount = 0;

      for(Map.Entry entry : this.parsedPlayers.entrySet()) {
         String donate = (String)entry.getKey();
         Set<String> currentPlayers = (Set)entry.getValue();
         Set<String> oldPlayers = (Set)this.initialPlayers.getOrDefault(donate, Collections.emptySet());

         for(String player : currentPlayers) {
            if (!oldPlayers.contains(player)) {
               ++newCount;
            }
         }
      }

      return newCount;
   }

   private void loadExistingData() {
      this.parsedPlayers.clear();

      for(String donate : this.donateSelect.getSelected()) {
         this.parsedPlayers.put(donate, ConcurrentHashMap.newKeySet());
      }

      File outputFile = getOutputFile();
      if (outputFile.exists()) {
         try {
            BufferedReader reader = new BufferedReader(new FileReader(outputFile));

            try {
               String currentDonate = null;
               int loadedCount = 0;

               String line;
               while((line = reader.readLine()) != null) {
                  line = line.trim();
                  if (line.startsWith("====") && line.contains("Ð´Ð¾Ð½Ð°Ñ‚Ð¾Ð¼")) {
                     String[] parts = line.split("Ð´Ð¾Ð½Ð°Ñ‚Ð¾Ð¼");
                     if (parts.length > 1) {
                        currentDonate = parts[1].replace("====", "").trim();
                        if (!this.parsedPlayers.containsKey(currentDonate)) {
                           this.parsedPlayers.put(currentDonate, ConcurrentHashMap.newKeySet());
                        }
                     }
                  } else if (!line.isEmpty() && currentDonate != null && NAME_PATTERN.matcher(line).matches()) {
                     ((Set)this.parsedPlayers.get(currentDonate)).add(line);
                     ++loadedCount;
                  }
               }

               ChatMessage.brandmessage("Ð—Ð°Ð³Ñ€ÑƒÐ¶ÐµÐ½Ð¾ " + loadedCount + " ÑÑƒÑ‰ÐµÑÑ‚Ð²ÑƒÑŽÑ‰Ð¸Ñ… Ð·Ð°Ð¿Ð¸ÑÐµÐ¹");
            } catch (Throwable var8) {
               try {
                  reader.close();
               } catch (Throwable var7) {
                  var8.addSuppressed(var7);
               }

               throw var8;
            }

            reader.close();
         } catch (IOException e) {
            ChatMessage.brandmessage("ÐžÑˆÐ¸Ð±ÐºÐ° Ð¿Ñ€Ð¸ Ð·Ð°Ð³Ñ€ÑƒÐ·ÐºÐµ Ð´Ð°Ð½Ð½Ñ‹Ñ…: " + e.getMessage());
         }

      }
   }

   private void scanCurrentServer() {
      if (mc.method_1562() != null && mc.field_1687 != null) {
         Collection<class_640> playerList = mc.method_1562().method_2880();
         if (playerList != null && !playerList.isEmpty()) {
            for(class_640 entry : playerList) {
               String name = entry.method_2966().getName();
               if (NAME_PATTERN.matcher(name).matches()) {
                  class_2561 displayName = entry.method_2971();
                  if (displayName != null) {
                     String displayText = displayName.getString().toLowerCase();

                     for(String donate : this.donateSelect.getSelected()) {
                        String donateLower = donate.toLowerCase();
                        if (displayText.contains(donateLower)) {
                           Set<String> players = (Set)this.parsedPlayers.get(donate);
                           if (players != null) {
                              players.add(name);
                           }
                           break;
                        }
                     }
                  }
               }
            }

         }
      }
   }

   private void switchToNextServer() {
      List<String> servers = this.versionSelect.isSelected("1.21.4") ? this.anarchyServers214 : this.anarchyServers165;
      if (this.currentServerIndex >= servers.size()) {
         this.parsing = false;
         int newPlayers = this.calculateNewPlayers();
         this.saveToFile();
         ChatMessage.brandmessage("Ð£ÑÐ¿ÐµÑˆÐ½Ð¾ ÑÐ¿Ð°Ñ€ÑÐ¸Ð» Ð²ÑÐµ Ð°Ð½Ð°Ñ€Ñ…Ð¸Ð¸!");
         if (newPlayers > 0) {
            ChatMessage.brandmessage("Ð‘Ñ‹Ð»Ð¾ Ð·Ð°Ð¿Ð¸ÑÐ°Ð½Ð¾ " + newPlayers + " Ð½Ð¾Ð²Ñ‹Ñ… Ð½Ð¸ÐºÐ¾Ð²");
         }

         ChatMessage.brandmessage("Ð§Ñ‚Ð¾Ð±Ñ‹ Ð¾Ñ‚ÐºÑ€Ñ‹Ñ‚ÑŒ Ñ„Ð°Ð¹Ð» Ñ Ð½Ð¸ÐºÐ½ÐµÐ¹Ð¼Ð°Ð¼Ð¸ Ð²Ð²ÐµÐ´Ð¸Ñ‚Ðµ .tabparser dir");
         this.setState(false);
      } else {
         String nextServer = (String)servers.get(this.currentServerIndex);
         ++this.currentServerIndex;
         ChatMessage.brandmessage("ÐŸÐµÑ€ÐµÐºÐ»ÑŽÑ‡Ð°ÑŽÑÑŒ Ð½Ð° ÑÐµÑ€Ð²ÐµÑ€: " + nextServer + " (" + this.currentServerIndex + "/" + servers.size() + ")");
         if (mc.field_1724 != null && mc.field_1724.field_3944 != null) {
            mc.field_1724.field_3944.method_52787(new class_2797(nextServer, Instant.now(), 0L, (class_7469)null, new class_7635.class_7636(0, new BitSet())));
            this.waitingForServerLoad = true;
            this.scanTimer.resetCounter();
         }

      }
   }

   private void saveToFile() {
      File outputFile = getOutputFile();

      try {
         outputFile.getParentFile().mkdirs();
         BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile, false));

         try {
            List<String> orderedDonates = Arrays.asList("Ð“ÐµÑ€Ñ†Ð¾Ð³", "ÐšÐ½ÑÐ·ÑŒ", "ÐŸÑ€Ð¸Ð½Ñ†", "Ð¢Ð¸Ñ‚Ð°Ð½", "Ð­Ð»Ð¸Ñ‚Ð°", "Ð“Ð»Ð°Ð²Ð°");
            int totalPlayers = 0;

            for(String donate : orderedDonates) {
               if (this.parsedPlayers.containsKey(donate)) {
                  Set<String> players = (Set)this.parsedPlayers.get(donate);
                  if (players != null && !players.isEmpty()) {
                     writer.write("==== ÐÐºÐºÐ°ÑƒÐ½Ñ‚Ñ‹ Ñ Ð´Ð¾Ð½Ð°Ñ‚Ð¾Ð¼ " + donate + " ====");
                     writer.newLine();
                     List<String> sortedPlayers = new ArrayList(players);
                     Collections.sort(sortedPlayers);

                     for(String player : sortedPlayers) {
                        writer.write(player);
                        writer.newLine();
                     }

                     writer.newLine();
                     totalPlayers += players.size();
                  }
               }
            }

            writer.flush();
            ChatMessage.brandmessage("Ð¡Ð¾Ñ…Ñ€Ð°Ð½ÐµÐ½Ð¾ " + totalPlayers + " Ð¸Ð³Ñ€Ð¾ÐºÐ¾Ð² Ð² Ñ„Ð°Ð¹Ð»");
         } catch (Throwable var12) {
            try {
               writer.close();
            } catch (Throwable var11) {
               var12.addSuppressed(var11);
            }

            throw var12;
         }

         writer.close();
      } catch (IOException e) {
         ChatMessage.brandmessage("ÐžÑˆÐ¸Ð±ÐºÐ° Ð¿Ñ€Ð¸ ÑÐ¾Ñ…Ñ€Ð°Ð½ÐµÐ½Ð¸Ð¸ Ñ„Ð°Ð¹Ð»Ð°: " + e.getMessage());
         e.printStackTrace();
      }

   }

   public static File getOutputFile() {
      return new File(mc.field_1697, "tabparser_results.txt");
   }
}

