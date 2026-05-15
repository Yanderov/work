package dev.client.yanderov.utils.features.aura.utils;

import dev.client.yanderov.Yanderov;
import dev.client.yanderov.utils.client.packet.network.Network;
import dev.client.yanderov.utils.display.interfaces.QuickImports;
import net.minecraft.class_1799;

public class Pressing implements QuickImports {
   private final int[] funTimeTicks = new int[]{10, 11, 10, 13};
   private final int[] spookyTicks = new int[]{11, 10, 13, 10, 12, 11, 12};
   private final int[] defaultTicks = new int[]{10, 11};
   private long lastClickTime = System.currentTimeMillis();
   private static final long MINIMUM_COOLDOWN_MS = 500L;

   public boolean isCooldownComplete(boolean dynamicCooldown, int ticks) {
      boolean isMace = this.isHoldingMace();
      boolean cooldownReady = isMace || mc.field_1724.method_7261((float)ticks) > 0.9F;
      boolean minimumDelayPassed = this.lastClickPassed() >= 500L;
      return cooldownReady && minimumDelayPassed;
   }

   public boolean hasTicksElapsedSinceLastClick(int ticks) {
      return (float)this.lastClickPassed() >= (float)((long)ticks * 50L) * (20.0F / Network.TPS);
   }

   public long lastClickPassed() {
      return System.currentTimeMillis() - this.lastClickTime;
   }

   public void recalculate() {
      this.lastClickTime = System.currentTimeMillis();
   }

   int tickCount() {
      int count = YanderovIntegration.getInstance().getAttackPerpetrator().getAttackHandler().getCount();
      String var2 = Network.server;
      byte var3 = -1;
      var2.hashCode();
      switch (var3) {
         default -> {
            return this.defaultTicks[count % this.defaultTicks.length];
         }
      }
   }

   private boolean isHoldingMace() {
      class_1799 mainHand = mc.field_1724.method_6047();
      return mainHand.method_7909().method_7876().toLowerCase().contains("mace");
   }
}

