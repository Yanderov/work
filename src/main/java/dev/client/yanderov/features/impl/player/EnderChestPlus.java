package dev.client.yanderov.features.impl.player;

import dev.client.yanderov.display.hud.Notifications;
import dev.client.yanderov.events.container.CloseScreenEvent;
import dev.client.yanderov.events.container.SetScreenEvent;
import dev.client.yanderov.events.keyboard.KeyEvent;
import dev.client.yanderov.events.packet.PacketEvent;
import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.features.module.ModuleCategory;
import dev.client.yanderov.features.module.setting.Setting;
import dev.client.yanderov.features.module.setting.implement.BindSetting;
import dev.client.yanderov.utils.client.managers.event.EventHandler;
import dev.client.yanderov.utils.interactions.inv.InventoryTask;
import java.util.List;
import java.util.Objects;
import net.minecraft.class_124;
import net.minecraft.class_1268;
import net.minecraft.class_1713;
import net.minecraft.class_1735;
import net.minecraft.class_2561;
import net.minecraft.class_2596;
import net.minecraft.class_2645;
import net.minecraft.class_2678;
import net.minecraft.class_2724;
import net.minecraft.class_2815;
import net.minecraft.class_2846;
import net.minecraft.class_3944;
import net.minecraft.class_437;
import net.minecraft.class_465;
import net.minecraft.class_476;
import net.minecraft.class_490;
import net.minecraft.class_2846.class_2847;

public class EnderChestPlus extends Module {
   private class_465 screen;
   private final BindSetting bindSetting = new BindSetting("ÐšÐ½Ð¾Ð¿ÐºÐ° ÑÐºÐ»Ð°Ð´Ñ‹Ð²Ð°Ð½Ð¸Ñ Ð¿Ñ€ÐµÐ´Ð¼ÐµÑ‚Ð¾Ð²", "ÐŸÐ¾Ð¼ÐµÑ‰Ð°ÐµÑ‚ Ð²ÑÐµ Ð¿Ñ€ÐµÐ´Ð¼ÐµÑ‚Ñ‹ Ð² ÑÐ½Ð´ÐµÑ€-ÑÑƒÐ½Ð´ÑƒÐº");

   public EnderChestPlus() {
      super("EnderChestPlus", "Ender-Chest Plus", ModuleCategory.PLAYER);
      this.setup(new Setting[]{this.bindSetting});
   }

   public void deactivate() {
      if (this.screen != null) {
         this.screen = null;
         InventoryTask.closeScreen(true);
         Notifications.getInstance().addList("Ender Chest - " + String.valueOf(class_124.field_1061) + "Ð·Ð°ÐºÑ€Ñ‹Ñ‚", 5000L);
      }

   }

   @EventHandler
   public void onKey(KeyEvent e) {
      if (e.isKeyDown(this.bindSetting.getKey()) && this.screen != null) {
         List<class_1735> slots = mc.field_1724.field_7512.field_7761;
         slots.stream().filter((s) -> s.field_7874 < slots.size() - 36 && s.method_7677().method_7960()).findFirst().ifPresent((s) -> InventoryTask.swapHand(s, class_1268.field_5810, false));
         slots.stream().filter((s) -> s.field_7874 >= slots.size() - 36 && !s.method_7677().method_7960()).forEach((s) -> InventoryTask.clickSlot(s, 0, class_1713.field_7794, false));
      }

   }

   @EventHandler
   public void onPacket(PacketEvent e) {
      if (this.screen != null && mc.field_1724 != null) {
         class_2596 var10000 = e.getPacket();
         Objects.requireNonNull(var10000);
         class_2596 var8 = var10000;
         byte var9 = 0;

         while(true) {
            //$FF: var9->value
            //0->net/minecraft/class_2678
            //1->net/minecraft/class_3944
            //2->net/minecraft/class_2645
            //3->net/minecraft/class_2724
            //4->net/minecraft/class_2815
            //5->net/minecraft/class_2846
            // TODO: Fix switch statement for var8
        if (var8 != null) {
            // // case 0:
            // class_2678 join = (class_2678)var8;
            // this.deactivate();
            // return;
            // // case 1:
            // class_3944 open = (class_3944)var8;
            // this.deactivate();
            // return;
            // // case 2:
            // class_2645 close = (class_2645)var8;
            // this.deactivate();
            // return;
            // // case 3:
            // class_2724 respawn = (class_2724)var8;
            // this.deactivate();
            // return;
            // // case 4:
            // class_2815 close = (class_2815)var8;
            // e.cancel();
            // return;
            // // case 5:
            // class_2846 player = (class_2846)var8;
            // if (!player.method_12363().equals(class_2847.field_12969)) {
            // var9 = 6;
            // break;
            // } else {
            // InventoryTask.swapHand(InventoryTask.mainHandSlot(), class_1268.field_5810, false);
            // e.cancel();
            // return;
            // }
            // // default:
            // return;
            }
         }
      }
   }

   @EventHandler
   public void onSetScreen(SetScreenEvent e) {
      if (e.getScreen() instanceof class_490 && this.screen != null) {
         e.setScreen(this.screen);
      }

   }

   @EventHandler
   public void onCloseScreen(CloseScreenEvent e) {
      class_437 var3 = e.getScreen();
      if (var3 instanceof class_476 scr) {
         if (scr.method_25440().getString().contains(class_2561.method_43471("container.enderchest").getString())) {
            this.screen = scr;
         }
      }

      if (this.screen != null) {
         mc.method_1507((class_437)null);
         e.cancel();
      }

   }
}

