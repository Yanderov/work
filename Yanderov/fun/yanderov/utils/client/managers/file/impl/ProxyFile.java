package fun.Yanderov.utils.client.managers.file.impl;

import fun.Yanderov.common.proxy.Config;
import fun.Yanderov.utils.client.managers.file.ClientFile;
import fun.Yanderov.utils.client.managers.file.exception.FileLoadException;
import fun.Yanderov.utils.client.managers.file.exception.FileSaveException;
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

