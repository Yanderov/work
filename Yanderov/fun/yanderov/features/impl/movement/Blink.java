package fun.Yanderov.features.impl.movement;

import fun.Yanderov.events.packet.PacketEvent;
import fun.Yanderov.events.player.TickEvent;
import fun.Yanderov.events.render.WorldRenderEvent;
import fun.Yanderov.features.impl.combat.Aura;
import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.features.module.setting.Setting;
import fun.Yanderov.features.module.setting.implement.BooleanSetting;
import fun.Yanderov.features.module.setting.implement.ColorSetting;
import fun.Yanderov.features.module.setting.implement.SelectSetting;
import fun.Yanderov.features.module.setting.implement.SliderSettings;
import fun.Yanderov.utils.client.Instance;
import fun.Yanderov.utils.client.managers.event.EventHandler;
import fun.Yanderov.utils.display.geometry.Render3D;
import fun.Yanderov.utils.interactions.interact.PlayerInteractionHelper;
import fun.Yanderov.utils.math.time.StopWatch;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import net.minecraft.class_1304;
import net.minecraft.class_1799;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_2596;
import net.minecraft.class_2678;
import net.minecraft.class_2724;
import net.minecraft.class_2799;
import net.minecraft.class_2824;
import net.minecraft.class_2799.class_2800;

public class Blink extends Module {
   private final List packets = new CopyOnWriteArrayList();
   private class_238 box;
   public static int tickStop = -1;
   private int flushCooldown = 0;
   private StopWatch timer = new StopWatch();
   private final BooleanSetting disableOnAttack = (new BooleanSetting("DisableOnAttack", "Ð’Ñ‹ÐºÐ»ÑŽÑ‡Ð¸Ñ‚ÑŒ Ð¿Ð¾ÑÐ»Ðµ Ð°Ñ‚Ð°ÐºÐ¸")).setValue(false);
   private final BooleanSetting disableOnKillAura = (new BooleanSetting("DisableOnKillAura", "Ð’Ñ‹ÐºÐ»ÑŽÑ‡Ð¸Ñ‚ÑŒ Ð¿Ñ€Ð¸ Ð²ÐºÐ»ÑŽÑ‡ÐµÐ½Ð¸Ð¸ Aura")).setValue(false);
   private final BooleanSetting pulseTimeActionDelay = (new BooleanSetting("PulseTimeActionDelay", "ÐÐ²Ñ‚Ð¾-Ð´ÐµÐ¹ÑÑ‚Ð²Ð¸Ðµ Ð¿Ð¾ Ñ‚Ð°Ð¹Ð¼ÐµÑ€Ñƒ")).setValue(false);
   private final SliderSettings pulseTimeMs = (new SliderSettings("PulseTimeMs", "Ð’Ñ€ÐµÐ¼Ñ Ð´Ð¾ Ð´ÐµÐ¹ÑÑ‚Ð²Ð¸Ñ (ms)")).setValue(1500.0F).range(0, 30000).visible(() -> this.pulseTimeActionDelay.isValue());
   private final BooleanSetting pulseTimeActionReset = (new BooleanSetting("PulseTimeActionReset", "ÐŸÐ¾ÑÐ»Ðµ Ñ‚Ð°Ð¹Ð¼ÐµÑ€Ð° Ð±Ð»Ð¸Ð½Ðº + Ð¿Ñ€Ð¾Ð´Ð¾Ð»Ð¶Ð¸Ñ‚ÑŒ")).setValue(false).visible(() -> this.pulseTimeActionDelay.isValue());
   private final SelectSetting renderEsp = (new SelectSetting("RenderEsp", "ÐžÑ‚Ð¾Ð±Ñ€Ð°Ð¶ÐµÐ½Ð¸Ðµ ESP")).value("Off", "Soul", "Chams", "Box").selected("Soul");
   private final BooleanSetting hideEspInFirstPerson = (new BooleanSetting("HideEspInFirstPerson", "Ð¡ÐºÑ€Ñ‹Ð²Ð°Ñ‚ÑŒ ESP Ð² 1 Ð»Ð¸Ñ†Ðµ")).setValue(false).visible(() -> !this.renderEsp.isSelected("Off"));
   private final ColorSetting chamsColor = (new ColorSetting("ChamsColor", "Ð¦Ð²ÐµÑ‚ ESP")).value(-2130706433).visible(() -> !this.renderEsp.isSelected("Off"));
   private final BooleanSetting visualSpinMethod = (new BooleanSetting("VisualSpinMethod", "ÐšÑ€ÑƒÑ‚Ð¸Ñ‚ÑŒ Ð²Ð¸Ð·ÑƒÐ°Ð»ÑŒÐ½ÑƒÑŽ Ð¼Ð¾Ð´ÐµÐ»ÑŒÐºÑƒ (Soul/Chams)")).setValue(false).visible(() -> this.renderEsp.isSelected("Soul") || this.renderEsp.isSelected("Chams"));
   private final SliderSettings spinSpeed = (new SliderSettings("SpinSpeed", "Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Ð²Ñ€Ð°Ñ‰ÐµÐ½Ð¸Ñ ESP")).setValue(1.0F).range(0.0F, 10.0F).visible(() -> this.visualSpinMethod.isValue() && (this.renderEsp.isSelected("Soul") || this.renderEsp.isSelected("Chams")));

   public Blink() {
      super("Blink", ModuleCategory.MOVEMENT);
      this.setup(new Setting[]{this.disableOnAttack, this.disableOnKillAura, this.pulseTimeActionDelay, this.pulseTimeMs, this.pulseTimeActionReset, this.renderEsp, this.hideEspInFirstPerson, this.chamsColor, this.visualSpinMethod, this.spinSpeed});
   }

   public void activate() {
      if (mc.field_1724 != null) {
         this.box = mc.field_1724.method_5829();
      }

      this.timer.reset();
   }

   public void deactivate() {
      this.flushAll();
      this.packets.clear();
      this.box = null;
      tickStop = -1;
   }

   private void flushAll() {
      if (!this.packets.isEmpty()) {
         this.packets.forEach(PlayerInteractionHelper::sendPacketWithOutEvent);
         this.packets.clear();
         if (mc.field_1724 != null) {
            this.box = mc.field_1724.method_5829();
         }

      }
   }

   @EventHandler
   public void tick(TickEvent e) {
      if (!PlayerInteractionHelper.nullCheck()) {
         if (this.disableOnKillAura.isValue()) {
            Aura aura = (Aura)Instance.get(Aura.class);
            if (aura != null && aura.isState()) {
               this.setState(false);
               return;
            }
         }

         if (this.pulseTimeActionDelay.isValue() && this.timer.finished((double)this.pulseTimeMs.getValue())) {
            if (!this.pulseTimeActionReset.isValue()) {
               this.setState(false);
               return;
            }

            this.flushAll();
            this.timer.reset();
         }

         --tickStop;
         if (tickStop >= 0 && !this.packets.isEmpty()) {
            if (mc.field_1724 != null) {
               this.box = mc.field_1724.method_5829();
            }

            this.flushAll();
         }

      }
   }

   @EventHandler
   public void onPacket(PacketEvent e) {
      if (!PlayerInteractionHelper.nullCheck()) {
         class_2596<?> packet = e.getPacket();
         Objects.requireNonNull(packet);
         class_2596 var3 = packet;
         byte var4 = 0;

         while(true) {
            //$FF: var4->value
            //0->net/minecraft/class_2724
            //1->net/minecraft/class_2678
            //2->net/minecraft/class_2799
            switch (var3.typeSwitch<invokedynamic>(var3, var4)) {
               case 0:
                  class_2724 respawn = (class_2724)var3;
                  this.setState(false);
                  return;
               case 1:
                  class_2678 join = (class_2678)var3;
                  this.setState(false);
                  return;
               case 2:
                  class_2799 status = (class_2799)var3;
                  if (!status.method_12119().equals(class_2800.field_12774)) {
                     var4 = 3;
                     break;
                  }

                  this.setState(false);
                  return;
               default:
                  if (e.isSend() && tickStop < 0) {
                     if (this.disableOnAttack.isValue() && packet instanceof class_2824) {
                        class_2824 interact = (class_2824)packet;

                        try {
                           Object type = this.getInteractTypeCompat(interact);
                           if (type != null && String.valueOf(type).toUpperCase().contains("ATTACK")) {
                              this.setState(false);
                              return;
                           }
                        } catch (Throwable var10) {
                        }
                     }

                     this.packets.add(packet);
                     e.cancel();
                  }

                  return;
            }
         }
      }
   }

   @EventHandler
   public void onWorldRender(WorldRenderEvent e) {
      if (this.isState()) {
         if (this.box != null) {
            if (!this.renderEsp.isSelected("Off")) {
               if (!this.hideEspInFirstPerson.isValue() || !mc.field_1690.method_31044().method_31034()) {
                  class_243 center = new class_243((this.box.field_1323 + this.box.field_1320) * (double)0.5F, this.box.field_1322, (this.box.field_1321 + this.box.field_1324) * (double)0.5F);
                  if (this.renderEsp.isSelected("Box")) {
                     Render3D.drawBox(this.box, this.chamsColor.getColor(), 1.5F);
                  } else if (mc.field_1724 != null) {
                     long now = System.currentTimeMillis();
                     float yaw = mc.field_1724.method_36454();
                     if (this.visualSpinMethod.isValue()) {
                        float speed = this.spinSpeed.getValue();
                        if (speed > 0.0F) {
                           float spin = (float)(now % 3600L) / 10.0F * speed;
                           yaw += spin;
                        }
                     }

                     if (this.renderEsp.isSelected("Soul")) {
                        Render3D.drawEntity(mc.field_1724, center, yaw, 200, e.getStack(), e.getPartialTicks());
                     } else {
                        if (this.renderEsp.isSelected("Chams")) {
                           int rgba = this.chamsColor.getColor();
                           class_1799 head = mc.field_1724.method_6118(class_1304.field_6169);
                           class_1799 chest = mc.field_1724.method_6118(class_1304.field_6174);
                           class_1799 legs = mc.field_1724.method_6118(class_1304.field_6172);
                           class_1799 feet = mc.field_1724.method_6118(class_1304.field_6166);
                           mc.field_1724.method_5673(class_1304.field_6169, class_1799.field_8037);
                           mc.field_1724.method_5673(class_1304.field_6174, class_1799.field_8037);
                           mc.field_1724.method_5673(class_1304.field_6172, class_1799.field_8037);
                           mc.field_1724.method_5673(class_1304.field_6166, class_1799.field_8037);

                           try {
                              Render3D.drawEntityChams(mc.field_1724, center, yaw, rgba, e.getStack(), e.getPartialTicks());
                           } finally {
                              mc.field_1724.method_5673(class_1304.field_6169, head);
                              mc.field_1724.method_5673(class_1304.field_6174, chest);
                              mc.field_1724.method_5673(class_1304.field_6172, legs);
                              mc.field_1724.method_5673(class_1304.field_6166, feet);
                           }
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
}

