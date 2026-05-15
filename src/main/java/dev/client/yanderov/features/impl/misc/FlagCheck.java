package dev.client.yanderov.features.impl.misc;

import dev.client.yanderov.events.packet.PacketEvent;
import dev.client.yanderov.events.player.TickEvent;
import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.features.module.ModuleCategory;
import dev.client.yanderov.features.module.setting.Setting;
import dev.client.yanderov.features.module.setting.implement.BooleanSetting;
import dev.client.yanderov.utils.client.managers.event.EventHandler;
import dev.client.yanderov.utils.display.interfaces.QuickImports;
import java.lang.reflect.Field;
import net.minecraft.class_2561;
import net.minecraft.class_2661;
import net.minecraft.class_2708;

public class FlagCheck extends Module implements QuickImports {
   private final BooleanSetting chatMessage = (new BooleanSetting("ChatMessage", "Ð¡Ð¾Ð¾Ð±Ñ‰ÐµÐ½Ð¸Ðµ Ð² Ñ‡Ð°Ñ‚ Ð¿Ñ€Ð¸ Ñ„Ð»Ð°Ð³Ð°Ñ…")).setValue(true);
   private final BooleanSetting notification = (new BooleanSetting("Notification", "Ð›Ð¾ÐºÐ°Ð»ÑŒÐ½Ð°Ñ Ð½Ð¾Ñ‚Ð¸Ñ„Ð¸ÐºÐ°Ñ†Ð¸Ñ (Ñ‡Ð°Ñ‚)")).setValue(false);
   private final BooleanSetting invalidAttributes = (new BooleanSetting("InvalidAttributes", "ÐŸÑ€Ð¾Ð²ÐµÑ€ÐºÐ° Ð½ÐµÐºÐ¾Ñ€Ñ€ÐµÐºÑ‚Ð½Ñ‹Ñ… Ð°Ñ‚Ñ€Ð¸Ð±ÑƒÑ‚Ð¾Ð²")).setValue(false);
   private int flagCount = 0;
   private float lastYaw = 0.0F;
   private float lastPitch = 0.0F;

   public FlagCheck() {
      super("FlagCheck", ModuleCategory.MISC);
      this.setup(new Setting[]{this.chatMessage, this.notification, this.invalidAttributes});
   }

   @EventHandler
   public void onPacket(PacketEvent e) {
      if (e.getType() == PacketEvent.Type.RECEIVE) {
         if (mc.field_1724 != null) {
            Object pkt = e.getPacket();
            if (mc.field_1724.field_6012 > 25) {
               try {
                  if (pkt instanceof class_2708) {
                     class_2708 ppl = (class_2708)pkt;
                     float pyaw = 0.0F;
                     float ppitch = 0.0F;

                     try {
                        pyaw = (Float)ppl.getClass().getMethod("getYaw").invoke(ppl);
                     } catch (Throwable var11) {
                     }

                     try {
                        ppitch = (Float)ppl.getClass().getMethod("getPitch").invoke(ppl);
                     } catch (Throwable var10) {
                     }

                     if (pyaw == 0.0F && ppitch == 0.0F) {
                        try {
                           Field fy = ppl.getClass().getDeclaredField("yaw");
                           fy.setAccessible(true);
                           pyaw = ((Number)fy.get(ppl)).floatValue();
                        } catch (Throwable var9) {
                        }

                        try {
                           Field fp = ppl.getClass().getDeclaredField("pitch");
                           fp.setAccessible(true);
                           ppitch = ((Number)fp.get(ppl)).floatValue();
                        } catch (Throwable var8) {
                        }
                     }

                     float dy = this.angleDelta(pyaw, this.lastYaw);
                     float dp = this.angleDelta(ppitch, this.lastPitch);
                     ++this.flagCount;
                     if (!(dy >= 90.0F) && !(dp >= 90.0F)) {
                        this.alert(FlagCheck.AlertReason.LAGBACK, (String)null);
                     } else {
                        AlertReason var10001 = FlagCheck.AlertReason.FORCEROTATE;
                        int var10002 = Math.round(dy);
                        this.alert(var10001, "(" + var10002 + "Â° | " + Math.round(dp) + "Â°)");
                     }

                     this.lastYaw = mc.field_1724.method_5791();
                     this.lastPitch = mc.field_1724.method_36455();
                  } else if (pkt instanceof class_2661) {
                     this.flagCount = 0;
                  }
               } catch (Throwable var12) {
               }

            }
         }
      }
   }

   @EventHandler
   public void onTick(TickEvent e) {
      if (e.getType() == 0) {
         if (this.invalidAttributes.isValue()) {
            if (mc.field_1724 != null) {
               try {
                  boolean invalidHealth = mc.field_1724.method_6032() <= 0.0F && mc.field_1724.method_5805();
                  boolean invalidHunger = mc.field_1724.method_7344().method_7586() <= 0;
                  if (!invalidHealth && !invalidHunger) {
                     return;
                  }

                  ++this.flagCount;
                  StringBuilder reason = new StringBuilder();
                  if (invalidHealth) {
                     reason.append("Health");
                  }

                  if (invalidHunger) {
                     if (reason.length() > 0) {
                        reason.append(", ");
                     }

                     reason.append("Hunger");
                  }

                  this.alert(FlagCheck.AlertReason.INVALID, reason.toString());
               } catch (Throwable var5) {
               }

            }
         }
      }
   }

   private void alert(AlertReason reason, String extra) {
      String var10000;
      switch (reason.ordinal()) {
         case 0 -> var10000 = "Invalid";
         case 1 -> var10000 = "ForceRotate";
         case 2 -> var10000 = "Lagback";
         default -> throw new MatchException((String)null, (Throwable)null);
      }

      String base = var10000;
      String msg = extra != null && !extra.isEmpty() ? String.format("[FlagCheck] %s %s (x%d)", base, extra, this.flagCount) : String.format("[FlagCheck] %s (x%d)", base, this.flagCount);
      if (this.notification.isValue()) {
         try {
            if (mc.field_1724 != null) {
               mc.field_1724.method_7353(class_2561.method_30163(msg), false);
            }
         } catch (Throwable var7) {
         }
      }

      if (this.chatMessage.isValue()) {
         try {
            if (mc.field_1724 != null) {
               mc.field_1724.method_7353(class_2561.method_30163(msg), false);
            }
         } catch (Throwable var6) {
         }
      }

   }

   private float angleDelta(float aNew, float aOld) {
      float d = aNew - aOld;
      if (d > 180.0F) {
         d -= 360.0F;
      }

      if (d < -180.0F) {
         d += 360.0F;
      }

      return Math.abs(d);
   }

   private static enum AlertReason {
      INVALID,
      FORCEROTATE,
      LAGBACK;

      // $FF: synthetic method
      private static AlertReason[] $values() {
         return new AlertReason[]{INVALID, FORCEROTATE, LAGBACK};
      }
   }
}

