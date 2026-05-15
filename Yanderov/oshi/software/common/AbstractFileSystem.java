package oshi.software.common;

import java.util.Arrays;
import java.util.List;
import oshi.annotation.concurrent.ThreadSafe;
import oshi.software.os.FileSystem;
import oshi.util.GlobalConfig;

@ThreadSafe
public abstract class AbstractFileSystem implements FileSystem {
   protected static final List NETWORK_FS_TYPES = Arrays.asList(GlobalConfig.get("oshi.network.filesystem.types", "").split(","));
   protected static final List PSEUDO_FS_TYPES = Arrays.asList(GlobalConfig.get("oshi.pseudo.filesystem.types", "").split(","));

   public List getFileStores() {
      return this.getFileStores(false);
   }
}
