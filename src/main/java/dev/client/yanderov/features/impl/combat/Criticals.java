package dev.client.yanderov.features.impl.combat;

import dev.client.yanderov.events.player.AttackEvent;
import dev.client.yanderov.events.player.TickEvent;
import dev.client.yanderov.features.impl.movement.Jesus;
import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.features.module.ModuleCategory;
import dev.client.yanderov.features.module.setting.Setting;
import dev.client.yanderov.features.module.setting.implement.BooleanSetting;
import dev.client.yanderov.features.module.setting.implement.SelectSetting;
import dev.client.yanderov.utils.client.Instance;
import dev.client.yanderov.utils.client.managers.event.EventHandler;
import dev.client.yanderov.utils.display.interfaces.QuickImports;
import net.minecraft.class_2828;

public class Criticals extends Module implements QuickImports {
   public final SelectSetting mode = (new SelectSetting("Ð ÐµÐ¶Ð¸Ð¼", "Ð ÐµÐ¶Ð¸Ð¼ ÐºÑ€Ð¸Ñ‚Ð¾Ð²")).value("ÐžÐ±Ñ‹Ñ‡Ð½Ñ‹Ðµ", "NCP", "UpdatedNCP", "RWCollision", "Grim", "SpookyTime", "FunSky HVH", "MetaHvH").selected("ÐžÐ±Ñ‹Ñ‡Ð½Ñ‹Ðµ");
   public final BooleanSetting syncJesus = (new BooleanSetting("SyncJesus", "Ð¡Ð¸Ð½Ñ…Ñ€Ð¾Ð½Ð¸Ð·Ð¸Ñ€Ð¾Ð²Ð°Ñ‚ÑŒ ÐºÑ€Ð¸Ñ‚Ñ‹ Ñ Jesus")).setValue(true).visible(() -> this.mode.isSelected("FunSky HVH"));
   public final BooleanSetting syncTPInfluence = (new BooleanSetting("SyncTPInfluence", "Ð¡Ð¸Ð½Ñ…Ñ€Ð¾Ð½Ð¸Ð·Ð¸Ñ€Ð¾Ð²Ð°Ñ‚ÑŒ ÐºÑ€Ð¸Ñ‚Ñ‹ Ñ TPInfluence")).setValue(true).visible(() -> this.mode.isSelected("FunSky HVH"));

   public static Criticals getInstance() {
      return (Criticals)Instance.get(Criticals.class);
   }

   public Criticals() {
      super("Criticals", "Criticals", ModuleCategory.COMBAT);
      this.setup(new Setting[]{this.mode, this.syncJesus, this.syncTPInfluence});
   }

   @EventHandler
   public void onTick(TickEvent e) {
      if (e.getType() == 0) {
         if (mc != null && mc.field_1724 != null) {
            if (this.mode.isSelected("MetaHvH") && mc.field_1724.method_24828() && Aura.getInstance().getCurrentTarget() != null) {
               mc.field_1724.method_5762((double)0.0F, 0.04, (double)0.0F);
            }

            if (this.mode.isSelected("FunSky HVH")) {
               if (this.syncJesus.isValue()) {
                  Jesus jesus = (Jesus)Instance.get(Jesus.class);
                  if (jesus != null && jesus.isState() && mc.field_1724.method_5799()) {
                     mc.field_1724.method_5762((double)0.0F, 0.04, (double)0.0F);
                  }
               }

               if (this.syncTPInfluence.isValue()) {
                  TPInfluence tp = TPInfluence.getInstance();
                  if (tp != null && tp.isState() && tp.isInTeleportCycle() && Aura.getInstance().getCurrentTarget() != null) {
                     mc.field_1724.method_5762((double)0.0F, 0.04, (double)0.0F);
                  }
               }
            }

         }
      }
   }

   @EventHandler
   public void onAttack(AttackEvent e) {
      if (mc.field_1724 != null && mc.method_1562() != null) {
         this.doCritical();
      }
   }

   public void doCritical() {
      if (mc.field_1724 != null && mc.method_1562() != null) {
         double x = mc.field_1724.method_23317();
         double y = mc.field_1724.method_23318();
         double z = mc.field_1724.method_23321();
         boolean onGround = mc.field_1724.method_24828();
         double eps = 1.0E-6;
         switch (this.mode.getSelected()) {
            case "Grim":
               if (!onGround) {
                  this.sendPos(x, y - eps, z, false);
                  this.sendPos(x, y + eps, z, false);
                  this.sendPos(x, y, z, false);
               }
               break;
            case "RWCollision":
               if (mc.field_1724.field_6235 == 0) {
                  this.sendPos(x, y + eps, z, true);
                  this.sendPos(x, y, z, false);
               }
               break;
            case "SpookyTime":
               if (!onGround) {
                  this.sendPos(x, y - eps, z, false);
               } else {
                  this.sendPos(x, y + eps, z, false);
                  this.sendPos(x, y, z, false);
               }
               break;
            case "ÐžÐ±Ñ‹Ñ‡Ð½Ñ‹Ðµ":
               if (!onGround) {
                  this.sendPos(x, y - eps, z, false);
               } else {
                  this.sendPos(x, y + eps, z, true);
                  this.sendPos(x, y, z, false);
               }
               break;
            case "NCP":
               double tiny = 2.71875E-7;
               this.sendPos(x, y + tiny, z, false);
               this.sendPos(x, y, z, false);
               break;
            case "UpdatedNCP":
               double tiny = 2.71875E-7;
               this.sendPos(x, y + tiny, z, false);
               this.sendPos(x, y, z, false);
               break;
            case "FunSky HVH":
               if (mc.field_1690 != null && mc.field_1690.field_1903 != null && mc.field_1690.field_1903.method_1434()) {
                  return;
               }

               double tiny = 2.71875E-7;
               this.sendPos(x, y + tiny, z, false);
               this.sendPos(x, y, z, false);
         }

      }
   }

   private void sendPos(double x, double y, double z, boolean onGround) {
      if (mc.method_1562() != null) {
         mc.method_1562().method_52787(new class_2828.class_2829(x, y, z, onGround, false));
      }
   }
}

