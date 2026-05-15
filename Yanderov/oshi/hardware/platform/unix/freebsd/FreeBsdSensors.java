package oshi.hardware.platform.unix.freebsd;

import com.sun.jna.Memory;
import com.sun.jna.Pointer;
import com.sun.jna.platform.unix.LibCAPI.size_t;
import oshi.annotation.concurrent.ThreadSafe;
import oshi.hardware.common.AbstractSensors;
import oshi.jna.ByRef;
import oshi.jna.platform.unix.FreeBsdLibc;

@ThreadSafe
final class FreeBsdSensors extends AbstractSensors {
   public double queryCpuTemperature() {
      return queryKldloadCoretemp();
   }

   private static double queryKldloadCoretemp() {
      String name = "dev.cpu.%d.temperature";
      ByRef.CloseableSizeTByReference size = new ByRef.CloseableSizeTByReference((long)FreeBsdLibc.INT_SIZE);

      double var12;
      try {
         int cpu = 0;
         double sumTemp = (double)0.0F;
         Memory p = new Memory(size.longValue());

         try {
            while(0 == FreeBsdLibc.INSTANCE.sysctlbyname(String.format(name, cpu), p, size, (Pointer)null, size_t.ZERO)) {
               sumTemp += (double)p.getInt(0L) / (double)10.0F - 273.15;
               ++cpu;
            }
         } catch (Throwable var10) {
            try {
               p.close();
            } catch (Throwable var9) {
               var10.addSuppressed(var9);
            }

            throw var10;
         }

         p.close();
         var12 = cpu > 0 ? sumTemp / (double)cpu : Double.NaN;
      } catch (Throwable var11) {
         try {
            size.close();
         } catch (Throwable var8) {
            var11.addSuppressed(var8);
         }

         throw var11;
      }

      size.close();
      return var12;
   }

   public int[] queryFanSpeeds() {
      return new int[0];
   }

   public double queryCpuVoltage() {
      return (double)0.0F;
   }
}
