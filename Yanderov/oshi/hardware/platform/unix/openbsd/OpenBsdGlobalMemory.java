package oshi.hardware.platform.unix.openbsd;

import com.sun.jna.Memory;
import java.util.function.Supplier;
import oshi.annotation.concurrent.ThreadSafe;
import oshi.hardware.VirtualMemory;
import oshi.hardware.common.AbstractGlobalMemory;
import oshi.jna.platform.unix.OpenBsdLibc;
import oshi.util.ExecutingCommand;
import oshi.util.Memoizer;
import oshi.util.ParseUtil;
import oshi.util.platform.unix.openbsd.OpenBsdSysctlUtil;

@ThreadSafe
final class OpenBsdGlobalMemory extends AbstractGlobalMemory {
   private final Supplier available = Memoizer.memoize(OpenBsdGlobalMemory::queryAvailable, Memoizer.defaultExpiration());
   private final Supplier total = Memoizer.memoize(OpenBsdGlobalMemory::queryPhysMem);
   private final Supplier pageSize = Memoizer.memoize(OpenBsdGlobalMemory::queryPageSize);
   private final Supplier vm = Memoizer.memoize(this::createVirtualMemory);

   public long getAvailable() {
      return (Long)this.available.get() * this.getPageSize();
   }

   public long getTotal() {
      return (Long)this.total.get();
   }

   public long getPageSize() {
      return (Long)this.pageSize.get();
   }

   public VirtualMemory getVirtualMemory() {
      return (VirtualMemory)this.vm.get();
   }

   private static long queryAvailable() {
      long free = 0L;
      long inactive = 0L;

      for(String line : ExecutingCommand.runNative("vmstat -s")) {
         if (line.endsWith("pages free")) {
            free = (long)ParseUtil.getFirstIntValue(line);
         } else if (line.endsWith("pages inactive")) {
            inactive = (long)ParseUtil.getFirstIntValue(line);
         }
      }

      int[] mib = new int[3];
      mib[0] = 10;
      mib[1] = 0;
      mib[2] = 3;
      Memory m = OpenBsdSysctlUtil.sysctl(mib);

      long var7;
      try {
         OpenBsdLibc.Bcachestats cache = new OpenBsdLibc.Bcachestats(m);
         var7 = cache.numbufpages + free + inactive;
      } catch (Throwable var10) {
         if (m != null) {
            try {
               m.close();
            } catch (Throwable var9) {
               var10.addSuppressed(var9);
            }
         }

         throw var10;
      }

      if (m != null) {
         m.close();
      }

      return var7;
   }

   private static long queryPhysMem() {
      return OpenBsdSysctlUtil.sysctl("hw.physmem", 0L);
   }

   private static long queryPageSize() {
      return OpenBsdSysctlUtil.sysctl("hw.pagesize", 4096L);
   }

   private VirtualMemory createVirtualMemory() {
      return new OpenBsdVirtualMemory(this);
   }
}
