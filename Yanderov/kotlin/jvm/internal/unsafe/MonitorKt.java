package kotlin.jvm.internal.unsafe;

import kotlin.Metadata;

@Metadata(
   mv = {1, 9, 0},
   k = 2,
   xi = 48,
   d1 = {"\u0000\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u001a\u0017\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\u0002¢\u0006\u0004\b\u0003\u0010\u0004\u001a\u0017\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\u0002¢\u0006\u0004\b\u0005\u0010\u0004¨\u0006\u0006"},
   d2 = {"", "monitor", "", "monitorEnter", "(Ljava/lang/Object;)V", "monitorExit", "kotlin-stdlib"}
)
public final class MonitorKt {
   private static final void monitorEnter(Object monitor) {
      throw new UnsupportedOperationException("This function can only be used privately");
   }

   private static final void monitorExit(Object monitor) {
      throw new UnsupportedOperationException("This function can only be used privately");
   }
}
