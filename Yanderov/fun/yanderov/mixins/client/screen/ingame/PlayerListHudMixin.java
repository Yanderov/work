package fun.Yanderov.mixins.client.screen.ingame;

import com.mojang.authlib.GameProfile;
import fun.Yanderov.features.impl.render.BetterMinecraft;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;
import net.minecraft.class_124;
import net.minecraft.class_2561;
import net.minecraft.class_268;
import net.minecraft.class_269;
import net.minecraft.class_310;
import net.minecraft.class_355;
import net.minecraft.class_5250;
import net.minecraft.class_640;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_355.class})
public class PlayerListHudMixin {
   private static final Pattern NAME_PATTERN = Pattern.compile("^\\w{3,16}$");

   @Inject(
      method = {"collectPlayerEntries"},
      at = {@At("RETURN")},
      cancellable = true
   )
   private void addVanishedEntries(CallbackInfoReturnable cir) {
      if (BetterMinecraft.getInstance().isState() && BetterMinecraft.getInstance().getTabVanishButton().isValue()) {
         class_310 client = class_310.method_1551();
         List<class_640> originalList = (List)cir.getReturnValue();
         List<class_640> vanishedList = new ArrayList();
         class_269 scoreboard = client.field_1687.method_8428();
         List<class_268> teams = new ArrayList(scoreboard.method_1159());
         teams.sort(Comparator.comparing(class_268::method_1197));
         Collection<class_640> online = client.field_1724.field_3944.method_2880();

         for(class_268 team : teams) {
            Collection<String> members = team.method_1204();
            if (members.size() == 1) {
               String name = (String)members.iterator().next();
               if (NAME_PATTERN.matcher(name).matches()) {
                  boolean present = online.stream().anyMatch((e) -> e.method_2966() != null && name.equals(e.method_2966().getName()));
                  if (!present) {
                     class_5250 displayName = class_2561.method_43473().method_10852(class_2561.method_43470("[").method_27692(class_124.field_1080)).method_10852(class_2561.method_43470("V").method_27692(class_124.field_1061)).method_10852(class_2561.method_43470("] ").method_27692(class_124.field_1080)).method_10852(team.method_1144()).method_10852(class_2561.method_43470(name).method_27692(class_124.field_1080));
                     GameProfile fakeProfile = new GameProfile(UUID.randomUUID(), name);
                     class_640 fake = new class_640(fakeProfile, client.method_1542());
                     fake.method_2962(displayName);
                     fake.method_62153(Integer.MIN_VALUE);
                     vanishedList.add(fake);
                  }
               }
            }
         }

         List<class_640> finalList = new ArrayList();
         finalList.addAll(vanishedList);
         finalList.addAll(originalList);
         cir.setReturnValue(finalList);
      }
   }
}

