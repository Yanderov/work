package dev.client.yanderov.utils.client.managers.file.impl;

import dev.client.yanderov.common.proxy.Config;
import dev.client.yanderov.utils.client.managers.file.ClientFile;
import dev.client.yanderov.utils.client.managers.file.exception.FileLoadException;
import dev.client.yanderov.utils.client.managers.file.exception.FileSaveException;
import java.io.File;

public class ProxyFile extends ClientFile {
   public ProxyFile() {
      super("Proxy/Proxyconfig");
   }

   public void saveToFile(File path) throws FileSaveException {
      Config.saveConfig();
   }

   public void loadFromFile(File path) throws FileLoadException {
      Config.loadConfig();
   }
}

