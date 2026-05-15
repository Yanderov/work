package oshi.driver.windows.wmi;

import com.sun.jna.platform.win32.COM.WbemcliUtil;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import oshi.annotation.concurrent.ThreadSafe;
import oshi.util.platform.windows.WmiQueryHandler;

@ThreadSafe
public final class Win32Process {
   private static final String WIN32_PROCESS = "Win32_Process";

   private Win32Process() {
   }

   public static WbemcliUtil.WmiResult queryCommandLines(Set pidsToQuery) {
      String sb = "Win32_Process";
      if (pidsToQuery != null) {
         sb = sb + " WHERE ProcessID=" + (String)pidsToQuery.stream().map(String::valueOf).collect(Collectors.joining(" OR PROCESSID="));
      }

      WbemcliUtil.WmiQuery<CommandLineProperty> commandLineQuery = new WbemcliUtil.WmiQuery(sb, CommandLineProperty.class);
      return ((WmiQueryHandler)Objects.requireNonNull(WmiQueryHandler.createInstance())).queryWMI(commandLineQuery);
   }

   public static WbemcliUtil.WmiResult queryProcesses(Collection pids) {
      String sb = "Win32_Process";
      if (pids != null) {
         sb = sb + " WHERE ProcessID=" + (String)pids.stream().map(String::valueOf).collect(Collectors.joining(" OR PROCESSID="));
      }

      WbemcliUtil.WmiQuery<ProcessXPProperty> processQueryXP = new WbemcliUtil.WmiQuery(sb, ProcessXPProperty.class);
      return ((WmiQueryHandler)Objects.requireNonNull(WmiQueryHandler.createInstance())).queryWMI(processQueryXP);
   }

   public static enum CommandLineProperty {
      PROCESSID,
      COMMANDLINE;

      // $FF: synthetic method
      private static CommandLineProperty[] $values() {
         return new CommandLineProperty[]{PROCESSID, COMMANDLINE};
      }
   }

   public static enum ProcessXPProperty {
      PROCESSID,
      NAME,
      KERNELMODETIME,
      USERMODETIME,
      THREADCOUNT,
      PAGEFILEUSAGE,
      HANDLECOUNT,
      EXECUTABLEPATH;

      // $FF: synthetic method
      private static ProcessXPProperty[] $values() {
         return new ProcessXPProperty[]{PROCESSID, NAME, KERNELMODETIME, USERMODETIME, THREADCOUNT, PAGEFILEUSAGE, HANDLECOUNT, EXECUTABLEPATH};
      }
   }
}
