package dev.client.modules.impl.movement;

import dev.client.event.classes.TickEvent;
import dev.client.event.interfaces.ITickable;
import dev.client.modules.Category;
import dev.client.modules.Module;
import dev.client.modules.ModuleBranding;
import dev.client.modules.settings.impl.BooleanSetting;
import dev.client.util.IUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

@Environment(EnvType.CLIENT)
public class MoveHelper extends Module implements ITickable, IUtil {
    private final BooleanSetting ladder = new BooleanSetting().name("Ladder Boost").value(true);
    private final BooleanSetting slime = new BooleanSetting().name("Slime Jump").value(true);
    private final BooleanSetting honey = new BooleanSetting().name("Honey Boost").value(true);

    public MoveHelper() {
        super(new ModuleBranding("MoveHelper", Category.MOVEMENT, "Ускоряет ваше движение в определённых моментах"));
        this.addSetting(this.ladder, this.slime, this.honey);
    }

    @Override
    public void onTick(TickEvent event) {
        if (mc.player == null || mc.world == null) return;

        // Ladder Boost
        if (this.ladder.getValue() && mc.player.isOnLadder() && mc.options.forwardKey.isPressed()) {
            Vec3d velocity = mc.player.getVelocity();
            mc.player.setVelocity(velocity.x, 0.28D, velocity.z);
        }

        // Slime Jump
        if (this.slime.getValue() && mc.player.isOnGround()) {
            BlockPos below = mc.player.getBlockPos().down();
            if (mc.world.getBlockState(below).getBlock().getTranslationKey().contains("slime")) {
                mc.player.jump();
            }
        }

        // Honey Boost
        if (this.honey.getValue()) {
            BlockPos currentPos = mc.player.getBlockPos();
            if (mc.world.getBlockState(currentPos).getBlock().getTranslationKey().contains("honey")) {
                Vec3d velocity = mc.player.getVelocity();
                mc.player.setVelocity(velocity.x * 1.08D, velocity.y, velocity.z * 1.08D);
            }
        }
    }
}
