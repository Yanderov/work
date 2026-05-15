package kotlin.enums;

import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.WasExperimental;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 2,
   xi = 48,
   d1 = {"\u0000\u001a\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\b\u0005\u001a(\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\"\u0010\b\u0000\u0010\u0001\u0018\u0001*\b\u0012\u0004\u0012\u00028\u00000\u0000H\u0087\b¢\u0006\u0004\b\u0003\u0010\u0004\u001a9\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\"\u000e\b\u0000\u0010\u0005*\b\u0012\u0004\u0012\u00028\u00000\u00002\u0012\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00070\u0006H\u0001¢\u0006\u0004\b\u0003\u0010\t\u001a3\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\"\u000e\b\u0000\u0010\u0005*\b\u0012\u0004\u0012\u00028\u00000\u00002\f\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007H\u0001¢\u0006\u0004\b\u0003\u0010\u000b¨\u0006\f"},
   d2 = {"", "T", "Lkotlin/enums/EnumEntries;", "enumEntries", "()Lkotlin/enums/EnumEntries;", "E", "Lkotlin/Function0;", "", "entriesProvider", "(Lkotlin/jvm/functions/Function0;)Lkotlin/enums/EnumEntries;", "entries", "([Ljava/lang/Enum;)Lkotlin/enums/EnumEntries;", "kotlin-stdlib"}
)
public final class EnumEntriesKt {
   // $FF: synthetic method
   @WasExperimental(
      markerClass = {ExperimentalStdlibApi.class}
   )
   @SinceKotlin(
      version = "2.0"
   )
   public static final EnumEntries enumEntries() {
      int $i$f$enumEntries = 0;
      throw new NotImplementedError((String)null, 1, (DefaultConstructorMarker)null);
   }

   @PublishedApi
   @SinceKotlin(
      version = "1.8"
   )
   @NotNull
   public static final EnumEntries enumEntries(@NotNull Function0 entriesProvider) {
      Intrinsics.checkNotNullParameter(entriesProvider, "entriesProvider");
      return new EnumEntriesList((Enum[])entriesProvider.invoke());
   }

   @PublishedApi
   @SinceKotlin(
      version = "1.8"
   )
   @NotNull
   public static final EnumEntries enumEntries(@NotNull Enum[] entries) {
      Intrinsics.checkNotNullParameter(entries, "entries");
      return new EnumEntriesList(entries);
   }
}
