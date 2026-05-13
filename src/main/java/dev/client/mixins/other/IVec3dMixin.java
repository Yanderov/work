package dev.client.mixins.other;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public interface IVec3dMixin {
   void wild$setY(float y);
}
