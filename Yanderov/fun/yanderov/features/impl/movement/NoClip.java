package fun.Yanderov.features.impl.movement;

import fun.Yanderov.events.block.BlockCollisionEvent;
import fun.Yanderov.events.packet.PacketEvent;
import fun.Yanderov.events.player.TickEvent;
import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.features.module.setting.Setting;
import fun.Yanderov.features.module.setting.implement.BooleanSetting;
import fun.Yanderov.features.module.setting.implement.SliderSettings;
import fun.Yanderov.utils.client.managers.event.EventHandler;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_2596;
import net.minecraft.class_2680;
import net.minecraft.class_2708;
import net.minecraft.class_2827;
import net.minecraft.class_2828;
import net.minecraft.class_634;
import net.minecraft.class_6374;

public class NoClip extends Module {
   private List packets = new CopyOnWriteArrayList();
   private class_238 box;
   private int tickCounter;
   private final BooleanSetting customSpeed = (new BooleanSetting("Custom Speed", "ÐšÐ°ÑÑ‚Ð¾Ð¼Ð½Ð°Ñ Ð³Ð¾Ñ€Ð¸Ð·Ð¾Ð½Ñ‚Ð°Ð»ÑŒÐ½Ð°Ñ ÑÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Ð²Ð¾ Ð²Ñ€ÐµÐ¼Ñ NoClip")).setValue(false);
   private final SliderSettings phaseSpeed;
   private final SliderSettings dashDistance;
   private final SliderSettings dashCount;
   private final SliderSettings dashInterval;
   private long lastDashTime;

   public NoClip() {
      super("No Clip", ModuleCategory.MOVEMENT);
      SliderSettings var10001 = (new SliderSettings("phaseSpeed", "Ð“Ð¾Ñ€Ð¸Ð·Ð¾Ð½Ñ‚Ð°Ð»ÑŒÐ½Ð°Ñ ÑÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Ð¿Ñ€Ð¸ Ñ„Ð°Ð·Ðµ")).range(0.05F, 0.6F).setValue(0.3F);
      BooleanSetting var10002 = this.customSpeed;
      Objects.requireNonNull(var10002);
      this.phaseSpeed = var10001.visible(var10002::isValue);
      this.dashDistance = (new SliderSettings("dashDistance", "Ð Ð°ÑÑÑ‚Ð¾ÑÐ½Ð¸Ðµ Ñ„ÐµÐ¹Ðº-Ñ€Ñ‹Ð²ÐºÐ° Ð¿Ñ€Ð¸ ÐºÐ¾Ñ€Ñ€ÐµÐºÑ†Ð¸Ð¸ (Ð¿Ð¾ X/Z)")).range(10.0F, 2000.0F).setValue(1000.0F);
      this.dashCount = (new SliderSettings("dashCount", "Ð§Ð¸ÑÐ»Ð¾ Ñ„ÐµÐ¹Ðº-Ñ€Ñ‹Ð²ÐºÐ¾Ð² Ð½Ð° Ð¾Ð´Ð½Ñƒ ÐºÐ¾Ñ€Ñ€ÐµÐºÑ†Ð¸ÑŽ")).range(1.0F, 5.0F).setValue(1.0F);
      this.dashInterval = (new SliderSettings("dashInterval", "ÐœÐ¸Ð½Ð¸Ð¼Ð°Ð»ÑŒÐ½Ñ‹Ð¹ Ð¸Ð½Ñ‚ÐµÑ€Ð²Ð°Ð» Ð¼ÐµÐ¶Ð´Ñƒ ÑÐµÑ€Ð¸ÑÐ¼Ð¸ Ñ€Ñ‹Ð²ÐºÐ¾Ð² (Ð¼Ñ)")).range(0.0F, 1000.0F).setValue(0.0F);
      this.lastDashTime = 0L;
      this.setup(new Setting[]{this.customSpeed, this.phaseSpeed, this.dashDistance, this.dashCount, this.dashInterval});
   }

   private boolean shouldPhase() {
      if (mc.field_1724 != null && mc.field_1687 != null) {
         class_238 hitbox = mc.field_1724.method_5829();
         class_2338 min = new class_2338((int)Math.floor(hitbox.field_1323), (int)Math.floor(hitbox.field_1322), (int)Math.floor(hitbox.field_1321));
         class_2338 max = new class_2338((int)Math.floor(hitbox.field_1320), (int)Math.floor(hitbox.field_1325), (int)Math.floor(hitbox.field_1324));

         for(int x = min.method_10263(); x <= max.method_10263(); ++x) {
            for(int y = min.method_10264(); y <= max.method_10264(); ++y) {
               for(int z = min.method_10260(); z <= max.method_10260(); ++z) {
                  class_2338 pos = new class_2338(x, y, z);
                  class_2680 state = mc.field_1687.method_8320(pos);
                  if (!state.method_26215() && mc.field_1687.method_8320(pos).method_26220(mc.field_1687, pos).method_1090().stream().anyMatch((box) -> box.method_994(hitbox.method_989((double)(-pos.method_10263()), (double)(-pos.method_10264()), (double)(-pos.method_10260()))))) {
                     return true;
                  }
               }
            }
         }

         return false;
      } else {
         return false;
      }
   }

   private void resumePackets() {
      if (mc.field_1724 != null && mc.field_1687 != null && !this.state) {
         if (!this.packets.isEmpty()) {
            for(class_2596 packet : new ArrayList(this.packets)) {
               mc.method_1562().method_52787(packet);
            }

            this.packets.clear();
            this.box = mc.field_1724.method_5829();
         }

      }
   }

   @EventHandler
   public void onCollide(BlockCollisionEvent e) {
      if (this.state) {
         class_2338 playerPos = class_2338.method_49638(mc.field_1724.method_19538());
         if (!e.getBlockPos().equals(playerPos.method_10074())) {
            e.setState(class_2246.field_10124.method_9564());
         }
      }
   }

   private void adjustToCustomSpeed() {
      if (mc.field_1724 != null && this.state && this.customSpeed.isValue()) {
         class_243 motion = mc.field_1724.method_18798();
         double horizontalSpeed = Math.sqrt(motion.field_1352 * motion.field_1352 + motion.field_1350 * motion.field_1350);
         if (horizontalSpeed > (double)0.0F) {
            double targetSpeed = (double)this.phaseSpeed.getValue();
            double scale = targetSpeed / horizontalSpeed;
            mc.field_1724.method_18800(motion.field_1352 * scale, motion.field_1351, motion.field_1350 * scale);
         }

      }
   }

   @EventHandler
   public void onTick(TickEvent e) {
      if (this.state && mc.field_1724 != null) {
         if (this.shouldPhase() && this.customSpeed.isValue()) {
            this.adjustToCustomSpeed();
         }

      }
   }

   @EventHandler
   public void onPacket(PacketEvent event) {
      if (this.state && mc.field_1724 != null) {
         double x = mc.field_1724.method_23317();
         double y = mc.field_1724.method_23318();
         double z = mc.field_1724.method_23321();
         float yaw = mc.field_1724.method_36454();
         float pitch = mc.field_1724.method_36455();
         if (event.getType() == PacketEvent.Type.SEND) {
            class_2596<?> p = event.getPacket();
            if (this.shouldPhase() && !(p instanceof class_2827) && !(p instanceof class_6374)) {
               this.packets.add(p);
               event.cancel();
            }
         }

         if (event.getType() == PacketEvent.Type.RECEIVE && event.getPacket() instanceof class_2708) {
            this.resumePackets();
            long now = System.currentTimeMillis();
            float dist = this.dashDistance.getValue();
            int count = Math.max(1, (int)this.dashCount.getValue());
            long interval = (long)this.dashInterval.getValue();
            if (now - this.lastDashTime >= interval) {
               for(int i = 0; i < count; ++i) {
                  ((class_634)Objects.requireNonNull(mc.method_1562())).method_52787(new class_2828.class_2830(x - (double)dist, y, z - (double)dist, yaw, pitch, false, false));
                  mc.method_1562().method_52787(new class_2828.class_2830(x, y, z, yaw, pitch, mc.field_1724.method_24828(), false));
               }

               this.lastDashTime = now;
            }

            mc.field_1724.field_3944.method_52787(new class_2828.class_2830(mc.field_1724.method_23317(), mc.field_1724.method_23318(), mc.field_1724.method_23321(), mc.field_1724.method_36454(), mc.field_1724.method_36455(), mc.field_1724.method_24828(), false));
         }

      }
   }

   public void deactivate() {
      super.deactivate();
      this.resumePackets();
      this.box = null;
      this.tickCounter = 0;
   }

   public List getPackets() {
      return this.packets;
   }

   public class_238 getBox() {
      return this.box;
   }

   public int getTickCounter() {
      return this.tickCounter;
   }

   public BooleanSetting getCustomSpeed() {
      return this.customSpeed;
   }

   public SliderSettings getPhaseSpeed() {
      return this.phaseSpeed;
   }

   public SliderSettings getDashDistance() {
      return this.dashDistance;
   }

   public SliderSettings getDashCount() {
      return this.dashCount;
   }

   public SliderSettings getDashInterval() {
      return this.dashInterval;
   }

   public long getLastDashTime() {
      return this.lastDashTime;
   }
}

