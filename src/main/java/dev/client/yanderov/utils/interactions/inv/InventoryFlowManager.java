package dev.client.yanderov.utils.interactions.inv;

import dev.client.yanderov.display.screens.clickgui.MenuScreen;
import dev.client.yanderov.events.player.InputEvent;
import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.features.module.ModuleCategory;
import dev.client.yanderov.utils.client.packet.network.Network;
import dev.client.yanderov.utils.display.interfaces.QuickImports;
import dev.client.yanderov.utils.features.aura.utils.MathAngle;
import dev.client.yanderov.utils.features.aura.warp.TurnsConfig;
import dev.client.yanderov.utils.features.aura.warp.TurnsConnection;
import dev.client.yanderov.utils.interactions.interact.PlayerInteractionHelper;
import dev.client.yanderov.utils.interactions.simulate.Simulations;
import dev.client.yanderov.utils.math.script.Script;
import dev.client.yanderov.utils.math.task.TaskPriority;
import java.util.List;
import java.util.Objects;
import net.minecraft.class_3675;
import net.minecraft.class_463;
import net.minecraft.class_471;
import net.minecraft.class_497;
import net.minecraft.class_498;

public final class InventoryFlowManager implements QuickImports {
   public static final List moveKeys;
   public static final Script script;
   public static final Script postScript;
   public static boolean canMove;

   public static void tick() {
      script.update();
   }

   public static void postMotion() {
      postScript.update();
   }

   public static void input(InputEvent e) {
      if (!canMove) {
         e.inputNone();
      }

   }

   public static void addTask(Runnable task) {
      if (script.isFinished() && Simulations.hasPlayerMovement()) {
         switch (Network.server) {
            case "FunTime":
               script.cleanup().addTickStep(0, () -> {
                  disableMoveKeys();
                  rotateToCamera();
               }).addTickStep(1, () -> {
                  task.run();
                  enableMoveKeys();
               });
               return;
            case "ReallyWorld":
               if (mc.field_1724.method_24828()) {
                  Script var3 = script.cleanup().addTickStep(0, InventoryFlowManager::disableMoveKeys).addTickStep(2, InventoryFlowManager::rotateToCamera);
                  Objects.requireNonNull(task);
                  var3.addTickStep(3, task::run).addTickStep(4, InventoryFlowManager::enableMoveKeys);
                  return;
               }
               break;
            case "SpookyTime":
            case "CopyTime":
               Script var10000 = script.cleanup().addTickStep(0, () -> {
                  disableMoveKeys();
                  rotateToCamera();
               });
               Objects.requireNonNull(task);
               var10000.addTickStep(1, task::run).addTickStep(2, InventoryFlowManager::enableMoveKeys);
               return;
         }
      }

      script.addTickStep(0, InventoryFlowManager::rotateToCamera);
      postScript.cleanup().addTickStep(0, () -> {
         task.run();
         InventoryTask.closeScreen(true);
      });
   }

   private static void rotateToCamera() {
      Module module = new Module("InventoryComponent", "Inventory Component", ModuleCategory.PLAYER);
      module.state = true;
      TurnsConnection.INSTANCE.rotateTo(MathAngle.cameraAngle(), TurnsConfig.DEFAULT, TaskPriority.HIGH_IMPORTANCE_3, module);
   }

   public static void disableMoveKeys() {
      canMove = false;
      unPressMoveKeys();
   }

   public static void enableMoveKeys() {
      InventoryTask.closeScreen(true);
      canMove = true;
      updateMoveKeys();
   }

   public static void unPressMoveKeys() {
      moveKeys.forEach((keyBinding) -> keyBinding.method_23481(false));
   }

   public static void updateMoveKeys() {
      moveKeys.forEach((keyBinding) -> keyBinding.method_23481(class_3675.method_15987(mc.method_22683().method_4490(), keyBinding.method_1429().method_1444())));
   }

   public static boolean shouldSkipExecution() {
      return mc.field_1755 != null && !PlayerInteractionHelper.isChat(mc.field_1755) && !(mc.field_1755 instanceof class_498) && !(mc.field_1755 instanceof class_471) && !(mc.field_1755 instanceof class_463) && !(mc.field_1755 instanceof class_497) && !(mc.field_1755 instanceof MenuScreen);
   }

   private InventoryFlowManager() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }

   static {
      moveKeys = List.of(mc.field_1690.field_1894, mc.field_1690.field_1881, mc.field_1690.field_1913, mc.field_1690.field_1849, mc.field_1690.field_1903);
      script = new Script();
      postScript = new Script();
      canMove = true;
   }
}

