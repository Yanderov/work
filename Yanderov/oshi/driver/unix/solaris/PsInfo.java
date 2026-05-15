package oshi.driver.unix.solaris;

import com.sun.jna.Memory;
import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.platform.unix.LibCAPI;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import oshi.annotation.concurrent.ThreadSafe;
import oshi.jna.platform.unix.SolarisLibc;
import oshi.util.ExecutingCommand;
import oshi.util.FileUtil;
import oshi.util.ParseUtil;
import oshi.util.tuples.Pair;
import oshi.util.tuples.Quartet;

@ThreadSafe
public final class PsInfo {
   private static final Logger LOG = LoggerFactory.getLogger(PsInfo.class);
   private static final SolarisLibc LIBC;
   private static final long PAGE_SIZE;

   private PsInfo() {
   }

   public static SolarisLibc.SolarisPsInfo queryPsInfo(int pid) {
      return new SolarisLibc.SolarisPsInfo(FileUtil.readAllBytesAsBuffer(String.format("/proc/%d/psinfo", pid)));
   }

   public static SolarisLibc.SolarisLwpsInfo queryLwpsInfo(int pid, int tid) {
      return new SolarisLibc.SolarisLwpsInfo(FileUtil.readAllBytesAsBuffer(String.format("/proc/%d/lwp/%d/lwpsinfo", pid, tid)));
   }

   public static SolarisLibc.SolarisPrUsage queryPrUsage(int pid) {
      return new SolarisLibc.SolarisPrUsage(FileUtil.readAllBytesAsBuffer(String.format("/proc/%d/usage", pid)));
   }

   public static SolarisLibc.SolarisPrUsage queryPrUsage(int pid, int tid) {
      return new SolarisLibc.SolarisPrUsage(FileUtil.readAllBytesAsBuffer(String.format("/proc/%d/lwp/%d/usage", pid, tid)));
   }

   public static Quartet queryArgsEnvAddrs(int pid, SolarisLibc.SolarisPsInfo psinfo) {
      if (psinfo != null) {
         int argc = psinfo.pr_argc;
         if (argc > 0) {
            long argv = Pointer.nativeValue(psinfo.pr_argv);
            long envp = Pointer.nativeValue(psinfo.pr_envp);
            byte dmodel = psinfo.pr_dmodel;
            if ((long)(dmodel * 4) == (envp - argv) / (long)(argc + 1)) {
               return new Quartet(argc, argv, envp, dmodel);
            } else {
               LOG.trace("Failed data model and offset increment sanity check: dm={} diff={}", dmodel, envp - argv);
               return null;
            }
         } else {
            LOG.trace("Failed argc sanity check: argc={}", argc);
            return null;
         }
      } else {
         LOG.trace("Failed to read psinfo file for pid: {} ", pid);
         return null;
      }
   }

   public static Pair queryArgsEnv(int pid, SolarisLibc.SolarisPsInfo psinfo) {
      List<String> args = new ArrayList();
      Map<String, String> env = new LinkedHashMap();
      Quartet<Integer, Long, Long, Byte> addrs = queryArgsEnvAddrs(pid, psinfo);
      if (addrs != null) {
         String procas = "/proc/" + pid + "/as";
         int fd = LIBC.open(procas, 0);
         if (fd < 0) {
            LOG.trace("No permission to read file: {} ", procas);
            return new Pair(args, env);
         }

         try {
            int argc = (Integer)addrs.getA();
            long argv = (Long)addrs.getB();
            long envp = (Long)addrs.getC();
            long increment = (long)(Byte)addrs.getD() * 4L;
            long bufStart = 0L;
            Memory buffer = new Memory(PAGE_SIZE * 2L);

            try {
               LibCAPI.size_t bufSize = new LibCAPI.size_t(buffer.size());
               long[] argp = new long[argc];
               long offset = argv;

               for(int i = 0; i < argc; ++i) {
                  bufStart = conditionallyReadBufferFromStartOfPage(fd, buffer, bufSize, bufStart, offset);
                  argp[i] = bufStart == 0L ? 0L : getOffsetFromBuffer(buffer, offset - bufStart, increment);
                  offset += increment;
               }

               List<Long> envPtrList = new ArrayList();
               offset = envp;
               long addr = 0L;
               int limit = 500;

               do {
                  bufStart = conditionallyReadBufferFromStartOfPage(fd, buffer, bufSize, bufStart, offset);
                  addr = bufStart == 0L ? 0L : getOffsetFromBuffer(buffer, offset - bufStart, increment);
                  if (addr != 0L) {
                     envPtrList.add(addr);
                  }

                  offset += increment;
                  if (addr == 0L) {
                     break;
                  }

                  --limit;
               } while(limit > 0);

               for(int i = 0; i < argp.length && argp[i] != 0L; ++i) {
                  bufStart = conditionallyReadBufferFromStartOfPage(fd, buffer, bufSize, bufStart, argp[i]);
                  if (bufStart != 0L) {
                     String argStr = buffer.getString(argp[i] - bufStart);
                     if (!argStr.isEmpty()) {
                        args.add(argStr);
                     }
                  }
               }

               for(Long envPtr : envPtrList) {
                  bufStart = conditionallyReadBufferFromStartOfPage(fd, buffer, bufSize, bufStart, envPtr);
                  if (bufStart != 0L) {
                     String envStr = buffer.getString(envPtr - bufStart);
                     int idx = envStr.indexOf(61);
                     if (idx > 0) {
                        env.put(envStr.substring(0, idx), envStr.substring(idx + 1));
                     }
                  }
               }
            } catch (Throwable var34) {
               try {
                  buffer.close();
               } catch (Throwable var33) {
                  var34.addSuppressed(var33);
               }

               throw var34;
            }

            buffer.close();
         } finally {
            LIBC.close(fd);
         }
      }

      return new Pair(args, env);
   }

   private static long conditionallyReadBufferFromStartOfPage(int fd, Memory buffer, LibCAPI.size_t bufSize, long bufStart, long addr) {
      if (addr >= bufStart && addr - bufStart <= PAGE_SIZE) {
         return bufStart;
      } else {
         long newStart = Math.floorDiv(addr, PAGE_SIZE) * PAGE_SIZE;
         LibCAPI.ssize_t result = LIBC.pread(fd, buffer, bufSize, new NativeLong(newStart));
         if (result.longValue() < PAGE_SIZE) {
            LOG.debug("Failed to read page from address space: {} bytes read", result.longValue());
            return 0L;
         } else {
            return newStart;
         }
      }
   }

   private static long getOffsetFromBuffer(Memory buffer, long offset, long increment) {
      return increment == 8L ? buffer.getLong(offset) : (long)buffer.getInt(offset);
   }

   static {
      LIBC = SolarisLibc.INSTANCE;
      PAGE_SIZE = ParseUtil.parseLongOrDefault(ExecutingCommand.getFirstAnswer("pagesize"), 4096L);
   }
}
