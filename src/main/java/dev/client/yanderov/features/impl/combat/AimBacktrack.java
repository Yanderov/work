package dev.client.yanderov.features.impl.combat;

import dev.client.yanderov.events.packet.PacketEvent;
import dev.client.yanderov.events.player.TickEvent;
import dev.client.yanderov.events.render.WorldRenderEvent;
import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.features.module.ModuleCategory;
import dev.client.yanderov.features.module.setting.Setting;
import dev.client.yanderov.features.module.setting.implement.SliderSettings;
import dev.client.yanderov.utils.client.managers.event.EventHandler;
import dev.client.yanderov.utils.display.color.ColorAssist;
import dev.client.yanderov.utils.display.geometry.Render3D;
import dev.client.yanderov.utils.display.interfaces.QuickImports;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.Predicate;
import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1675;
import net.minecraft.class_238;
import net.minecraft.class_239;
import net.minecraft.class_243;
import net.minecraft.class_2879;
import net.minecraft.class_3532;
import net.minecraft.class_3959;
import net.minecraft.class_3966;
import net.minecraft.class_239.class_240;
import net.minecraft.class_3959.class_242;
import net.minecraft.class_3959.class_3960;

public class AimBacktrack extends Module implements QuickImports {
   private final SliderSettings backtrack = (new SliderSettings("Ð‘ÐµÐºÑ‚Ñ€ÐµÐº", "ÐšÐ¾Ð»Ð¸Ñ‡ÐµÑÑ‚Ð²Ð¾ ÑÐ¾Ñ…Ñ€Ð°Ð½Ñ‘Ð½Ð½Ñ‹Ñ… Ð¿Ð¾Ð²Ð¾Ñ€Ð¾Ñ‚Ð¾Ð²")).setValue(6.0F).range(1.0F, 20.0F);
   private final SliderSettings reach = (new SliderSettings("Ð Ð¸Ñ‡", "Ð”Ð¸ÑÑ‚Ð°Ð½Ñ†Ð¸Ñ Ñ€ÐµÐ¹ÐºÐ°ÑÑ‚Ð°")).setValue(3.0F).range(2.5F, 6.0F);
   private Deque rotations = new ArrayDeque();
   private boolean attacked = false;
   private int lastSize = 0;
   private class_1309 espTarget = null;
   private class_243 espPos = null;
   private class_243 espLerped = null;

   public AimBacktrack() {
      super("AimBacktrack", ModuleCategory.COMBAT);
      this.setup(new Setting[]{this.backtrack, this.reach});
   }

   @EventHandler
   public void onTick(TickEvent e) {
      if (e.getType() == 0) {
         if (mc != null && mc.field_1724 != null) {
            int need = Math.max(1, Math.round(this.backtrack.getValue()));
            if (need != this.lastSize) {
               this.rotations.clear();
               this.lastSize = need;
            }

            this.rotations.addLast(new float[]{mc.field_1724.method_36454(), mc.field_1724.method_36455()});

            while(this.rotations.size() > need) {
               this.rotations.removeFirst();
            }

            this.attacked = false;
         }
      }
   }

   @EventHandler
   public void onPacket(PacketEvent e) {
      if (e.getType() == PacketEvent.Type.SEND) {
         if (e.getPacket() instanceof class_2879) {
            if (mc != null && mc.field_1724 != null && mc.field_1687 != null && mc.field_1761 != null) {
               try {
                  if (mc.field_1765 == null || mc.field_1765.method_17783() != class_240.field_1331) {
                     float dist = this.reach.getValue();

                     for(float[] rot : this.rotations) {
                        class_1297 hit = this.raycastEntity(rot[0], rot[1], dist);
                        if (hit instanceof class_1309) {
                           class_1309 living = (class_1309)hit;
                           if (!this.attacked) {
                              mc.field_1761.method_2918(mc.field_1724, living);
                              mc.field_1724.method_6104(class_1268.field_5808);
                              this.attacked = true;
                              this.espTarget = living;
                              this.espPos = living.method_19538();
                              this.espLerped = this.espPos;
                              break;
                           }
                        }
                     }
                  }
               } catch (Throwable var7) {
               }

            }
         }
      }
   }

   private class_1297 raycastEntity(float yaw, float pitch, float distance) {
      class_243 start = mc.field_1724.method_5836(1.0F);
      class_243 dir = this.directionFromRotation(yaw, pitch);
      class_243 end = start.method_1019(dir.method_1021((double)distance));

      try {
         class_3959 ctx = new class_3959(start, end, class_3960.field_17559, class_242.field_1348, mc.field_1724);
         class_239 hr = mc.field_1687.method_17742(ctx);
         if (hr != null && hr.method_17783() == class_240.field_1332) {
            end = hr.method_17784();
         }
      } catch (Throwable var10) {
      }

      Predicate<class_1297> predicate = (ent) -> ent != mc.field_1724 && ent.method_5805();
      class_238 box = mc.field_1724.method_5829().method_18804(dir.method_1021((double)distance)).method_1014((double)1.0F);
      class_3966 result = class_1675.method_18075(mc.field_1724, start, end, box, predicate, (double)(distance * distance));
      return result != null ? result.method_17782() : null;
   }

   private class_243 directionFromRotation(float yawDeg, float pitchDeg) {
      float yaw = (float)Math.toRadians((double)yawDeg);
      float pitch = (float)Math.toRadians((double)pitchDeg);
      float f = class_3532.method_15362(pitch);
      float x = -class_3532.method_15374(yaw) * f;
      float y = -class_3532.method_15374(pitch);
      float z = class_3532.method_15362(yaw) * f;
      return new class_243((double)x, (double)y, (double)z);
   }

   @EventHandler
   public void onWorldRender(WorldRenderEvent e) {
      if (this.isState()) {
         if (mc != null && mc.field_1724 != null && mc.field_1687 != null) {
            if (this.espTarget != null && !this.espTarget.method_31481() && this.espTarget.method_5805()) {
               if (this.espPos == null) {
                  this.espPos = this.espTarget.method_19538();
               }

               class_243 currentPos = this.espTarget.method_19538();
               this.espPos = currentPos;
               if (this.espLerped != null && !(this.espLerped.method_1025(this.espPos) > (double)4.0F)) {
                  double t = (double)0.5F;
                  this.espLerped = this.espLerped.method_35590(this.espPos, t);
               } else {
                  this.espLerped = this.espPos;
               }

               double half = (double)this.espTarget.method_17681() / (double)2.0F;
               class_238 box = new class_238(this.espLerped.field_1352 - half, this.espLerped.field_1351, this.espLerped.field_1350 - half, this.espLerped.field_1352 + half, this.espLerped.field_1351 + (double)this.espTarget.method_5751(), this.espLerped.field_1350 + half);
               int color = ColorAssist.multAlpha(-1, 0.8F);
               Render3D.drawBox(box, color, 1.0F);
            }
         }
      }
   }
}

