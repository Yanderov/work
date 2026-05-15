package dev.client.yanderov.features.impl.player;

import dev.client.yanderov.events.packet.PacketEvent;
import dev.client.yanderov.events.player.TickEvent;
import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.features.module.ModuleCategory;
import dev.client.yanderov.features.module.setting.Setting;
import dev.client.yanderov.features.module.setting.implement.BooleanSetting;
import dev.client.yanderov.features.module.setting.implement.SliderSettings;
import dev.client.yanderov.utils.client.managers.event.EventHandler;
import dev.client.yanderov.utils.display.interfaces.QuickImports;
import java.util.concurrent.ThreadLocalRandom;
import net.minecraft.class_1268;
import net.minecraft.class_1536;
import net.minecraft.class_1787;
import net.minecraft.class_243;
import net.minecraft.class_2596;
import net.minecraft.class_2767;
import net.minecraft.class_3414;
import net.minecraft.class_3417;

public class AutoFish extends Module implements QuickImports {
   private final SliderSettings reelDelayMin = (new SliderSettings("ReelDelayMin", "ÐœÐ¸Ð½. Ð·Ð°Ð´ÐµÑ€Ð¶ÐºÐ° Ð¿Ð¾Ð´ÑÐµÑ‡ÐºÐ¸ (Ñ‚Ð¸ÐºÐ¸)")).setValue(5.0F).range(0.0F, 20.0F);
   private final SliderSettings reelDelayMax = (new SliderSettings("ReelDelayMax", "ÐœÐ°ÐºÑ. Ð·Ð°Ð´ÐµÑ€Ð¶ÐºÐ° Ð¿Ð¾Ð´ÑÐµÑ‡ÐºÐ¸ (Ñ‚Ð¸ÐºÐ¸)")).setValue(8.0F).range(0.0F, 20.0F);
   private final BooleanSetting recast = (new BooleanSetting("RecastRod", "ÐŸÐ¾Ð²Ñ‚Ð¾Ñ€Ð½Ñ‹Ð¹ Ð·Ð°Ð±Ñ€Ð¾Ñ")).setValue(true);
   private final SliderSettings recastDelayMin = (new SliderSettings("RecastDelayMin", "ÐœÐ¸Ð½. Ð·Ð°Ð´ÐµÑ€Ð¶ÐºÐ° Ð·Ð°Ð±Ñ€Ð¾ÑÐ° (Ñ‚Ð¸ÐºÐ¸)")).setValue(15.0F).range(0.0F, 40.0F).visible(() -> this.recast.isValue());
   private final SliderSettings recastDelayMax = (new SliderSettings("RecastDelayMax", "ÐœÐ°ÐºÑ. Ð·Ð°Ð´ÐµÑ€Ð¶ÐºÐ° Ð·Ð°Ð±Ñ€Ð¾ÑÐ° (Ñ‚Ð¸ÐºÐ¸)")).setValue(20.0F).range(0.0F, 40.0F).visible(() -> this.recast.isValue());
   private final BooleanSetting distanceFilter = (new BooleanSetting("SoundDistance", "Ð¤Ð¸Ð»ÑŒÑ‚Ñ€ Ð¿Ð¾ Ð´Ð¸ÑÑ‚Ð°Ð½Ñ†Ð¸Ð¸ Ð·Ð²ÑƒÐºÐ°")).setValue(true);
   private final SliderSettings maxDistance = (new SliderSettings("MaxDistance", "ÐœÐ°ÐºÑ. Ð´Ð¸ÑÑ‚Ð°Ð½Ñ†Ð¸Ñ Ð´Ð¾ Ð·Ð²ÑƒÐºÐ° (Ð±Ð»Ð¾ÐºÐ¸)")).setValue(1.0F).range(0.0F, 10.0F).visible(() -> this.distanceFilter.isValue());
   private boolean pendingReel = false;
   private boolean pendingRecast = false;
   private int waitTicks = 0;
   private class_1268 plannedHand;

   public AutoFish() {
      super("AutoFish", ModuleCategory.PLAYER);
      this.plannedHand = class_1268.field_5808;
      this.setup(new Setting[]{this.reelDelayMin, this.reelDelayMax, this.recast, this.recastDelayMin, this.recastDelayMax, this.distanceFilter, this.maxDistance});
   }

   public void deactivate() {
      super.deactivate();
      this.pendingReel = false;
      this.pendingRecast = false;
      this.waitTicks = 0;
   }

   @EventHandler
   public void onPacket(PacketEvent e) {
      if (e.getType() == PacketEvent.Type.RECEIVE) {
         class_2596 var3 = e.getPacket();
         if (var3 instanceof class_2767) {
            class_2767 pkt = (class_2767)var3;
            if (mc.field_1724 != null) {
               try {
                  class_3414 ev = (class_3414)pkt.method_11894().comp_349();
                  if (ev != class_3417.field_14660) {
                     return;
                  }
               } catch (Throwable var10) {
                  return;
               }

               if (this.distanceFilter.isValue()) {
                  try {
                     class_1536 hook = mc.field_1724.field_7513;
                     if (hook != null && !hook.method_31481()) {
                        class_243 soundPos = new class_243(pkt.method_11890(), pkt.method_11889(), pkt.method_11893());
                        double distSq = hook.method_19538().method_1025(soundPos);
                        if (distSq > (double)(this.maxDistance.getValue() * this.maxDistance.getValue())) {
                           return;
                        }
                     }
                  } catch (Throwable var8) {
                  }
               }

               for(class_1268 h : new class_1268[]{class_1268.field_5808, class_1268.field_5810}) {
                  try {
                     if (mc.field_1724.method_5998(h).method_7909() instanceof class_1787) {
                        this.plannedHand = h;
                        break;
                     }
                  } catch (Throwable var9) {
                  }
               }

               this.pendingReel = true;
               this.pendingRecast = false;
               this.waitTicks = this.randomBetween((int)this.reelDelayMin.getValue(), (int)this.reelDelayMax.getValue());
            }
         }
      }
   }

   @EventHandler
   public void onTick(TickEvent e) {
      if (e.getType() == 0) {
         if (mc.field_1724 != null && mc.field_1761 != null) {
            if (this.waitTicks > 0) {
               --this.waitTicks;
            } else if (this.pendingReel) {
               this.interactItem(this.plannedHand);
               if (this.recast.isValue()) {
                  this.pendingRecast = true;
                  this.waitTicks = this.randomBetween((int)this.recastDelayMin.getValue(), (int)this.recastDelayMax.getValue());
               }

               this.pendingReel = false;
            } else {
               if (this.pendingRecast) {
                  this.interactItem(this.plannedHand);
                  this.pendingRecast = false;
               }

            }
         }
      }
   }

   private void interactItem(class_1268 hand) {
      try {
         mc.field_1761.method_2919(mc.field_1724, hand);
      } catch (Throwable var4) {
      }

      try {
         mc.field_1724.method_6104(hand);
      } catch (Throwable var3) {
      }

   }

   private int randomBetween(int a, int b) {
      if (b < a) {
         int t = a;
         a = b;
         b = t;
      }

      return ThreadLocalRandom.current().nextInt(b - a + 1) + a;
   }
}

