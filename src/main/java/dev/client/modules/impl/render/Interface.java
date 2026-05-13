package dev.client.modules.impl.render;

import dev.client.event.classes.HudRenderEvent;
import dev.client.event.interfaces.IHudRenderable;
import dev.client.modules.Category;
import dev.client.modules.Module;
import dev.client.modules.ModuleBranding;
import dev.client.modules.settings.impl.BooleanSetting;
import dev.client.modules.settings.impl.ModeSetting;
import dev.client.modules.settings.impl.MultiBoxSetting;
import dev.client.ui.hud.impl.BindsElement;
import dev.client.ui.hud.impl.MusicElement;
import dev.client.ui.hud.impl.NotifyElement;
import dev.client.ui.hud.impl.PotionsElement;
import dev.client.ui.hud.impl.TargetHudElement;
import dev.client.ui.hud.impl.WatermarkElement;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class Interface extends Module implements IHudRenderable {
   private final MultiBoxSetting elements = new MultiBoxSetting().name("Elements").booleanSettings(new BooleanSetting().name("Watermark").value(false), new BooleanSetting().name("Binds").value(false), new BooleanSetting().name("Potions").value(false), new BooleanSetting().name("Staff").value(false), new BooleanSetting().name("TargetHUD").value(false), new BooleanSetting().name("Scoreboard").value(false), new BooleanSetting().name("Hotbar").value(true), new BooleanSetting().name("Music").value(false), new BooleanSetting().name("Notify").value(false), new BooleanSetting().name("Picture").value(false));
   public final ModeSetting picture = new ModeSetting().name("Picture Mode").modes("Artas").value("Artas");
   private final WatermarkElement watermarkElement = new WatermarkElement();
   private final PotionsElement potionsElement = new PotionsElement();
   private final TargetHudElement targetHudElement = new TargetHudElement();
   private final BindsElement bindsElement = new BindsElement();
   private final NotifyElement notifyElement = new NotifyElement();
   private final MusicElement musicElement = new MusicElement();

   public Interface() {
      super(new ModuleBranding("Interface", Category.RENDER, "Показывает худ чита"));
      this.addSetting(this.elements, this.picture);
      this.setEnabled(true);
   }

   public MultiBoxSetting getElements() {
      return this.elements;
   }

   public void onHudRender(HudRenderEvent event) {
      if (this.elements.getValueByName("Watermark")) {
         this.watermarkElement.render(event.getDrawContext());
      }

      if (this.elements.getValueByName("Potions")) {
         this.potionsElement.render(event.getDrawContext());
      }

      if (this.elements.getValueByName("TargetHUD")) {
         this.targetHudElement.render(event.getDrawContext());
      }

      if (this.elements.getValueByName("Binds")) {
         this.bindsElement.render(event.getDrawContext());
      }

      if (this.elements.getValueByName("Notify")) {
         this.notifyElement.render(event.getDrawContext());
      }

      if (this.elements.getValueByName("Music")) {
         this.musicElement.render(event.getDrawContext());
      }

   }
}
