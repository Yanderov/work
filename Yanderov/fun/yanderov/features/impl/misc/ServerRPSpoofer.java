package fun.Yanderov.features.impl.misc;

import antidaunleak.api.annotation.Native;
import fun.Yanderov.events.packet.PacketEvent;
import fun.Yanderov.events.player.TickEvent;
import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.utils.client.managers.event.EventHandler;
import fun.Yanderov.utils.math.time.TimerUtil;
import net.minecraft.class_2720;
import net.minecraft.class_2856;
import net.minecraft.class_634;
import net.minecraft.class_2856.class_2857;

public class ServerRPSpoofer extends Module {
   private ResourcePackAction currentAction;
   private final TimerUtil counter;

   public ServerRPSpoofer() {
      super("ServerRPSpoof", "Server RP Spoof", ModuleCategory.MISC);
      this.currentAction = ServerRPSpoofer.ResourcePackAction.WAIT;
      this.counter = TimerUtil.create();
   }

   @EventHandler
   public void onPacket(PacketEvent e) {
      if (e.getPacket() instanceof class_2720) {
         this.currentAction = ServerRPSpoofer.ResourcePackAction.ACCEPT;
         e.cancel();
      }

   }

   @EventHandler
   @Native(
      type = Native.Type.VMProtectBeginMutation
   )
   public void onTick(TickEvent e) {
      class_634 networkHandler = mc.method_1562();
      if (networkHandler != null) {
         if (this.currentAction == ServerRPSpoofer.ResourcePackAction.ACCEPT) {
            networkHandler.method_52787(new class_2856(mc.field_1724.method_5667(), class_2857.field_13016));
            this.currentAction = ServerRPSpoofer.ResourcePackAction.SEND;
            this.counter.resetCounter();
         } else if (this.currentAction == ServerRPSpoofer.ResourcePackAction.SEND && this.counter.isReached(300L)) {
            networkHandler.method_52787(new class_2856(mc.field_1724.method_5667(), class_2857.field_13017));
            this.currentAction = ServerRPSpoofer.ResourcePackAction.WAIT;
         }
      }

   }

   public void deactivate() {
      this.currentAction = ServerRPSpoofer.ResourcePackAction.WAIT;
      super.deactivate();
   }

   public static enum ResourcePackAction {
      ACCEPT,
      SEND,
      WAIT;

      // $FF: synthetic method
      private static ResourcePackAction[] $values() {
         return new ResourcePackAction[]{ACCEPT, SEND, WAIT};
      }
   }
}

