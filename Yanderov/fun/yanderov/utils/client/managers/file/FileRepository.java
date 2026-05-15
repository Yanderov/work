package fun.Yanderov.utils.client.managers.file;

import fun.Yanderov.Yanderov;
import fun.Yanderov.utils.client.managers.file.impl.BlockESPFile;
import fun.Yanderov.utils.client.managers.file.impl.EntityESPFile;
import fun.Yanderov.utils.client.managers.file.impl.FriendFile;
import fun.Yanderov.utils.client.managers.file.impl.MacroFile;
import fun.Yanderov.utils.client.managers.file.impl.ModuleFile;
import fun.Yanderov.utils.client.managers.file.impl.PrefixFile;
import fun.Yanderov.utils.client.managers.file.impl.ProxyFile;
import fun.Yanderov.utils.client.managers.file.impl.StaffFile;
import fun.Yanderov.utils.client.managers.file.impl.WayFile;
import java.util.ArrayList;
import java.util.List;

public class FileRepository {
   private final List clientFiles = new ArrayList();

   public void setup(Yanderov main) {
      this.register(new ModuleFile(main.getModuleRepository(), main.getDraggableRepository()), new EntityESPFile(main.getBoxESPRepository()), new BlockESPFile(main.getBoxESPRepository()), new MacroFile(main.getMacroRepository()), new WayFile(main.getWayRepository()), new PrefixFile(), new FriendFile(), new StaffFile(), new ProxyFile());
   }

   public void register(ClientFile... clientFIle) {
      this.clientFiles.addAll(List.of(clientFIle));
   }

   public List getClientFiles() {
      return this.clientFiles;
   }
}

