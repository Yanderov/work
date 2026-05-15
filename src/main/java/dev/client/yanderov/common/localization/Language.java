package dev.client.yanderov.common.localization;

import java.util.HashMap;

public enum Language {
   ENG("en_US"),
   RUS("ru_RU");

   private final String file;
   private final HashMap strings = new HashMap();

   public String getFile() {
      return this.file;
   }

   public HashMap getStrings() {
      return this.strings;
   }

   private Language(final String file) {
      this.file = file;
   }

   // $FF: synthetic method
   private static Language[] $values() {
      return new Language[]{ENG, RUS};
   }
}

