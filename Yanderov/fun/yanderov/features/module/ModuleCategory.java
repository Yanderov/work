package fun.Yanderov.features.module;

public enum ModuleCategory {
   COMBAT("Combat"),
   MOVEMENT("Movement"),
   RENDER("Render"),
   PLAYER("Player"),
   MISC("Misc"),
   CONFIGS("Configs"),
   AUTOBUY("AutoBuy");

   final String readableName;

   private ModuleCategory(final String readableName) {
      this.readableName = readableName;
   }

   public String getReadableName() {
      return this.readableName;
   }

   // $FF: synthetic method
   private static ModuleCategory[] $values() {
      return new ModuleCategory[]{COMBAT, MOVEMENT, RENDER, PLAYER, MISC, CONFIGS, AUTOBUY};
   }
}

