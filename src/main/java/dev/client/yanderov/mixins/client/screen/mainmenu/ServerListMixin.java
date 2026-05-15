package dev.client.yanderov.mixins.client.screen.mainmenu;

import com.llamalad7.mixinextras.sugar.Local;
import dev.client.yanderov.features.impl.misc.SelfDestruct;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import net.minecraft.class_2499;
import net.minecraft.class_2520;
import net.minecraft.class_641;
import net.minecraft.class_642;
import net.minecraft.class_642.class_8678;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_641.class})
public class ServerListMixin {
   @Unique
   private final List sponsorServers;
   @Shadow
   @Final
   private List field_3749;

   public ServerListMixin() {
      this.sponsorServers = List.of(new class_642("FunTime", "mc.funtime.su", class_8678.field_45609), new class_642("HolyWorld", "mc.holyworld.ru", class_8678.field_45609), new class_642("ReallyWorld", "mc.reallyworld.ru", class_8678.field_45609), new class_642("SpookyTime", "mc.spookytime.net", class_8678.field_45609), new class_642("AresMine", "mc.aresmine.ru", class_8678.field_45609));
   }

   @Inject(
      method = {"loadFile"},
      at = {@At(
   value = "FIELD",
   target = "Lnet/minecraft/client/option/ServerList;hiddenServers:Ljava/util/List;",
   ordinal = 0
)}
   )
   private void loadFileHook(CallbackInfo ci) {
      if (!SelfDestruct.unhooked) {
         this.removeDuplicateSponsors();
         this.addMissingSponsors();
      }
   }

   @Redirect(
      method = {"saveFile"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/nbt/NbtList;add(Ljava/lang/Object;)Z",
   ordinal = 0
)
   )
   private boolean saveFileHook(class_2499 instance, Object o, @Local(ordinal = 0) class_642 info) {
      if (SelfDestruct.unhooked) {
         return instance.add((class_2520)o);
      } else {
         return this.isSponsorServer(info) ? true : instance.add((class_2520)o);
      }
   }

   @Unique
   private void removeDuplicateSponsors() {
      Set<String> sponsorAddresses = new HashSet();

      for(class_642 sponsor : this.sponsorServers) {
         sponsorAddresses.add(sponsor.field_3761.toLowerCase());
      }

      Iterator<class_642> iterator = this.field_3749.iterator();
      Set<String> seenAddresses = new HashSet();

      while(iterator.hasNext()) {
         class_642 server = (class_642)iterator.next();
         String address = server.field_3761.toLowerCase();
         if (sponsorAddresses.contains(address)) {
            if (seenAddresses.contains(address)) {
               iterator.remove();
            } else {
               seenAddresses.add(address);
            }
         }
      }

   }

   @Unique
   private void addMissingSponsors() {
      Set<String> existingAddresses = new HashSet();

      for(class_642 server : this.field_3749) {
         existingAddresses.add(server.field_3761.toLowerCase());
      }

      for(class_642 sponsor : this.sponsorServers) {
         if (!existingAddresses.contains(sponsor.field_3761.toLowerCase())) {
            this.field_3749.add(sponsor);
         }
      }

   }

   @Unique
   private boolean isSponsorServer(class_642 info) {
      for(class_642 sponsor : this.sponsorServers) {
         if (sponsor.field_3761.equalsIgnoreCase(info.field_3761)) {
            return true;
         }
      }

      return false;
   }
}

