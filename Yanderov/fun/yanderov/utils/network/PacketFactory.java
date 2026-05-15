package fun.Yanderov.utils.network;

import fun.Yanderov.utils.display.interfaces.QuickImports;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.class_1703;
import net.minecraft.class_1713;
import net.minecraft.class_1799;
import net.minecraft.class_2813;

public class PacketFactory implements QuickImports {
   public static class_2813 createClickSlotC2SPacket(int syncId, int slotId, int buttonId, class_1713 actionType, class_1799 stack, Int2ObjectMap modifiedSlots) {
      if (mc.field_1724 != null && mc.field_1724.field_7512 != null) {
         class_1703 screenHandler = mc.field_1724.field_7512;
         int revision = screenHandler.method_37421();
         return new class_2813(syncId, revision, slotId, buttonId, actionType, stack, modifiedSlots);
      } else {
         return new class_2813(syncId, 0, slotId, buttonId, actionType, stack, modifiedSlots);
      }
   }
}

