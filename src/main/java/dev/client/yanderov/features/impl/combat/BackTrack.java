package dev.client.yanderov.features.impl.combat;

import dev.client.yanderov.events.packet.PacketEvent;
import dev.client.yanderov.events.player.AttackEvent;
import dev.client.yanderov.events.player.TickEvent;
import dev.client.yanderov.events.render.WorldRenderEvent;
import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.features.module.ModuleCategory;
import dev.client.yanderov.features.module.setting.Setting;
import dev.client.yanderov.features.module.setting.implement.BindSetting;
import dev.client.yanderov.features.module.setting.implement.BooleanSetting;
import dev.client.yanderov.features.module.setting.implement.SelectSetting;
import dev.client.yanderov.features.module.setting.implement.SliderSettings;
import dev.client.yanderov.utils.client.managers.event.EventHandler;
import dev.client.yanderov.utils.display.color.ColorAssist;
import dev.client.yanderov.utils.display.geometry.Render3D;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_2596;
import net.minecraft.class_2663;
import net.minecraft.class_2684;
import net.minecraft.class_2708;
import net.minecraft.class_2743;
import net.minecraft.class_2767;
import net.minecraft.class_2777;
import net.minecraft.class_310;
import net.minecraft.class_3532;
import org.lwjgl.glfw.GLFW;

public class BackTrack extends Module {
   private final SelectSetting mode = (new SelectSetting("Mode", "Backtrack Ñ€ÐµÐ¶Ð¸Ð¼")).value("Ultimate", "Intave", "Grim Latest", "All AC", "Custom").selected("Ultimate");
   private final SliderSettings range = (new SliderSettings("Range", "ÐœÐ°ÐºÑÐ¸Ð¼Ð°Ð»ÑŒÐ½Ð°Ñ Ð´Ð¸ÑÑ‚Ð°Ð½Ñ†Ð¸Ñ Ð´Ð¾ ÑÐµÑ€Ð²ÐµÑ€Ð½Ð¾Ð¹ Ð¿Ð¾Ð·Ð¸Ñ†Ð¸Ð¸")).setValue(3.0F).range(0.0F, 6.0F);
   private final SliderSettings delayMs = (new SliderSettings("Delay", "Ð—Ð°Ð´ÐµÑ€Ð¶ÐºÐ° Ð¿Ð°ÐºÐµÑ‚Ð¾Ð² (ms)")).setValue(120.0F).range(0.0F, 1000.0F).visible(() -> this.mode.isSelected("Custom"));
   private final BooleanSetting renderBox = (new BooleanSetting("RenderBox", "Ð ÐµÐ½Ð´ÐµÑ€Ð¸Ñ‚ÑŒ ESP Ð±Ð¾ÐºÑ")).setValue(true);
   private final BooleanSetting autoReset = (new BooleanSetting("AutoReset", "ÐÐ²Ñ‚Ð¾-ÑÐ±Ñ€Ð¾Ñ Ð¿Ñ€Ð¸ Ñ€Ð°Ð·Ñ€Ñ‹Ð²Ðµ")).setValue(true);
   private final BindSetting resetBind = (new BindSetting("ResetBind", "ÐšÐ½Ð¾Ð¿ÐºÐ° ÑÐ±Ñ€Ð¾ÑÐ°")).setKey(0);
   private final List queue = new LinkedList();
   private int targetId = -1;
   private class_243 serverPos = null;
   private class_243 serverPosLerped = null;

   public BackTrack() {
      super("BackTrack", "BackTrack", ModuleCategory.COMBAT);
      this.setup(new Setting[]{this.mode, this.range, this.delayMs, this.renderBox, this.autoReset, this.resetBind});
   }

   private long getDelay() {
      long var10000;
      switch (this.mode.getSelected()) {
         case "Grim Latest" -> var10000 = 100L;
         case "Ultimate" -> var10000 = 80L;
         case "All AC" -> var10000 = 50L;
         case "Intave" -> var10000 = 200L;
         case "Custom" -> var10000 = (long)this.delayMs.getValue();
         default -> var10000 = 0L;
      }

      long base = var10000;
      if (mc.field_1724 != null && mc.field_1724.method_5681()) {
         base += 40L;
      }

      return base;
   }

   private double getMaxDistance() {
      if (this.mode.isSelected("Custom")) {
         return (double)this.range.getValue();
      } else {
         return this.mode.isSelected("All AC") ? 3.8 : (double)6.0F;
      }
   }

   @EventHandler
   public void onAttack(AttackEvent e) {
      if (mc.field_1724 != null && mc.field_1687 != null) {
         class_1297 hit = e.getEntity();
         if (hit instanceof class_1309) {
            class_1309 living = (class_1309)hit;
            if (living.method_5805() && !living.method_5655()) {
               this.targetId = living.method_5628();
               this.serverPos = living.method_19538();
               this.serverPosLerped = this.serverPos;
            }
         }
      }
   }

   @EventHandler
   public void onPacket(PacketEvent e) {
      if (mc.field_1724 != null && mc.field_1687 != null && !mc.method_1542()) {
         if (!e.isSend()) {
            if (this.hasTarget()) {
               class_2596<?> packet = e.getPacket();
               if (packet != null) {
                  if (!(packet instanceof class_2767) && !(packet instanceof class_2663)) {
                     if (packet instanceof class_2708) {
                        this.reset();
                     } else {
                        if (this.serverPos != null) {
                           if (packet instanceof class_2777) {
                              class_2777 pos = (class_2777)packet;
                              int id = this.reflectEntityId(pos);
                              if (id == this.targetId) {
                                 double x = this.reflectDouble(pos, "x", this.serverPos.field_1352);
                                 double y = this.reflectDouble(pos, "y", this.serverPos.field_1351);
                                 double z = this.reflectDouble(pos, "z", this.serverPos.field_1350);
                                 this.serverPos = new class_243(x, y, z);
                              }
                           }

                           if (packet instanceof class_2684) {
                              class_2684 rel = (class_2684)packet;
                              int id = this.reflectEntityId(rel);
                              if (id == this.targetId) {
                                 double dx = this.reflectDelta(rel, "deltaX") / (double)4096.0F;
                                 double dy = this.reflectDelta(rel, "deltaY") / (double)4096.0F;
                                 double dz = this.reflectDelta(rel, "deltaZ") / (double)4096.0F;
                                 this.serverPos = this.serverPos.method_1031(dx, dy, dz);
                              }
                           }

                           if (packet instanceof class_2743) {
                              class_2743 var14 = (class_2743)packet;
                           }
                        }

                        e.cancel();
                        synchronized(this.queue) {
                           this.queue.add(new QueuedPacket(packet, System.currentTimeMillis()));
                        }
                     }
                  }
               }
            }
         }
      }
   }

   @EventHandler
   public void onTick(TickEvent e) {
      if (e.getType() == 0) {
         if (mc.field_1724 != null && mc.field_1687 != null && !mc.method_1542()) {
            if (this.resetBind.getKey() != 0 && mc.field_1690 != null && mc.method_22683() != null && this.mode.isSelected("Custom")) {
               int key = this.resetBind.getKey();
               long handle = mc.method_22683().method_4490();
               if (key != -1 && GLFW.glfwGetKey(handle, key) == 1) {
                  this.reset();
                  return;
               }
            }

            if (!this.hasTarget()) {
               if (this.autoReset.isValue()) {
                  this.reset();
               }

            } else {
               if (this.serverPos != null && mc.field_1724 != null) {
                  double dist = mc.field_1724.method_19538().method_1022(this.serverPos);
                  if (dist > this.getMaxDistance() && this.autoReset.isValue()) {
                     this.reset();
                     return;
                  }
               }

               this.flushQueue(false);
            }
         }
      }
   }

   @EventHandler
   public void onWorldRender(WorldRenderEvent e) {
      if (this.renderBox.isValue()) {
         if (mc.field_1724 != null && mc.field_1687 != null) {
            if (this.hasTarget() && this.serverPos != null) {
               class_1297 ent = mc.field_1687.method_8469(this.targetId);
               if (ent instanceof class_1309) {
                  class_1309 living = (class_1309)ent;
                  if (this.serverPosLerped != null && !(this.serverPosLerped.method_1025(this.serverPos) > (double)4.0F)) {
                     double t = class_3532.method_15350(deltaTime() * (double)12.0F, (double)0.0F, (double)1.0F);
                     this.serverPosLerped = this.serverPosLerped.method_35590(this.serverPos, t);
                  } else {
                     this.serverPosLerped = this.serverPos;
                  }

                  double half = (double)living.method_17681() / (double)2.0F;
                  class_238 box = new class_238(this.serverPosLerped.field_1352 - half, this.serverPosLerped.field_1351, this.serverPosLerped.field_1350 - half, this.serverPosLerped.field_1352 + half, this.serverPosLerped.field_1351 + (double)living.method_5751(), this.serverPosLerped.field_1350 + half);
                  int color = ColorAssist.multAlpha(-1, 0.8F);
                  Render3D.drawBox(box, color, 1.0F);
               }
            }
         }
      }
   }

   private void flushQueue(boolean all) {
      if (!this.queue.isEmpty() && mc.method_1562() != null) {
         long now = System.currentTimeMillis();
         long baseDelay = this.getDelay();
         double factor = (double)1.0F;
         if (mc.field_1724 != null && this.serverPos != null) {
            double dist = mc.field_1724.method_19538().method_1022(this.serverPos);
            factor = dist / Math.max(0.001, this.getMaxDistance());
            factor = class_3532.method_15350(factor, 0.2, (double)2.0F);
         }

         synchronized(this.queue) {
            for(Iterator<QueuedPacket> it = this.queue.iterator(); it.hasNext(); it.remove()) {
               QueuedPacket qp = (QueuedPacket)it.next();
               if (!all && (double)(now - qp.timestamp) < (double)baseDelay * factor) {
                  break;
               }

               try {
                  class_2596<?> p = qp.packet;
                  p.method_65081(mc.method_1562());
               } catch (Throwable var13) {
               }
            }

         }
      }
   }

   private boolean hasTarget() {
      return this.targetId != -1;
   }

   private void reset() {
      this.flushQueue(true);
      synchronized(this.queue) {
         this.queue.clear();
      }

      this.targetId = -1;
      this.serverPos = null;
      this.serverPosLerped = null;
   }

   public void activate() {
      super.activate();
      this.reset();
   }

   public void deactivate() {
      super.deactivate();
      this.reset();
   }

   private static double deltaTime() {
      int fps = class_310.method_1551().method_47599();
      return fps > 0 ? (double)1.0F / (double)fps : (double)1.0F;
   }

   private int reflectEntityId(Object packet) {
      try {
         Class<?> cls = packet.getClass();

         try {
            Field f = cls.getDeclaredField("entityId");
            f.setAccessible(true);
            return f.getInt(packet);
         } catch (NoSuchFieldException var5) {
            try {
               Field f = cls.getDeclaredField("id");
               f.setAccessible(true);
               return f.getInt(packet);
            } catch (NoSuchFieldException var4) {
            }
         }
      } catch (Throwable var6) {
      }

      return -1;
   }

   private double reflectDouble(Object packet, String field, double def) {
      try {
         Field f = packet.getClass().getDeclaredField(field);
         f.setAccessible(true);
         return f.getDouble(packet);
      } catch (Throwable var6) {
         return def;
      }
   }

   private double reflectDelta(Object packet, String field) {
      try {
         Field f = packet.getClass().getDeclaredField(field);
         f.setAccessible(true);
         return (double)f.getInt(packet);
      } catch (Throwable var4) {
         return (double)0.0F;
      }
   }

   private static class QueuedPacket {
      final class_2596 packet;
      final long timestamp;

      QueuedPacket(class_2596 packet, long timestamp) {
         this.packet = packet;
         this.timestamp = timestamp;
      }
   }
}

