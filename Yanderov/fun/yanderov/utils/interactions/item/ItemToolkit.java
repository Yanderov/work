package fun.Yanderov.utils.interactions.item;

import fun.Yanderov.Yanderov;
import fun.Yanderov.events.packet.PacketEvent;
import fun.Yanderov.utils.client.managers.event.EventHandler;
import fun.Yanderov.utils.display.interfaces.QuickImports;
import java.util.Objects;
import net.minecraft.class_1268;
import net.minecraft.class_2596;
import net.minecraft.class_2678;
import net.minecraft.class_2724;
import net.minecraft.class_2799;
import net.minecraft.class_2846;
import net.minecraft.class_2799.class_2800;
import net.minecraft.class_2846.class_2847;

public class ItemToolkit implements QuickImports {
   public static final ItemToolkit INSTANCE = new ItemToolkit();
   public boolean useItem;
   public boolean releaseItem = true;

   public ItemToolkit() {
      Yanderov.getInstance().getEventManager().register(this);
   }

   @EventHandler
   public void onPacket(PacketEvent e) {
      class_2596 var10000 = e.getPacket();
      Objects.requireNonNull(var10000);
      class_2596 var2 = var10000;
      byte var3 = 0;

      while(true) {
         //$FF: var3->value
         //0->net/minecraft/class_2846
         //1->net/minecraft/class_2799
         //2->net/minecraft/class_2724
         //3->net/minecraft/class_2678
         switch (var2.typeSwitch<invokedynamic>(var2, var3)) {
            case 0:
               class_2846 player = (class_2846)var2;
               if (!player.method_12363().equals(class_2847.field_12974)) {
                  var3 = 1;
                  break;
               }

               this.releaseItem = true;
               return;
            case 1:
               class_2799 status = (class_2799)var2;
               if (!status.method_12119().equals(class_2800.field_12774)) {
                  var3 = 2;
                  break;
               }

               this.releaseItem = true;
               return;
            case 2:
               class_2724 respawn = (class_2724)var2;
               this.releaseItem = true;
               return;
            case 3:
               class_2678 join = (class_2678)var2;
               this.releaseItem = true;
               return;
            default:
               return;
         }
      }
   }

   public void useHand(class_1268 hand) {
      if (this.releaseItem) {
         mc.field_1761.method_2919(mc.field_1724, hand);
         this.releaseItem = false;
      }

      this.useItem = true;
   }

   public void setUseItem(boolean useItem) {
      this.useItem = useItem;
   }

   public void setReleaseItem(boolean releaseItem) {
      this.releaseItem = releaseItem;
   }

   public boolean isUseItem() {
      return this.useItem;
   }

   public boolean isReleaseItem() {
      return this.releaseItem;
   }
}

