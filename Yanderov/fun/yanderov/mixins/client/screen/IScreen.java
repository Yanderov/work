package fun.Yanderov.mixins.client.screen;

import java.util.List;
import net.minecraft.class_437;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({class_437.class})
public interface IScreen {
   @Accessor
   List getDrawables();

   @Accessor
   List getChildren();

   @Accessor
   List getSelectables();
}

