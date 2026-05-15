package kotlin.time;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.WasExperimental;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0087\b\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0017\u0012\u0006\u0010\u0003\u001a\u00028\u0000\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\b\u001a\u00028\u0000HÆ\u0003¢\u0006\u0004\b\b\u0010\tJ\u0013\u0010\f\u001a\u00020\u0004HÆ\u0003ø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bJ*\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0003\u001a\u00028\u00002\b\b\u0002\u0010\u0005\u001a\u00020\u0004HÆ\u0001¢\u0006\u0004\b\r\u0010\u000eJ\u001a\u0010\u0012\u001a\u00020\u00112\b\u0010\u0010\u001a\u0004\u0018\u00010\u0002HÖ\u0003¢\u0006\u0004\b\u0012\u0010\u0013J\u0010\u0010\u0015\u001a\u00020\u0014HÖ\u0001¢\u0006\u0004\b\u0015\u0010\u0016J\u0010\u0010\u0018\u001a\u00020\u0017HÖ\u0001¢\u0006\u0004\b\u0018\u0010\u0019R\u001a\u0010\u0005\u001a\u00020\u00048\u0006ø\u0001\u0000¢\u0006\f\n\u0004\b\u0005\u0010\u001a\u001a\u0004\b\u001b\u0010\u000bR\u0017\u0010\u0003\u001a\u00028\u00008\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u001c\u001a\u0004\b\u001d\u0010\t\u0082\u0002\u0004\n\u0002\b!¨\u0006\u001e"},
   d2 = {"Lkotlin/time/TimedValue;", "T", "", "value", "Lkotlin/time/Duration;", "duration", "<init>", "(Ljava/lang/Object;JLkotlin/jvm/internal/DefaultConstructorMarker;)V", "component1", "()Ljava/lang/Object;", "component2-UwyO8pc", "()J", "component2", "copy-RFiDyg4", "(Ljava/lang/Object;J)Lkotlin/time/TimedValue;", "copy", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "J", "getDuration-UwyO8pc", "Ljava/lang/Object;", "getValue", "kotlin-stdlib"}
)
@SinceKotlin(
   version = "1.9"
)
@WasExperimental(
   markerClass = {ExperimentalTime.class}
)
public final class TimedValue {
   private final Object value;
   private final long duration;

   private TimedValue(Object value, long duration) {
      this.value = value;
      this.duration = duration;
   }

   public final Object getValue() {
      return this.value;
   }

   public final long getDuration_UwyO8pc/* $FF was: getDuration-UwyO8pc*/() {
      return this.duration;
   }

   public final Object component1() {
      return this.value;
   }

   public final long component2_UwyO8pc/* $FF was: component2-UwyO8pc*/() {
      return this.duration;
   }

   @NotNull
   public final TimedValue copy_RFiDyg4/* $FF was: copy-RFiDyg4*/(Object value, long duration) {
      return new TimedValue(value, duration, (DefaultConstructorMarker)null);
   }

   // $FF: synthetic method
   public static TimedValue copy_RFiDyg4$default/* $FF was: copy-RFiDyg4$default*/(TimedValue var0, Object var1, long var2, int var4, Object var5) {
      if ((var4 & 1) != 0) {
         var1 = var0.value;
      }

      if ((var4 & 2) != 0) {
         var2 = var0.duration;
      }

      return var0.copy-RFiDyg4(var1, var2);
   }

   @NotNull
   public String toString() {
      return "TimedValue(value=" + this.value + ", duration=" + Duration.toString-impl(this.duration) + ')';
   }

   public int hashCode() {
      int result = this.value == null ? 0 : this.value.hashCode();
      result = result * 31 + Duration.hashCode-impl(this.duration);
      return result;
   }

   public boolean equals(@Nullable Object other) {
      if (this == other) {
         return true;
      } else if (!(other instanceof TimedValue)) {
         return false;
      } else {
         TimedValue var2 = (TimedValue)other;
         if (!Intrinsics.areEqual(this.value, var2.value)) {
            return false;
         } else {
            return Duration.equals-impl0(this.duration, var2.duration);
         }
      }
   }

   // $FF: synthetic method
   public TimedValue(Object value, long duration, DefaultConstructorMarker $constructor_marker) {
      this(value, duration);
   }
}
