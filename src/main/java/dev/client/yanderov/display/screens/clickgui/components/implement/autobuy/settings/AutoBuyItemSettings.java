package dev.client.yanderov.display.screens.clickgui.components.implement.autobuy.settings;

import net.minecraft.class_1792;
import net.minecraft.class_1802;

public class AutoBuyItemSettings {
   private int buyBelow;
   private int sellAbove;
   private int minQuantity;
   private final boolean canHaveQuantity;
   private final String itemName;

   public AutoBuyItemSettings(int defaultBuyBelow, class_1792 material, String itemName) {
      this.itemName = itemName;
      this.buyBelow = defaultBuyBelow;
      this.sellAbove = (int)((double)defaultBuyBelow * (double)1.5F);
      this.minQuantity = 1;
      this.canHaveQuantity = this.canItemStack(material);
   }

   private boolean canItemStack(class_1792 material) {
      if (material != class_1802.field_22027 && material != class_1802.field_22028 && material != class_1802.field_22029 && material != class_1802.field_22030 && material != class_1802.field_22022 && material != class_1802.field_22024 && material != class_1802.field_8399 && material != class_1802.field_8547 && material != class_1802.field_49814 && material != class_1802.field_8833 && material != class_1802.field_8288) {
         return material.method_7882() > 1;
      } else {
         return false;
      }
   }

   public int getBuyBelow() {
      return this.buyBelow;
   }

   public int getSellAbove() {
      return this.sellAbove;
   }

   public int getMinQuantity() {
      return this.minQuantity;
   }

   public boolean isCanHaveQuantity() {
      return this.canHaveQuantity;
   }

   public String getItemName() {
      return this.itemName;
   }

   public void setBuyBelow(int buyBelow) {
      this.buyBelow = buyBelow;
   }

   public void setSellAbove(int sellAbove) {
      this.sellAbove = sellAbove;
   }

   public void setMinQuantity(int minQuantity) {
      this.minQuantity = minQuantity;
   }
}

