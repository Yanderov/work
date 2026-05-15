package dev.client.yanderov.features.impl.combat;

import dev.client.yanderov.events.player.TickEvent;
import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.features.module.ModuleCategory;
import dev.client.yanderov.features.module.setting.Setting;
import dev.client.yanderov.features.module.setting.implement.BooleanSetting;
import dev.client.yanderov.features.module.setting.implement.MultiSelectSetting;
import dev.client.yanderov.utils.client.managers.event.EventHandler;
import dev.client.yanderov.utils.client.packet.network.Network;
import dev.client.yanderov.utils.display.interfaces.QuickImports;
import dev.client.yanderov.utils.features.aura.warp.Turns;
import dev.client.yanderov.utils.features.aura.warp.TurnsConnection;
import dev.client.yanderov.utils.interactions.inv.InventoryTask;
import net.minecraft.class_1268;
import net.minecraft.class_1291;
import net.minecraft.class_1294;
import net.minecraft.class_1735;
import net.minecraft.class_6880;

public class AutoPotion extends Module implements QuickImports {
   private final MultiSelectSetting potions = (new MultiSelectSetting("Ð‘Ð°Ñ„Ñ„Ñ‹", "Ð’Ñ‹Ð±Ð¾Ñ€ Ð·ÐµÐ»Ð¸Ð¹ Ð´Ð»Ñ Ð°Ð²Ñ‚Ð¾ÐºÐ¸Ð´Ð°")).value("Ð¡Ð¸Ð»Ð°", "Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ", "ÐžÐ³Ð½ÐµÑÑ‚Ð¾Ð¹ÐºÐ¾ÑÑ‚ÑŒ").selected("Ð¡Ð¸Ð»Ð°", "Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ", "ÐžÐ³Ð½ÐµÑÑ‚Ð¾Ð¹ÐºÐ¾ÑÑ‚ÑŒ");
   private final BooleanSetting onlyPvp = (new BooleanSetting("Ð¢Ð¾Ð»ÑŒÐºÐ¾ PvP", "Ð˜ÑÐ¿Ð¾Ð»ÑŒÐ·Ð¾Ð²Ð°Ñ‚ÑŒ Ð·ÐµÐ»ÑŒÑ Ñ‚Ð¾Ð»ÑŒÐºÐ¾ Ð² PvP")).setValue(true);
   private final BooleanSetting autoDisable = (new BooleanSetting("ÐÐ²Ñ‚Ð¾ Ð²Ñ‹ÐºÐ»", "ÐžÑ‚ÐºÐ»ÑŽÑ‡Ð¸Ñ‚ÑŒ Ð¼Ð¾Ð´ÑƒÐ»ÑŒ Ð¿Ð¾ÑÐ»Ðµ Ñ†Ð¸ÐºÐ»Ð° Ð±Ð°Ñ„Ð¾Ð²")).setValue(true);
   private int potionStep = 0;
   private long lastThrowTime = 0L;
   private boolean cycleStarted = false;
   private Turns savedAngle = null;

   public AutoPotion() {
      super("AutoPotion", "AutoPotion", ModuleCategory.COMBAT);
      this.setup(new Setting[]{this.potions, this.onlyPvp, this.autoDisable});
   }

   @EventHandler
   public void onTick(TickEvent e) {
      if (mc.field_1724 != null) {
         if (!this.onlyPvp.isValue() || Network.isPvp()) {
            if (!this.cycleStarted) {
               this.potionStep = 0;
               this.lastThrowTime = 0L;
               this.cycleStarted = true;
               if (this.savedAngle == null) {
                  this.savedAngle = TurnsConnection.INSTANCE.getRotation();
               }

               Turns down = new Turns(this.savedAngle.getYaw(), 90.0F);
               TurnsConnection.INSTANCE.setRotation(down);
            }

            boolean anyPlanned = false;

            while(this.potionStep < AutoPotion.Buff.values().length) {
               Buff b = AutoPotion.Buff.values()[this.potionStep];
               if (this.potions.isSelected(b.settingName) && !mc.field_1724.method_6059(b.effect)) {
                  class_1735 slot = InventoryTask.getPotion(b.effect);
                  if (slot != null) {
                     anyPlanned = true;
                     long now = System.currentTimeMillis();
                     if (this.lastThrowTime == 0L || now - this.lastThrowTime >= 180L) {
                        this.throwPotionInstant(slot);
                        this.lastThrowTime = now;
                        ++this.potionStep;
                     }
                     break;
                  }

                  ++this.potionStep;
               } else {
                  ++this.potionStep;
               }
            }

            if (this.potionStep >= AutoPotion.Buff.values().length) {
               if (this.savedAngle != null) {
                  TurnsConnection.INSTANCE.setRotation(this.savedAngle);
                  this.savedAngle = null;
               }

               this.cycleStarted = false;
               if (this.autoDisable.isValue()) {
                  this.setState(false);
               }
            }

         }
      }
   }

   public void activate() {
      super.activate();
      this.potionStep = 0;
      this.lastThrowTime = 0L;
      this.cycleStarted = false;
      this.savedAngle = null;
   }

   public void deactivate() {
      if (this.savedAngle != null) {
         TurnsConnection.INSTANCE.setRotation(this.savedAngle);
         this.savedAngle = null;
      }

      this.cycleStarted = false;
      super.deactivate();
   }

   private void throwPotionInstant(class_1735 slot) {
      if (mc.field_1724 != null && mc.field_1761 != null) {
         InventoryTask.swapHand(slot, class_1268.field_5808, false, true);
         mc.field_1761.method_2919(mc.field_1724, class_1268.field_5808);
         InventoryTask.swapHand(slot, class_1268.field_5808, false, true);
      }
   }

   private static enum Buff {
      STRENGTH("Ð¡Ð¸Ð»Ð°", class_1294.field_5910),
      SPEED("Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ", class_1294.field_5904),
      FIRE_RESIST("ÐžÐ³Ð½ÐµÑÑ‚Ð¾Ð¹ÐºÐ¾ÑÑ‚ÑŒ", class_1294.field_5918);

      final String settingName;
      final class_6880 effect;

      private Buff(String settingName, class_6880 effect) {
         this.settingName = settingName;
         this.effect = effect;
      }

      // $FF: synthetic method
      private static Buff[] $values() {
         return new Buff[]{STRENGTH, SPEED, FIRE_RESIST};
      }
   }
}

