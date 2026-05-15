package fun.Yanderov.main.client;

import fun.Yanderov.utils.client.chat.StringHelper;
import java.io.File;

public record ClientInfo(String clientName, String userName, String role, File clientDir, File filesDir) implements ClientInfoProvider {
   public String getFullInfo() {
      return String.format("Welcome! Client: %s Version: %s Branch: %s", this.clientName, "Baflllik && HZeed", StringHelper.getUserRole());
   }

   public File configsDir() {
      return this.clientDir;
   }
}

