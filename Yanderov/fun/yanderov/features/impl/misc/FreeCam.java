package fun.Yanderov.features.impl.misc;

import fun.Yanderov.events.packet.PacketEvent;
import fun.Yanderov.events.player.InputEvent;
import fun.Yanderov.events.player.MoveEvent;
import fun.Yanderov.events.render.CameraPositionEvent;
import fun.Yanderov.events.render.WorldRenderEvent;
import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.features.module.setting.Setting;
import fun.Yanderov.features.module.setting.implement.BooleanSetting;
import fun.Yanderov.features.module.setting.implement.SliderSettings;
import fun.Yanderov.utils.client.Instance;
import fun.Yanderov.utils.client.managers.event.EventHandler;
import fun.Yanderov.utils.display.geometry.Render3D;
import fun.Yanderov.utils.interactions.simulate.Simulations;
import fun.Yanderov.utils.math.calc.Calculate;
import java.util.Objects;
import net.minecraft.class_243;
import net.minecraft.class_2596;
import net.minecraft.class_2678;
import net.minecraft.class_2724;
import net.minecraft.class_2828;
import net.minecraft.class_5498;

public class FreeCam extends Module {
   private final SliderSettings speedSetting = (new SliderSettings("Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ", "Ð’Ñ‹Ð±ÐµÑ€Ð¸Ñ‚Ðµ ÑÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ ÐºÐ°Ð¼ÐµÑ€Ñ‹ Ð¾Ñ‚Ð»Ð°Ð´ÐºÐ¸")).setValue(2.0F).range(0.5F, 5.0F);
   private final BooleanSetting freezeSetting = (new BooleanSetting("Ð—Ð°Ð¼Ð¾Ñ€Ð¾Ð·ÐºÐ°", "Ð’Ñ‹ Ð·Ð°Ð¼Ð¾Ñ€Ð°Ð¶Ð¸Ð²Ð°ÐµÑ‚ÐµÑÑŒ Ð½Ð° Ð¼ÐµÑÑ‚Ðµ")).setValue(false);
   public class_243 pos;
   public class_243 prevPos;

   public static FreeCam getInstance() {
      return (FreeCam)Instance.get(FreeCam.class);
   }

   public FreeCam() {
      super("FreeCam", "Free Cam", ModuleCategory.MISC);
      this.setup(new Setting[]{this.speedSetting, this.freezeSetting});
   }

   public void activate() {
      this.prevPos = this.pos = new class_243(mc.method_1561().field_4686.method_19326().method_46409());
      super.activate();
   }

   @EventHandler
   public void onPacket(PacketEvent e) {
      class_2596 var10000 = e.getPacket();
      Objects.requireNonNull(var10000);
      class_2596 var2 = var10000;
      byte var3 = 0;

      while(true) {
         //$FF: var3->value
         //0->net/minecraft/class_2828
         //1->net/minecraft/class_2724
         //2->net/minecraft/class_2678
         switch (var2.typeSwitch<invokedynamic>(var2, var3)) {
            case 0:
               class_2828 move = (class_2828)var2;
               if (!this.freezeSetting.isValue()) {
                  var3 = 1;
                  break;
               }

               e.cancel();
               return;
            case 1:
               class_2724 respawn = (class_2724)var2;
               this.setState(false);
               return;
            case 2:
               class_2678 join = (class_2678)var2;
               this.setState(false);
               return;
            default:
               return;
         }
      }
   }

   @EventHandler
   public void onWorldRender(WorldRenderEvent e) {
      Render3D.drawBox(mc.field_1724.method_5829().method_997(Calculate.interpolate(mc.field_1724).method_1020(mc.field_1724.method_19538())), -1, 1.0F);
   }

   @EventHandler
   public void onMove(MoveEvent e) {
      if (this.freezeSetting.isValue()) {
         e.setMovement(class_243.field_1353);
      }

   }

   @EventHandler
   public void onInput(InputEvent e) {
      float speed = this.speedSetting.getValue();
      double[] motion = Simulations.calculateDirection((float)e.forward(), e.sideways(), (double)speed);
      this.prevPos = this.pos;
      this.pos = this.pos.method_1031(motion[0], e.getInput().comp_3163() ? (double)speed : (e.getInput().comp_3164() ? (double)(-speed) : (double)0.0F), motion[1]);
      e.inputNone();
   }

   @EventHandler
   public void onCameraPosition(CameraPositionEvent e) {
      e.setPos(Calculate.interpolate(this.prevPos, this.pos));
      mc.field_1690.method_31043(class_5498.field_26664);
   }
}

