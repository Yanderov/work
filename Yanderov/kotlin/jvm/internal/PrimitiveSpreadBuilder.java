package kotlin.jvm.internal;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\r\n\u0002\u0010\u0011\n\u0002\b\u0005\b&\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\u00020\u0001B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0015\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00028\u0000¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\u0004\u001a\u00020\u0003H\u0004¢\u0006\u0004\b\u0004\u0010\u000bJ\u001f\u0010\u000e\u001a\u00028\u00002\u0006\u0010\f\u001a\u00028\u00002\u0006\u0010\r\u001a\u00028\u0000H\u0004¢\u0006\u0004\b\u000e\u0010\u000fJ\u0013\u0010\u0010\u001a\u00020\u0003*\u00028\u0000H$¢\u0006\u0004\b\u0010\u0010\u0011R\"\u0010\u0012\u001a\u00020\u00038\u0004@\u0004X\u0084\u000e¢\u0006\u0012\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u000b\"\u0004\b\u0015\u0010\u0006R\u0014\u0010\u0004\u001a\u00020\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\u0013R\"\u0010\u0017\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u00168\u0002X\u0082\u0004¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u0012\u0004\b\u0019\u0010\u001a¨\u0006\u001b"},
   d2 = {"Lkotlin/jvm/internal/PrimitiveSpreadBuilder;", "", "T", "", "size", "<init>", "(I)V", "spreadArgument", "", "addSpread", "(Ljava/lang/Object;)V", "()I", "values", "result", "toArray", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "getSize", "(Ljava/lang/Object;)I", "position", "I", "getPosition", "setPosition", "", "spreads", "[Ljava/lang/Object;", "getSpreads$annotations", "()V", "kotlin-stdlib"}
)
public abstract class PrimitiveSpreadBuilder {
   private final int size;
   private int position;
   @NotNull
   private final Object[] spreads;

   public PrimitiveSpreadBuilder(int size) {
      this.size = size;
      this.spreads = new Object[this.size];
   }

   protected abstract int getSize(@NotNull Object var1);

   protected final int getPosition() {
      return this.position;
   }

   protected final void setPosition(int <set-?>) {
      this.position = <set-?>;
   }

   /** @deprecated */
   // $FF: synthetic method
   private static void getSpreads$annotations() {
   }

   public final void addSpread(@NotNull Object spreadArgument) {
      Intrinsics.checkNotNullParameter(spreadArgument, "spreadArgument");
      Object[] var10000 = this.spreads;
      int var2 = this.position++;
      var10000[var2] = spreadArgument;
   }

   protected final int size() {
      int totalLength = 0;
      int i = 0;
      int var3 = this.size - 1;
      if (i <= var3) {
         while(true) {
            Object var10001 = this.spreads[i];
            totalLength += var10001 != null ? this.getSize(var10001) : 1;
            if (i == var3) {
               break;
            }

            ++i;
         }
      }

      return totalLength;
   }

   @NotNull
   protected final Object toArray(@NotNull Object values, @NotNull Object result) {
      Intrinsics.checkNotNullParameter(values, "values");
      Intrinsics.checkNotNullParameter(result, "result");
      int dstIndex = 0;
      int copyValuesFrom = 0;
      int i = 0;
      int var6 = this.size - 1;
      if (i <= var6) {
         while(true) {
            Object spreadArgument = this.spreads[i];
            if (spreadArgument != null) {
               if (copyValuesFrom < i) {
                  System.arraycopy(values, copyValuesFrom, result, dstIndex, i - copyValuesFrom);
                  dstIndex += i - copyValuesFrom;
               }

               int spreadSize = this.getSize(spreadArgument);
               System.arraycopy(spreadArgument, 0, result, dstIndex, spreadSize);
               dstIndex += spreadSize;
               copyValuesFrom = i + 1;
            }

            if (i == var6) {
               break;
            }

            ++i;
         }
      }

      if (copyValuesFrom < this.size) {
         System.arraycopy(values, copyValuesFrom, result, dstIndex, this.size - copyValuesFrom);
      }

      return result;
   }
}
