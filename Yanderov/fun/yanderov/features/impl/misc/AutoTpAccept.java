package fun.Yanderov.features.impl.misc;

import antidaunleak.api.annotation.Native;
import fun.Yanderov.common.repository.friend.FriendUtils;
import fun.Yanderov.events.packet.PacketEvent;
import fun.Yanderov.events.player.TickEvent;
import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.features.module.setting.Setting;
import fun.Yanderov.features.module.setting.implement.BooleanSetting;
import fun.Yanderov.utils.client.managers.event.EventHandler;
import fun.Yanderov.utils.client.packet.network.Network;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;
import net.minecraft.class_2596;
import net.minecraft.class_7439;

public class AutoTpAccept extends Module {
   private final String[] teleportMessages = new String[]{"has requested teleport", "Ð¿Ñ€Ð¾ÑÐ¸Ñ‚ Ñ‚ÐµÐ»ÐµÐ¿Ð¾Ñ€Ñ‚Ð¸Ñ€Ð¾Ð²Ð°Ñ‚ÑŒÑÑ", "Ñ…Ð¾Ñ‡ÐµÑ‚ Ñ‚ÐµÐ»ÐµÐ¿Ð¾Ñ€Ñ‚Ð¸Ñ€Ð¾Ð²Ð°Ñ‚ÑŒÑÑ Ðº Ð²Ð°Ð¼", "Ð¿Ñ€Ð¾ÑÐ¸Ñ‚ Ðº Ð²Ð°Ð¼ Ñ‚ÐµÐ»ÐµÐ¿Ð¾Ñ€Ñ‚Ð¸Ñ€Ð¾Ð²Ð°Ñ‚ÑŒÑÑ"};
   private boolean canAccept;
   private final BooleanSetting friendSetting = (new BooleanSetting("Ð¢Ð¾Ð»ÑŒÐºÐ¾ Ð´Ñ€ÑƒÐ·ÑŒÑ", "Ð‘ÑƒÐ´ÐµÑ‚ Ð¿Ñ€Ð¸Ð½Ð¸Ð¼Ð°Ñ‚ÑŒ Ð·Ð°Ð¿Ñ€Ð¾ÑÑ‹ Ñ‚Ð¾Ð»ÑŒÐºÐ¾ Ð¾Ñ‚ Ð´Ñ€ÑƒÐ·ÐµÐ¹")).setValue(true);

   public AutoTpAccept() {
      super("AutoTpAccept", "Auto Tp Accept", ModuleCategory.MISC);
      this.setup(new Setting[]{this.friendSetting});
   }

   @EventHandler
   @Native(
      type = Native.Type.VMProtectBeginMutation
   )
   public void onPacket(PacketEvent e) {
      class_2596 var3 = e.getPacket();
      if (var3 instanceof class_7439 m) {
         String message = m.comp_763().getString();
         boolean validPlayer = !this.friendSetting.isValue() || FriendUtils.getFriends().stream().anyMatch((s) -> message.contains(s.getName()));
         if (this.isTeleportMessage(message)) {
            this.canAccept = validPlayer;
         }
      }

   }

   @EventHandler
   public void onTick(TickEvent e) {
      if (!Network.isPvp() && this.canAccept) {
         mc.field_1724.field_3944.method_45730("tpaccept");
         this.canAccept = false;
      }

   }

   private boolean isTeleportMessage(String message) {
      Stream var10000 = Arrays.stream(this.teleportMessages).map(String::toLowerCase);
      Objects.requireNonNull(message);
      return var10000.anyMatch(message::contains);
   }
}

