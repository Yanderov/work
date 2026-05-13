package dev.client.mixins.hooks;

import dev.client.WildClient;
import dev.client.modules.impl.player.NameProtect;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.text.TextVisitFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Environment(EnvType.CLIENT)
@Mixin({TextVisitFactory.class})
public class TextVisitFactoryMixin {
   @ModifyArg(
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/text/TextVisitFactory;visitFormatted(Ljava/lang/String;ILnet/minecraft/text/Style;Lnet/minecraft/text/Style;Lnet/minecraft/text/CharacterVisitor;)Z",
   ordinal = 0
),
      method = {"visitFormatted(Ljava/lang/String;ILnet/minecraft/text/Style;Lnet/minecraft/text/CharacterVisitor;)Z"},
      index = 0
   )
   private static String adjustText(String text) {
      return protect(text);
   }

   private static String protect(String string) {
      return ((NameProtect)WildClient.INSTANCE.getModuleManager().getByClass(NameProtect.class)).replace(string);
   }
}
