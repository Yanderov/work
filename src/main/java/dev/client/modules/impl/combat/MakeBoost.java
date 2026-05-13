package dev.client.modules.impl.combat;

import dev.client.event.classes.AttackEvent;
import dev.client.event.classes.TickEvent;
import dev.client.event.interfaces.IAttackable;
import dev.client.event.interfaces.ITickable;
import dev.client.modules.Category;
import dev.client.modules.Module;
import dev.client.modules.PlayerModel;
import dev.client.util.IUtil;
import dev.client.util.math.TimerUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.UpdateSelectedSlotC2SPacket;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.RaycastContext.FluidHandling;
import net.minecraft.world.RaycastContext.ShapeType;

@Environment(EnvType.CLIENT)
public class MakeBoost extends Module implements IAttackable, ITickable, IUtil {
   private boolean swapped = false;
   private final TimerUtil timerUtil = new TimerUtil();
   private int b;
   private int fallDistance;

   public MakeBoost() {
      super(new PlayerModel("MakeBoost", Category.COMBAT, "NoDesc"));
   }

   public void onAttack(AttackEvent event) {
      this.b = mc.player.getInventory().selectedSlot;

      for(int i = 0; i < 9; ++i) {
         if (mc.player.getInventory().getStack(i).getItem() == Items.MACE && !this.swapped) {
            mc.player.networkHandler.sendPacket(new UpdateSelectedSlotC2SPacket(i));
            this.swapped = true;
            this.timerUtil.reset();
         }
      }

   }

   public void onTick(TickEvent event) {
      BlockHitResult hit = mc.world.raycast(new RaycastContext(mc.player.getPos(), mc.player.getPos().add(0.0D, -256.0D, 0.0D), ShapeType.COLLIDER, FluidHandling.NONE, mc.player));
      double distanceToGround = mc.player.getY() - hit.getPos().y;
      if (this.timerUtil.isReached(500L)) {
         mc.player.networkHandler.sendPacket(new UpdateSelectedSlotC2SPacket(this.b));
      }

   }
}

