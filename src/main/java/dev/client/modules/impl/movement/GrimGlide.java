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
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.PlayerInteractItemC2SPacket;
import net.minecraft.network.packet.c2s.play.UpdateSelectedSlotC2SPacket;
import net.minecraft.util.Hand;

@Environment(EnvType.CLIENT)
public class GrimGlide extends Module implements ITickable, IUtil {
    public final BooleanSetting fireworkMode = new BooleanSetting().name("FireworkMode").value(false);
    private int ticksSinceLastBoost = 0;

    public GrimGlide() {
        super(new ModuleBranding("GrimGlide", Category.MOVEMENT, "Ускорение на элитре без видимого использования фейерверков"));
        this.addSetting(this.fireworkMode);
    }

    @Override
    public void onTick(TickEvent event) {
        if (mc.player == null || !mc.player.isGliding()) {
            this.ticksSinceLastBoost = 0;
            return;
        }

        this.ticksSinceLastBoost++;

        if (this.fireworkMode.getValue()) {
            // Logic to boost with fireworks every ~10 ticks if speed is low
            if (this.ticksSinceLastBoost >= 10 && mc.player.getVelocity().length() < 1.0D) {
                int fireworkSlot = this.findFirework();
                if (fireworkSlot != -1) {
                    int oldSlot = mc.player.getInventory().selectedSlot;
                    
                    // Silent swap and use
                    mc.player.networkHandler.sendPacket(new UpdateSelectedSlotC2SPacket(fireworkSlot));
                    mc.player.networkHandler.sendPacket(new PlayerInteractItemC2SPacket(Hand.MAIN_HAND, 0, mc.player.getYaw(), mc.player.getPitch()));
                    mc.player.networkHandler.sendPacket(new UpdateSelectedSlotC2SPacket(oldSlot));
                    
                    this.ticksSinceLastBoost = 0;
                }
            }
        } else {
            // Default GrimGlide logic (non-firework based acceleration exploit)
            // Usually involves pitch manipulation or packet spam, but here we provide a basic boost
            if (mc.player.getVelocity().length() < 0.8D) {
                mc.player.addVelocity(mc.player.getRotationVector().x * 0.1D, 0.05D, mc.player.getRotationVector().z * 0.1D);
            }
        }
    }

    private int findFirework() {
        for (int i = 0; i < 9; i++) {
            if (mc.player.getInventory().getStack(i).getItem() == Items.FIREWORK_ROCKET) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void onDisable() {
        this.ticksSinceLastBoost = 0;
        super.onDisable();
    }
}
