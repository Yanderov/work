package fun.Yanderov.features.impl.combat;

import fun.Yanderov.events.packet.PacketEvent;
import fun.Yanderov.events.player.InputEvent;
import fun.Yanderov.events.player.TickEvent;
import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.features.module.setting.Setting;
import fun.Yanderov.features.module.setting.implement.BooleanSetting;
import fun.Yanderov.features.module.setting.implement.MultiSelectSetting;
import fun.Yanderov.features.module.setting.implement.SelectSetting;
import fun.Yanderov.features.module.setting.implement.SliderSettings;
import fun.Yanderov.utils.client.managers.event.EventHandler;
import fun.Yanderov.utils.display.interfaces.QuickImports;
import fun.Yanderov.utils.interactions.interact.PlayerInteractionHelper;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Random;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_243;
import net.minecraft.class_2596;
import net.minecraft.class_2824;
import net.minecraft.class_2848;
import net.minecraft.class_2848.class_2849;

public class SuperKnockback extends Module implements QuickImports {
   private final SelectSetting mode = (new SelectSetting("Mode", "Ð ÐµÐ¶Ð¸Ð¼ ÑƒÐ²ÐµÐ»Ð¸Ñ‡ÐµÐ½Ð¸Ñ Ð¾Ñ‚ÐºÐ¸Ð´Ð°")).value("Packet", "SprintTap", "WTap").selected("Packet");
   private final SliderSettings hurtTime = (new SliderSettings("HurtTime", "ÐœÐ°ÐºÑ. hurtTime Ñ†ÐµÐ»Ð¸")).setValue(10.0F).range(0.0F, 10.0F);
   private final SliderSettings chance = (new SliderSettings("Chance", "Ð¨Ð°Ð½Ñ ÑÑ€Ð°Ð±Ð°Ñ‚Ñ‹Ð²Ð°Ð½Ð¸Ñ, %")).setValue(100.0F).range(0.0F, 100.0F);
   private final MultiSelectSetting conditions = (new MultiSelectSetting("Conditions", "Ð£ÑÐ»Ð¾Ð²Ð¸Ñ ÑÑ€Ð°Ð±Ð°Ñ‚Ñ‹Ð²Ð°Ð½Ð¸Ñ")).value("OnlyFacing", "OnlyOnGround", "NotInWater").selected("NotInWater");
   private final BooleanSetting onlyOnMove = (new BooleanSetting("OnlyOnMove", "Ð¢Ð¾Ð»ÑŒÐºÐ¾ Ð¿Ñ€Ð¸ Ð´Ð²Ð¸Ð¶ÐµÐ½Ð¸Ð¸")).setValue(true);
   private final BooleanSetting onlyForward = (new BooleanSetting("OnlyForward", "Ð¢Ð¾Ð»ÑŒÐºÐ¾ Ð²Ð¿ÐµÑ€Ñ‘Ð´")).setValue(true).visible(() -> this.onlyOnMove.isValue());
   private final SliderSettings reSprintTicks = (new SliderSettings("ReSprint", "Ð—Ð°Ð´ÐµÑ€Ð¶ÐºÐ° Ð¿ÐµÑ€ÐµÐ´ Ð¿Ð¾Ð²Ñ‚Ð¾Ñ€Ð½Ñ‹Ð¼ ÑÐ¿Ñ€Ð¸Ð½Ñ‚Ð¾Ð¼ (Ñ‚Ð¸ÐºÐ¸)")).setValue(0.0F).range(0.0F, 10.0F).visible(() -> this.mode.isSelected("SprintTap"));
   private final SliderSettings untilMovementBlock = (new SliderSettings("UntilMovementBlock", "Ð—Ð°Ð´ÐµÑ€Ð¶ÐºÐ° Ð¿ÐµÑ€ÐµÐ´ Ð±Ð»Ð¾ÐºÐ¸Ñ€Ð¾Ð²ÐºÐ¾Ð¹ (Ñ‚Ð¸ÐºÐ¸)")).setValue(0.0F).range(0.0F, 10.0F).visible(() -> this.mode.isSelected("WTap"));
   private final SliderSettings untilAllowedMovement = (new SliderSettings("UntilAllowedMovement", "Ð—Ð°Ð´ÐµÑ€Ð¶ÐºÐ° Ð´Ð¾ Ñ€Ð°Ð·Ñ€ÐµÑˆÐµÐ½Ð¸Ñ (Ñ‚Ð¸ÐºÐ¸)")).setValue(1.0F).range(0.0F, 10.0F).visible(() -> this.mode.isSelected("WTap"));
   private boolean cancelSprint = false;
   private int reSprintWait = 0;
   private boolean inWTapSeq = false;
   private boolean cancelMovement = false;
   private int wtapWaitTicks = 0;
   private final Random rng = new Random();

   public SuperKnockback() {
      super("SuperKnockback", ModuleCategory.COMBAT);
      this.setup(new Setting[]{this.mode, this.hurtTime, this.chance, this.conditions, this.onlyOnMove, this.onlyForward, this.reSprintTicks, this.untilMovementBlock, this.untilAllowedMovement});
   }

   @EventHandler
   public void onPacket(PacketEvent e) {
      if (e.getType() == PacketEvent.Type.SEND) {
         class_2596 var3 = e.getPacket();
         if (var3 instanceof class_2824) {
            class_2824 pkt = (class_2824)var3;
            if (mc.field_1687 != null) {
               class_1297 ent = null;

               try {
                  int id = -1;

                  try {
                     Method m = pkt.getClass().getMethod("getEntityId");
                     m.setAccessible(true);
                     id = ((Number)m.invoke(pkt)).intValue();
                  } catch (Throwable var7) {
                  }

                  if (id == -1) {
                     Field f = pkt.getClass().getDeclaredField("entityId");
                     f.setAccessible(true);
                     id = ((Number)f.get(pkt)).intValue();
                  }

                  if (id != -1) {
                     ent = mc.field_1687.method_8469(id);
                  }
               } catch (Throwable var8) {
               }

               if (ent != null) {
                  if (ent instanceof class_1309) {
                     class_1309 target = (class_1309)ent;
                     if (this.shouldOperate(target)) {
                        try {
                           if (target.field_6235 > (int)this.hurtTime.getValue()) {
                              return;
                           }
                        } catch (Throwable var6) {
                        }

                        if (this.rng.nextInt(100) < (int)this.chance.getValue()) {
                           if (this.mode.isSelected("Packet")) {
                              this.doPacketSequence();
                           } else if (this.mode.isSelected("SprintTap")) {
                              if (!this.cancelSprint && this.shouldStopSprinting(target)) {
                                 this.startSprintTapSequence();
                              }
                           } else if (this.mode.isSelected("WTap") && !this.inWTapSeq && this.shouldStopSprinting(target)) {
                              this.startWTapSequence();
                           }

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
         if (mc.field_1724 != null) {
            if (this.cancelSprint) {
               mc.field_1724.method_5728(false);
               if (this.reSprintWait > 0) {
                  --this.reSprintWait;
               } else {
                  this.cancelSprint = false;
               }
            }

            if (this.inWTapSeq) {
               if (this.wtapWaitTicks > 0) {
                  --this.wtapWaitTicks;
               } else if (!this.cancelMovement) {
                  this.inWTapSeq = false;
               }
            }

         }
      }
   }

   @EventHandler
   public void onInput(InputEvent e) {
      if (this.inWTapSeq) {
         if (this.cancelMovement) {
            e.setDirectional(false, false, false, false);
            e.setJumping(false);
         }

      }
   }

   private void doPacketSequence() {
      try {
         if (mc.field_1724.method_5624()) {
            PlayerInteractionHelper.sendPacketWithOutEvent(new class_2848(mc.field_1724, class_2849.field_12985));
         }

         PlayerInteractionHelper.sendPacketWithOutEvent(new class_2848(mc.field_1724, class_2849.field_12981));
         PlayerInteractionHelper.sendPacketWithOutEvent(new class_2848(mc.field_1724, class_2849.field_12985));
         PlayerInteractionHelper.sendPacketWithOutEvent(new class_2848(mc.field_1724, class_2849.field_12981));
         mc.field_1724.method_5728(true);
      } catch (Throwable var2) {
      }

   }

   private boolean shouldStopSprinting(class_1309 enemy) {
      if (mc.field_1724 == null) {
         return false;
      } else if (!mc.field_1724.method_5624()) {
         return false;
      } else {
         try {
            if (enemy.field_6235 > (int)this.hurtTime.getValue()) {
               return false;
            }
         } catch (Throwable var3) {
         }

         return true;
      }
   }

   private boolean shouldOperate(class_1297 target) {
      if (mc.field_1724 == null) {
         return false;
      } else {
         if (this.onlyOnMove.isValue()) {
            float fwd = mc.field_1724.field_6250;
            float side = mc.field_1724.field_6212;
            boolean moving = fwd != 0.0F || side != 0.0F;
            if (!moving) {
               return false;
            }

            if (this.onlyForward.isValue() && side != 0.0F) {
               return false;
            }
         }

         if (this.conditions.isSelected("OnlyOnGround") && !mc.field_1724.method_24828()) {
            return false;
         } else if (this.conditions.isSelected("NotInWater") && mc.field_1724.method_5799()) {
            return false;
         } else {
            if (this.conditions.isSelected("OnlyFacing")) {
               try {
                  class_243 dir = this.getEntityFacingVector(target);
                  class_243 toPlayer = mc.field_1724.method_19538().method_1020(target.method_19538());
                  if (dir != null && dir.method_1026(toPlayer) >= (double)0.0F) {
                     return false;
                  }
               } catch (Throwable var5) {
               }
            }

            return true;
         }
      }
   }

   private class_243 getEntityFacingVector(class_1297 e) {
      try {
         return e.method_5828(0.0F);
      } catch (Throwable var4) {
         try {
            return e.method_5720();
         } catch (Throwable var3) {
            return null;
         }
      }
   }

   private void startSprintTapSequence() {
      this.cancelSprint = true;
      this.reSprintWait = Math.max(0, Math.round(this.reSprintTicks.getValue()));
   }

   private void startWTapSequence() {
      this.inWTapSeq = true;
      int blockDelay = Math.max(0, Math.round(this.untilMovementBlock.getValue()));
      int allowDelay = Math.max(0, Math.round(this.untilAllowedMovement.getValue()));
      this.wtapWaitTicks = blockDelay;
      this.cancelMovement = false;
      mc.execute(() -> {
      });
      (new Thread(() -> {
         try {
            while(true) {
               if (this.wtapWaitTicks <= 0 || !this.inWTapSeq) {
                  if (!this.inWTapSeq) {
                     return;
                  }

                  this.cancelMovement = true;
                  this.wtapWaitTicks = allowDelay;

                  while(this.wtapWaitTicks > 0 && this.inWTapSeq) {
                     Thread.sleep(50L);
                  }

                  this.cancelMovement = false;
                  break;
               }

               Thread.sleep(50L);
            }
         } catch (InterruptedException var3) {
         }

      })).start();
   }
}

