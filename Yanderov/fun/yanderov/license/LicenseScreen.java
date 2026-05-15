package fun.Yanderov.license;

import net.minecraft.class_156;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_342;
import net.minecraft.class_4185;
import net.minecraft.class_437;

public class LicenseScreen extends class_437 {
   private final class_437 parent;
   private class_342 keyField;
   private class_4185 checkButton;
   private class_2561 statusMessage = class_2561.method_30163("");

   public LicenseScreen(class_437 parent) {
      super(class_2561.method_30163("Ð’Ð²ÐµÐ´Ð¸Ñ‚Ðµ ÐºÐ»ÑŽÑ‡ Ð´Ð¾ÑÑ‚ÑƒÐ¿Ð° Ð¸Ð· Telegram Ð±Ð¾Ñ‚Ð°"));
      this.parent = parent;
   }

   protected void method_25426() {
      super.method_25426();
      this.keyField = new class_342(this.field_22793, this.field_22789 / 2 - 100, this.field_22790 / 2 - 20, 200, 20, class_2561.method_30163("Key"));
      this.method_25429(this.keyField);
      this.keyField.method_25365(true);
      this.method_48265(this.keyField);
      this.checkButton = class_4185.method_46430(class_2561.method_30163("Check"), (button) -> this.checkKey()).method_46434(this.field_22789 / 2 - 100, this.field_22790 / 2 + 10, 200, 20).method_46431();
      this.method_37063(this.checkButton);
   }

   private void checkKey() {
      String key = this.keyField.method_1882().trim();
      if (key.isEmpty()) {
         this.setStatusMessage(class_2561.method_30163("Please enter a key."));
      } else {
         this.setStatusMessage(class_2561.method_30163("Checking..."));
         class_156.method_18349().execute(() -> {
            boolean valid = LicenseService.validateKey(key);
            if (valid) {
               class_310.method_1551().execute(() -> class_310.method_1551().method_1507(this.parent));
            } else {
               this.setStatusMessage(class_2561.method_30163("Invalid key. Please try again."));
            }

         });
      }
   }

   private void setStatusMessage(class_2561 message) {
      this.statusMessage = message;
   }

   public void method_25394(class_332 context, int mouseX, int mouseY, float delta) {
      super.method_25394(context, mouseX, mouseY, delta);
      context.method_27534(this.field_22793, this.field_22785, this.field_22789 / 2, 20, 16777215);
      context.method_25303(this.field_22793, class_2561.method_30163("License key:").getString(), this.field_22789 / 2 - 100, this.field_22790 / 2 - 32, 10526880);
      this.keyField.method_25394(context, mouseX, mouseY, delta);
      context.method_27534(this.field_22793, this.statusMessage, this.field_22789 / 2, this.field_22790 / 2 + 40, 16711680);
   }

   public boolean method_25402(double mouseX, double mouseY, int button) {
      return this.keyField != null && this.keyField.method_25402(mouseX, mouseY, button) ? true : super.method_25402(mouseX, mouseY, button);
   }

   public boolean method_25400(char chr, int modifiers) {
      return this.keyField != null && this.keyField.method_25400(chr, modifiers) ? true : super.method_25400(chr, modifiers);
   }

   public boolean method_25404(int keyCode, int scanCode, int modifiers) {
      if (keyCode != 257 && keyCode != 335) {
         return this.keyField != null && this.keyField.method_25404(keyCode, scanCode, modifiers) ? true : super.method_25404(keyCode, scanCode, modifiers);
      } else {
         this.checkKey();
         return true;
      }
   }

   public boolean method_25422() {
      return false;
   }
}

