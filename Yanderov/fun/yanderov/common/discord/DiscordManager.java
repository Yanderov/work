package fun.Yanderov.common.discord;

import antidaunleak.api.UserProfile;
import fun.Yanderov.Yanderov;
import fun.Yanderov.common.discord.utils.DiscordEventHandlers;
import fun.Yanderov.common.discord.utils.DiscordYanderovPresence;
import fun.Yanderov.common.discord.utils.DiscordRPC;
import fun.Yanderov.common.discord.utils.RPCButton;
import fun.Yanderov.utils.client.discord.Buffer;
import fun.Yanderov.utils.display.interfaces.QuickImports;
import java.io.IOException;
import net.minecraft.class_2960;

public class DiscordManager implements QuickImports {
   private final DiscordDaemonThread discordDaemonThread = new DiscordDaemonThread();
   private boolean running = true;
   private DiscordInfo info = new DiscordInfo("Unknown", "", "");
   private class_2960 avatarId;

   public void init() {
      String os = System.getProperty("os.name").toLowerCase();
      if (!os.contains("linux")) {
         DiscordEventHandlers handlers = (new DiscordEventHandlers.Builder()).ready((user) -> {
            Yanderov.getInstance().getDiscordManager().setInfo(new DiscordInfo(user.username, "https://cdn.discordapp.com/avatars/" + user.userId + "/" + user.avatar + ".png", user.userId));
            DiscordYanderovPresence.Builder var10000 = (new DiscordYanderovPresence.Builder()).setStartTimestamp(System.currentTimeMillis() / 1000L);
            UserProfile var10001 = UserProfile.getInstance();
            var10000 = var10000.setDetails("User: " + var10001.profile("username"));
            var10001 = UserProfile.getInstance();
            DiscordYanderovPresence YanderovPresence = var10000.setState("Uid: " + var10001.profile("uid")).setLargeImage("https://i.postimg.cc/nznMWbhM/0001-0250.gif", "https://t.me/YanderovDLC").setSmallImage(Yanderov.getInstance().getDiscordManager().getInfo().avatarUrl, "https://t.me/YanderovDLC").setButtons(RPCButton.create("Ð¢ÐµÐ»ÐµÐ³Ñ€Ð°Ð¼", "https://t.me/YanderovDLC")).build();
            DiscordRPC.INSTANCE.Discord_UpdatePresence(YanderovPresence);
         }).build();
         DiscordRPC.INSTANCE.Discord_Initialize("1419653405265105021", handlers, true, "");
         this.discordDaemonThread.start();
      }
   }

   public void stopRPC() {
      DiscordRPC.INSTANCE.Discord_Shutdown();
      this.running = false;
   }

   public void load() throws IOException {
      if (this.avatarId == null && !this.info.avatarUrl.isEmpty()) {
         this.avatarId = Buffer.registerDynamicTexture("avatar-", Buffer.getHeadFromURL(this.info.avatarUrl));
      }

   }

   public class_2960 getAvatarId() {
      return this.avatarId;
   }

   public void setRunning(boolean running) {
      this.running = running;
   }

   public void setInfo(DiscordInfo info) {
      this.info = info;
   }

   public void setAvatarId(class_2960 avatarId) {
      this.avatarId = avatarId;
   }

   public DiscordDaemonThread getDiscordDaemonThread() {
      return this.discordDaemonThread;
   }

   public boolean isRunning() {
      return this.running;
   }

   public DiscordInfo getInfo() {
      return this.info;
   }

   private class DiscordDaemonThread extends Thread {
      public void run() {
         this.setName("Discord-RPC");

         try {
            while(Yanderov.getInstance().getDiscordManager().isRunning()) {
               DiscordRPC.INSTANCE.Discord_RunCallbacks();
               DiscordManager.this.load();
               Thread.sleep(15000L);
            }
         } catch (Exception var2) {
            DiscordManager.this.stopRPC();
         }

         super.run();
      }
   }

   public static record DiscordInfo(String userName, String avatarUrl, String userId) {
   }
}

