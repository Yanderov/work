package dev.client.yanderov.features.impl.combat;

import dev.client.yanderov.events.player.TickEvent;
import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.features.module.ModuleCategory;
import dev.client.yanderov.features.module.setting.Setting;
import dev.client.yanderov.features.module.setting.implement.BooleanSetting;
import dev.client.yanderov.features.module.setting.implement.SliderSettings;
import dev.client.yanderov.utils.client.managers.event.EventHandler;
import dev.client.yanderov.utils.display.interfaces.QuickImports;
import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_1511;
import net.minecraft.class_1802;
import net.minecraft.class_2246;
import net.minecraft.class_2350;
import net.minecraft.class_239;
import net.minecraft.class_3965;
import net.minecraft.class_3966;

public class CrystalTap extends Module implements QuickImports {
   private final BooleanSetting autoAddCrystal = (new BooleanSetting("ÐÐ²Ñ‚Ð¾Ð¼Ð°Ñ‚Ð¸Ñ‡ÐµÑÐºÐ¸ ÑÑ‚Ð°Ð²Ð¸Ñ‚ÑŒ ÐºÑ€Ð¸ÑÑ‚Ð°Ð»Ð»", "ÐÐ²Ñ‚Ð¾-ÑƒÑÑ‚Ð°Ð½Ð¾Ð²ÐºÐ° ÐºÑ€Ð¸ÑÑ‚Ð°Ð»Ð»Ð° Ð½Ð° Ð¾Ð±ÑÐ¸Ð´Ð¸Ð°Ð½ Ð¿Ð¾Ð´ Ð¿Ñ€Ð¸Ñ†ÐµÐ»Ð¾Ð¼")).setValue(true);
   private final SliderSettings addCooldown = (new SliderSettings("Ð—Ð°Ð´ÐµÑ€Ð¶ÐºÐ° Ð½Ð° ÑƒÑÑ‚Ð°Ð½Ð¾Ð²ÐºÑƒ (Ñ‚Ð¸Ðº)", "ÐšÑƒÐ»Ð´Ð°ÑƒÐ½ Ð¼ÐµÐ¶Ð´Ñƒ ÑƒÑÑ‚Ð°Ð½Ð¾Ð²ÐºÐ°Ð¼Ð¸")).setValue(0.0F).range(0.0F, 20.0F);
   private final SliderSettings attackCooldown = (new SliderSettings("Ð—Ð°Ð´ÐµÑ€Ð¶ÐºÐ° Ð½Ð° Ð°Ñ‚Ð°ÐºÑƒ (Ñ‚Ð¸Ðº)", "ÐšÑƒÐ»Ð´Ð°ÑƒÐ½ Ð¼ÐµÐ¶Ð´Ñƒ Ð°Ñ‚Ð°ÐºÐ°Ð¼Ð¸")).setValue(0.0F).range(0.0F, 20.0F);
   private int attackCrystalCooldown = 0;
   private int addCrystalCooldown = 0;

   public CrystalTap() {
      super("CrystalTap", ModuleCategory.COMBAT);
      this.setup(new Setting[]{this.autoAddCrystal, this.addCooldown, this.attackCooldown});
   }

   @EventHandler
   public void onTick(TickEvent e) {
      if (this.isState()) {
         if (e.getType() == 0) {
            if (mc.field_1687 != null && mc.field_1724 != null && mc.field_1761 != null) {
               if (this.autoAddCrystal.isValue()) {
                  this.tryPlaceCrystal();
               }

               this.tryAttackCrystal();
            }
         }
      }
   }

   private void tryPlaceCrystal() {
      class_239 var2 = mc.field_1765;
      if (var2 instanceof class_3965 obsidianTarget) {
         if (mc.field_1687.method_8320(obsidianTarget.method_17777()).method_26204() == class_2246.field_10540) {
            for(class_1297 entity : mc.field_1687.method_18112()) {
               if (entity instanceof class_1511) {
                  class_1511 crystal = (class_1511)entity;
                  if (crystal.method_24515().method_10074().equals(obsidianTarget.method_17777())) {
                     return;
                  }
               }
            }

            if (mc.field_1724.method_6047().method_7909() == class_1802.field_8301) {
               class_2350 side = obsidianTarget.method_17780();
               class_3965 crystalHit = new class_3965(obsidianTarget.method_17784(), side, obsidianTarget.method_17777(), true);
               if (this.addCrystalCooldown >= (int)this.addCooldown.getValue()) {
                  mc.field_1761.method_2896(mc.field_1724, class_1268.field_5808, crystalHit);
                  this.addCrystalCooldown = 0;
               } else {
                  ++this.addCrystalCooldown;
               }

            }
         }
      }
   }

   private void tryAttackCrystal() {
      class_239 var2 = mc.field_1765;
      if (var2 instanceof class_3966 hit) {
         if (hit.method_17782() instanceof class_1511) {
            if (this.attackCrystalCooldown >= (int)this.attackCooldown.getValue()) {
               mc.field_1761.method_2918(mc.field_1724, hit.method_17782());
               mc.field_1724.method_6104(class_1268.field_5808);
               this.attackCrystalCooldown = 0;
            } else {
               ++this.attackCrystalCooldown;
            }
         }
      }

   }

   public BooleanSetting getAutoAddCrystal() {
      return this.autoAddCrystal;
   }

   public SliderSettings getAddCooldown() {
      return this.addCooldown;
   }

   public SliderSettings getAttackCooldown() {
      return this.attackCooldown;
   }

   public int getAttackCrystalCooldown() {
      return this.attackCrystalCooldown;
   }

   public int getAddCrystalCooldown() {
      return this.addCrystalCooldown;
   }
}

