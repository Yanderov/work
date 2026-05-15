package kotlin.math;

import kotlin.ExperimentalUnsignedTypes;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.WasExperimental;
import kotlin.comparisons.UComparisonsKt;
import kotlin.internal.InlineOnly;

@Metadata(
   mv = {1, 9, 0},
   k = 2,
   xi = 48,
   d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a \u0010\u0005\u001a\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0000H\u0087\b¢\u0006\u0004\b\u0003\u0010\u0004\u001a \u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0001\u001a\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u0006H\u0087\b¢\u0006\u0004\b\u0007\u0010\b\u001a \u0010\n\u001a\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0000H\u0087\b¢\u0006\u0004\b\t\u0010\u0004\u001a \u0010\n\u001a\u00020\u00062\u0006\u0010\u0001\u001a\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u0006H\u0087\b¢\u0006\u0004\b\u000b\u0010\b¨\u0006\f"},
   d2 = {"Lkotlin/UInt;", "a", "b", "max-J1ME1BU", "(II)I", "max", "Lkotlin/ULong;", "max-eb3DHEI", "(JJ)J", "min-J1ME1BU", "min", "min-eb3DHEI", "kotlin-stdlib"}
)
public final class UMathKt {
   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalUnsignedTypes.class}
   )
   @InlineOnly
   private static final int min_J1ME1BU/* $FF was: min-J1ME1BU*/(int a, int b) {
      return UComparisonsKt.minOf-J1ME1BU(a, b);
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalUnsignedTypes.class}
   )
   @InlineOnly
   private static final long min_eb3DHEI/* $FF was: min-eb3DHEI*/(long a, long b) {
      return UComparisonsKt.minOf-eb3DHEI(a, b);
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalUnsignedTypes.class}
   )
   @InlineOnly
   private static final int max_J1ME1BU/* $FF was: max-J1ME1BU*/(int a, int b) {
      return UComparisonsKt.maxOf-J1ME1BU(a, b);
   }

   @SinceKotlin(
      version = "1.5"
   )
   @WasExperimental(
      markerClass = {ExperimentalUnsignedTypes.class}
   )
   @InlineOnly
   private static final long max_eb3DHEI/* $FF was: max-eb3DHEI*/(long a, long b) {
      return UComparisonsKt.maxOf-eb3DHEI(a, b);
   }
}
