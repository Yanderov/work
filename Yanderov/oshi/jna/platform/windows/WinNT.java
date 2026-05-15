package oshi.jna.platform.windows;

import com.sun.jna.Structure;
import com.sun.jna.Structure.FieldOrder;
import oshi.util.Util;

public interface WinNT extends com.sun.jna.platform.win32.WinNT {
   @FieldOrder({"TokenIsElevated"})
   public static class TOKEN_ELEVATION extends Structure implements AutoCloseable {
      public int TokenIsElevated;

      public void close() {
         Util.freeMemory(this.getPointer());
      }
   }
}
