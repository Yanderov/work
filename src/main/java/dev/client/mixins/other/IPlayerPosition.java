package dev.client.mixins.other;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public interface IPlayerPosition {
   void setYaw(float yaw);

   void setPitch(float pitch);
}
