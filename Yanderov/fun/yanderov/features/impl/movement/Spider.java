package fun.Yanderov.features.impl.movement;

import fun.Yanderov.events.player.PostTickEvent;
import fun.Yanderov.events.player.RotationUpdateEvent;
import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.features.module.setting.Setting;
import fun.Yanderov.features.module.setting.implement.BooleanSetting;
import fun.Yanderov.features.module.setting.implement.SelectSetting;
import fun.Yanderov.utils.client.managers.event.EventHandler;
import fun.Yanderov.utils.features.aura.rotations.impl.SnapAngle;
import fun.Yanderov.utils.features.aura.utils.MathAngle;
import fun.Yanderov.utils.features.aura.warp.Turns;
import fun.Yanderov.utils.features.aura.warp.TurnsConfig;
import fun.Yanderov.utils.features.aura.warp.TurnsConnection;
import fun.Yanderov.utils.interactions.interact.PlayerInteractionHelper;
import fun.Yanderov.utils.interactions.inv.InventoryTask;
import fun.Yanderov.utils.interactions.simulate.PlayerSimulation;
import fun.Yanderov.utils.math.script.Script;
import fun.Yanderov.utils.math.task.TaskPriority;
import fun.Yanderov.utils.math.time.StopWatch;
import java.util.Objects;
import java.util.stream.Stream;
import net.minecraft.class_1268;
import net.minecraft.class_1747;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_238;
import net.minecraft.class_239;
import net.minecraft.class_243;
import net.minecraft.class_265;
import net.minecraft.class_2879;
import net.minecraft.class_2886;
import net.minecraft.class_3965;

public class Spider extends Module {
   private final Script script = new Script();
   private final StopWatch stopWatch = new StopWatch();
   private final SelectSetting mode = (new SelectSetting("Ð ÐµÐ¶Ð¸Ð¼", "Ð’Ñ‹Ð±Ð¸Ñ€Ð°ÐµÑ‚ Ñ€ÐµÐ¶Ð¸Ð¼")).value("SpookyTime", "FunTime", "Slime Block", "Water Bucket", "Head").selected("Slime Block");
   private final BooleanSetting onlyJump = (new BooleanSetting("ÐŸÑ€Ð¸ Ð¿Ñ€Ñ‹Ð¶ÐºÐµ", "Ð Ð°Ð±Ð¾Ñ‚Ð°ÐµÑ‚ Ñ‚Ð¾Ð»ÑŒÐºÐ¾ Ð¿Ñ€Ð¸ Ð·Ð°Ð¶Ð°Ñ‚Ð¸Ð¸ Ð¿Ñ€Ñ‹Ð¶ÐºÐ°")).setValue(false);
   private final int useItemSequence = 0;
   private final double lastWaterY = (double)0.0F;
   private final long lastWaterPlaceTime = 0L;
   private int cooldown;
   private boolean startSetPitch = false;

   public Spider() {
      super("Spider", ModuleCategory.MOVEMENT);
      this.setup(new Setting[]{this.mode, this.onlyJump});
   }

   private class_2248 getBlockState(class_2338 blockPos) {
      return mc.field_1687.method_8320(blockPos).method_26204();
   }

   public void deactivate() {
      if (this.mode.isSelected("Slime Block")) {
         mc.field_1690.field_1903.method_23481(false);
      }

   }

   @EventHandler
   public void onPostTick(PostTickEvent e) {
      if (!this.onlyJump.isValue() || mc.field_1690.field_1903.method_1434()) {
         if (this.mode.isSelected("FunTime")) {
            if (mc.field_1690.field_1903.method_1434()) {
               return;
            }

            class_238 playerBox = mc.field_1724.method_5829().method_1014(-0.001);
            class_238 box = new class_238(playerBox.field_1323, playerBox.field_1322, playerBox.field_1321, playerBox.field_1320, playerBox.field_1322 + (double)0.5F, playerBox.field_1324);
            if (this.stopWatch.finished((double)400.0F) && PlayerInteractionHelper.isBox(box, this::hasCollision)) {
               box = new class_238(playerBox.field_1323 - 0.3, playerBox.field_1322 + (double)1.0F, playerBox.field_1321 - 0.3, playerBox.field_1320, playerBox.field_1325, playerBox.field_1324);
               if (PlayerInteractionHelper.isBox(box, this::hasCollision)) {
                  mc.field_1724.method_24830(true);
                  mc.field_1724.field_18276.field_1351 = 0.6;
               } else {
                  mc.field_1724.method_24830(true);
                  mc.field_1724.method_6043();
               }
            }
         }

         if (this.mode.isSelected("Head")) {
            if (!mc.field_1690.field_1903.method_1434()) {
               return;
            }

            if (!this.stopWatch.finished((double)200.0F)) {
               return;
            }

            class_2338 anchor = this.findPos();
            int headSlot = InventoryTask.getHotbarSlotId((i) -> mc.field_1724.method_31548().method_5438(i).method_7909() == class_1802.field_8575);
            boolean triedPlace = false;
            if (headSlot != -1 && !anchor.equals(class_2338.field_10980)) {
               class_243 center = anchor.method_46558();
               class_2350 face = class_2350.method_10142(center.field_1352 - mc.field_1724.method_23317(), center.field_1351 - mc.field_1724.method_23318(), center.field_1350 - mc.field_1724.method_23321());
               class_243 hitPos = center.method_1019(class_243.method_24954(face.method_62675()).method_1021((double)0.5F));
               Turns angle = MathAngle.calculateAngle(hitPos.method_1020((new class_243(face.method_62675())).method_1021((double)0.1F)));
               Turns.VecRotation vecRotation = new Turns.VecRotation(angle, angle.toVector());
               TurnsConnection.INSTANCE.rotateTo(vecRotation, mc.field_1724, 1, new TurnsConfig(new SnapAngle(), true, true), TaskPriority.HIGH_IMPORTANCE_1, this);
               int prev = mc.field_1724.method_31548().field_7545;
               mc.field_1724.method_31548().field_7545 = headSlot;
               class_3965 hit = new class_3965(hitPos, face, anchor, false);
               mc.field_1761.method_2896(mc.field_1724, class_1268.field_5808, hit);
               mc.field_1724.field_3944.method_52787(new class_2879(class_1268.field_5808));
               mc.field_1724.method_31548().field_7545 = prev;
               this.stopWatch.reset();
               triedPlace = true;
            }

            if (!triedPlace && mc.field_1724.field_5976) {
               mc.field_1724.method_24830(true);
               mc.field_1724.method_18800(mc.field_1724.method_18798().field_1352, 0.42, mc.field_1724.method_18798().field_1350);
               this.stopWatch.reset();
            }
         }

         if (this.mode.isSelected("Water Bucket") && mc.field_1724.method_6047().method_7909() == class_1802.field_8705 && mc.field_1724.field_5976) {
            mc.field_1761.method_2919(mc.field_1724, class_1268.field_5808);
            mc.field_1724.method_6104(class_1268.field_5808);
            mc.field_1724.method_18800(mc.field_1724.method_18798().field_1352, 0.3, mc.field_1724.method_18798().field_1350);
         }

         if (this.mode.isSelected("SpookyTime") && this.stopWatch.finished((double)310.0F)) {
            if (mc.field_1724.method_6047().method_7909() == class_1802.field_8705 && mc.field_1724.field_5976) {
               mc.field_1724.field_3944.method_52787(new class_2886(class_1268.field_5808, 0, mc.field_1724.method_36454(), mc.field_1724.method_36455()));
               mc.field_1724.field_3944.method_52787(new class_2879(class_1268.field_5808));
               mc.field_1724.method_18800(mc.field_1724.method_18798().field_1352, 0.35, mc.field_1724.method_18798().field_1350);
            }

            this.stopWatch.reset();
         }

         if (this.mode.isSelected("Slime Block")) {
            class_2338 playerPos = mc.field_1724.method_24515();
            class_2338[] adjacentBlocks = new class_2338[]{playerPos.method_10078(), playerPos.method_10067(), playerPos.method_10095(), playerPos.method_10072()};
            boolean hasAdjacentSlime = false;
            class_2338[] crosshair = adjacentBlocks;
            int blockHit = adjacentBlocks.length;
            int face = 0;

            while(true) {
               if (face < blockHit) {
                  class_2338 pos = crosshair[face];
                  if (this.getBlockState(pos) != class_2246.field_10030) {
                     ++face;
                     continue;
                  }

                  hasAdjacentSlime = true;
               }

               if (!hasAdjacentSlime || !mc.field_1724.field_5976 || mc.field_1724.method_18798().field_1351 <= (double)-1.0F) {
                  return;
               }

               class_239 crosshair = mc.field_1765;
               if (crosshair instanceof class_3965) {
                  class_3965 blockHit = (class_3965)crosshair;
                  class_2350 face = blockHit.method_17780();
                  class_2338 targetPos = blockHit.method_17777();
                  if (this.getBlockState(targetPos) == class_2246.field_10124) {
                     return;
                  }

                  int slimeSlot = InventoryTask.getHotbarSlotId((i) -> mc.field_1724.method_31548().method_5438(i).method_7909() == class_1802.field_8828);
                  if (slimeSlot != -1) {
                     mc.field_1724.method_31548().method_61496(slimeSlot);
                     this.startSetPitch = true;
                     mc.field_1724.method_36457(54.0F);
                     class_3965 interaction = new class_3965(blockHit.method_17784(), face, targetPos, false);
                     mc.field_1761.method_2896(mc.field_1724, class_1268.field_5808, interaction);
                     mc.field_1724.method_6104(class_1268.field_5808);
                     if ((double)this.cooldown >= (double)0.5F) {
                        mc.field_1724.method_18800(mc.field_1724.method_18798().field_1352, 0.63, mc.field_1724.method_18798().field_1350);
                        this.cooldown = 0;
                     } else {
                        ++this.cooldown;
                     }
                  }
               }
               break;
            }
         }

      }
   }

   @EventHandler
   public void onRotationUpdate(RotationUpdateEvent e) {
      if (e.getType() == 0) {
         if (this.onlyJump.isValue() && !mc.field_1690.field_1903.method_1434()) {
            return;
         }

         if (this.mode.isSelected("Slime Block")) {
            boolean offHand = mc.field_1724.method_6079().method_7909() instanceof class_1747;
            int slotId = InventoryTask.getHotbarSlotId((i) -> mc.field_1724.method_31548().method_5438(i).method_7909() instanceof class_1747);
            class_2338 blockPos = this.findPos();
            if (this.script.isFinished() && (offHand || slotId != -1) && !blockPos.equals(class_2338.field_10980)) {
               class_1799 stack = offHand ? mc.field_1724.method_6079() : mc.field_1724.method_31548().method_5438(slotId);
               class_1268 hand = offHand ? class_1268.field_5810 : class_1268.field_5808;
               class_243 vec = blockPos.method_46558();
               class_2350 direction = class_2350.method_10142(vec.field_1352 - mc.field_1724.method_23317(), vec.field_1351 - mc.field_1724.method_23318(), vec.field_1350 - mc.field_1724.method_23321());
               Turns angle = MathAngle.calculateAngle(vec.method_1020((new class_243(direction.method_62675())).method_1021((double)0.1F)));
               Turns.VecRotation vecRotation = new Turns.VecRotation(angle, angle.toVector());
               TurnsConnection.INSTANCE.rotateTo(vecRotation, mc.field_1724, 1, new TurnsConfig(new SnapAngle(), true, true), TaskPriority.HIGH_IMPORTANCE_1, this);
               if (this.canPlace(stack)) {
                  int prev = mc.field_1724.field_7514.field_7545;
                  if (!offHand) {
                     mc.field_1724.field_7514.field_7545 = slotId;
                  }

                  mc.field_1761.method_2896(mc.field_1724, hand, new class_3965(vec, direction.method_10153(), blockPos, false));
                  mc.field_1724.field_3944.method_52787(new class_2879(hand));
                  if (!offHand) {
                     mc.field_1724.field_7514.field_7545 = prev;
                  }
               }
            }
         }
      }

   }

   private boolean canPlace(class_1799 stack) {
      class_2338 blockPos = this.getBlockPos();
      if (blockPos.method_10264() >= mc.field_1724.method_31478()) {
         return false;
      } else {
         class_1747 blockItem = (class_1747)stack.method_7909();
         class_265 shape = blockItem.method_7711().method_9564().method_26220(mc.field_1687, blockPos);
         if (shape.method_1110()) {
            return false;
         } else {
            class_238 box = shape.method_1107().method_996(blockPos);
            return !box.method_994(mc.field_1724.method_5829()) && box.method_994(PlayerSimulation.simulateLocalPlayer(4).boundingBox);
         }
      }
   }

   private class_2338 findPos() {
      class_2338 blockPos = this.getBlockPos();
      return mc.field_1687.method_8320(blockPos).method_51367() ? class_2338.field_10980 : (class_2338)Stream.of(blockPos.method_10067(), blockPos.method_10078(), blockPos.method_10072(), blockPos.method_10095()).filter((pos) -> mc.field_1687.method_8320(pos).method_51367()).findFirst().orElse(class_2338.field_10980);
   }

   private class_2338 getPlaceableWaterBlock() {
      class_2338 below = class_2338.method_49638(mc.field_1724.method_19538().method_1031((double)0.0F, -1.3, (double)0.0F));
      if (mc.field_1687.method_8320(below).method_26212(mc.field_1687, below)) {
         return below;
      } else {
         for(class_2350 dir : class_2350.values()) {
            class_2338 side = below.method_10093(dir);
            if (mc.field_1687.method_8320(side).method_26212(mc.field_1687, side)) {
               return side;
            }
         }

         return null;
      }
   }

   private class_2338 getBlockPos() {
      return class_2338.method_49638(PlayerSimulation.simulateLocalPlayer(1).pos.method_1031((double)0.0F, -0.001, (double)0.0F));
   }

   private boolean hasCollision(class_2338 blockPos) {
      return !mc.field_1687.method_8320(blockPos).method_26220(mc.field_1687, blockPos).method_1110();
   }

   public void setCooldown(int cooldown) {
      this.cooldown = cooldown;
   }

   public void setStartSetPitch(boolean startSetPitch) {
      this.startSetPitch = startSetPitch;
   }

   public Script getScript() {
      return this.script;
   }

   public StopWatch getStopWatch() {
      return this.stopWatch;
   }

   public SelectSetting getMode() {
      return this.mode;
   }

   public BooleanSetting getOnlyJump() {
      return this.onlyJump;
   }

   public int getUseItemSequence() {
      Objects.requireNonNull(this);
      return 0;
   }

   public double getLastWaterY() {
      Objects.requireNonNull(this);
      return (double)0.0F;
   }

   public long getLastWaterPlaceTime() {
      Objects.requireNonNull(this);
      return 0L;
   }

   public int getCooldown() {
      return this.cooldown;
   }

   public boolean isStartSetPitch() {
      return this.startSetPitch;
   }
}

