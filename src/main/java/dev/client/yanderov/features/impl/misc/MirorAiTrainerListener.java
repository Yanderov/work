package dev.client.yanderov.features.impl.misc;

import dev.client.yanderov.events.packet.PacketEvent;
import dev.client.yanderov.main.listener.Listener;
import dev.client.yanderov.utils.client.managers.event.EventHandler;
import dev.client.yanderov.utils.features.aura.miror.MirorAiManager;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import net.minecraft.class_1297;
import net.minecraft.class_1621;
import net.minecraft.class_243;
import net.minecraft.class_2824;
import net.minecraft.class_2879;
import net.minecraft.class_310;

public class MirorAiTrainerListener implements Listener {
   @EventHandler
   public void onPacket(PacketEvent e) {
      if (e.isSend()) {
         Object pkt = e.getPacket();
         if (pkt instanceof class_2824) {
            class_2824 interact = (class_2824)pkt;
            this.handleInteract(interact);
         }

         if (pkt instanceof class_2879) {
            class_2879 swing = (class_2879)pkt;
            this.handleSwing(swing);
         }

      }
   }

   private void handleInteract(class_2824 pkt) {
      Object type = this.getInteractTypeCompat(pkt);
      if (type != null) {
         String typeName = String.valueOf(type).toUpperCase();
         if (typeName.contains("ATTACK")) {
            class_310 mc = class_310.method_1551();
            if (mc.field_1687 != null) {
               int id = -1;

               try {
                  try {
                     Method m = pkt.getClass().getMethod("getEntityId");
                     m.setAccessible(true);
                     id = ((Number)m.invoke(pkt)).intValue();
                  } catch (Throwable var8) {
                  }

                  if (id == -1) {
                     try {
                        Field f = pkt.getClass().getDeclaredField("entityId");
                        f.setAccessible(true);
                        id = ((Number)f.get(pkt)).intValue();
                     } catch (Throwable var7) {
                     }
                  }
               } catch (Throwable var9) {
               }

               if (id != -1) {
                  class_1297 ent = mc.field_1687.method_8469(id);
                  if (ent instanceof class_1621) {
                     MirorAiManager.getInstance().onPlayerAttackEntity(ent.method_5628());
                  }
               }
            }
         }
      }
   }

   private void handleSwing(class_2879 pkt) {
      Object handObj = this.getHandCompat(pkt);
      if (handObj != null) {
         String handName = String.valueOf(handObj).toUpperCase();
         if (handName.contains("MAIN")) {
            class_310 mc = class_310.method_1551();
            if (mc.field_1724 != null && mc.field_1687 != null) {
               class_1621 trainer = MirorAiManager.getInstance().getCurrentTrainer();
               if (trainer != null && !trainer.method_31481()) {
                  class_243 eye = mc.field_1724.method_33571();
                  class_243 center = trainer.method_5829().method_1005();
                  class_243 toTarget = center.method_1020(eye);
                  double dist = toTarget.method_1033();
                  if (!(dist <= 1.0E-6)) {
                     double maxDistance = (double)7.0F;
                     if (!(dist > maxDistance)) {
                        class_243 dirToTarget = toTarget.method_1029();
                        class_243 look = mc.field_1724.method_5828(1.0F);
                        double dot = look.method_1026(dirToTarget);
                        if (!(dot < 0.97)) {
                           MirorAiManager.getInstance().onPlayerAttackEntity(trainer.method_5628());
                        }
                     }
                  }
               }
            }
         }
      }
   }

   private Object getInteractTypeCompat(class_2824 pkt) {
      try {
         return class_2824.class.getMethod("getType").invoke(pkt);
      } catch (Throwable var5) {
         try {
            return class_2824.class.getMethod("getInteractionType").invoke(pkt);
         } catch (Throwable var4) {
            try {
               return class_2824.class.getMethod("getAction").invoke(pkt);
            } catch (Throwable var3) {
               return null;
            }
         }
      }
   }

   private Object getHandCompat(class_2879 pkt) {
      try {
         return class_2879.class.getMethod("getHand").invoke(pkt);
      } catch (Throwable var4) {
         try {
            Field f = class_2879.class.getDeclaredField("hand");
            f.setAccessible(true);
            return f.get(pkt);
         } catch (Throwable var3) {
            return null;
         }
      }
   }
}

