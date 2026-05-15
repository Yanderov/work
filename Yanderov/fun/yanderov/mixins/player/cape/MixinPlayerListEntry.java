package fun.Yanderov.mixins.player.cape;

import com.mojang.authlib.GameProfile;
import net.minecraft.class_2960;
import net.minecraft.class_640;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin({class_640.class})
public class MixinPlayerListEntry {
   @Shadow
   @Final
   private GameProfile field_3741;
   @Unique
   private static final class_2960 CUSTOM_CAPE = class_2960.method_60655("minecraft", "textures/cape/cape.png");
}

