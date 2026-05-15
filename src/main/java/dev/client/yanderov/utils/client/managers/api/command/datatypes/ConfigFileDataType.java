package dev.client.yanderov.utils.client.managers.api.command.datatypes;

import dev.client.yanderov.Yanderov;
import dev.client.yanderov.utils.client.managers.api.command.exception.CommandException;
import dev.client.yanderov.utils.client.managers.api.command.helpers.TabCompleteHelper;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public enum ConfigFileDataType implements IDatatypeFor {
   INSTANCE;

   public Stream tabComplete(IDatatypeContext ctx) throws CommandException {
      Stream<String> friends = this.getConfigs().stream().map(String::toString);
      String context = ctx.getConsumer().getString();
      return (new TabCompleteHelper()).append(friends).filterPrefix(context).sortAlphabetically().stream();
   }

   public String get(IDatatypeContext datatypeContext) throws CommandException {
      String username = datatypeContext.getConsumer().getString();
      return (String)this.getConfigs().stream().filter((s) -> s.equalsIgnoreCase(username)).findFirst().orElse((Object)null);
   }

   public List getConfigs() {
      List<String> configs = new ArrayList();
      File customDir = new File(YanderovIntegration.getInstance().getClientInfoProvider().clientDir(), "Custom");
      File[] configFiles = customDir.listFiles();
      if (configFiles != null) {
         for(File configFile : configFiles) {
            if (configFile.isFile() && configFile.getName().endsWith(".json")) {
               String configName = configFile.getName().replace(".json", "");
               configs.add(configName);
            }
         }
      }

      return configs;
   }

   // $FF: synthetic method
   private static ConfigFileDataType[] $values() {
      return new ConfigFileDataType[]{INSTANCE};
   }
}

