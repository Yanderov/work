package fun.Yanderov.utils.connection.tps;

import fun.Yanderov.Yanderov;
import fun.Yanderov.events.packet.PacketEvent;
import fun.Yanderov.utils.client.managers.event.EventHandler;
import net.minecraft.class_2761;
import net.minecraft.class_3532;

public class TPSCalculate {
   private float TPS = 20.0F;
   private float adjustTicks = 0.0F;
   private long timestamp;

   public TPSCalculate() {
      Yanderov.getInstance().getEventManager().register(this);
   }

   @EventHandler
   private void onPacket(PacketEvent e) {
      if (e.getPacket() instanceof class_2761) {
         this.updateTPS();
      }

   }

   private void updateTPS() {
      long delay = System.nanoTime() - this.timestamp;
      float maxTPS = 20.0F;
      float rawTPS = maxTPS * (1.0E9F / (float)delay);
      float boundedTPS = class_3532.method_15363(rawTPS, 0.0F, maxTPS);
      this.TPS = (float)this.round((double)boundedTPS);
      this.adjustTicks = boundedTPS - maxTPS;
      this.timestamp = System.nanoTime();
   }

   public double round(double input) {
      return (double)Math.round(input * (double)100.0F) / (double)100.0F;
   }

   public float getTPS() {
      return this.TPS;
   }

   public float getAdjustTicks() {
      return this.adjustTicks;
   }

   public long getTimestamp() {
      return this.timestamp;
   }
}

